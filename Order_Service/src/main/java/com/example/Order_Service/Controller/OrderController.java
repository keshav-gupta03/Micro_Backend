package com.example.Order_Service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Order_Service.DTO.OrderDTO;
import com.example.Order_Service.DTO.ResponseDTO;
import com.example.Order_Service.Entity.OrderEntity;
import com.example.Order_Service.Service.OrderService;

@RestController
@RequestMapping("/")
public class OrderController {

	@Autowired
	OrderService orderService;

	// Get all the orders
	@GetMapping("/orders")
	ResponseEntity<List<OrderDTO>> getAllOrders() {
		List<OrderDTO> orderDTOs = orderService.getAllOrders();
	    if(orderDTOs==null) {
	    	return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.status(HttpStatus.OK).body(orderDTOs);
	}

	// Place a new order
	@PostMapping("/orders")
	ResponseEntity<OrderDTO> placeNewOrder(@RequestBody OrderDTO orderDTO) {
		OrderDTO entity = orderService.placeOrder(orderDTO);
		if (entity.getUserId() == 0) {
			ResponseEntity.badRequest().body(new OrderEntity());
		}
		return ResponseEntity.ok(entity);
	}

	// Get details of a specific order.
	@GetMapping("/orders/{id}")
	ResponseEntity<OrderDTO> getOrderDetails(@PathVariable("id") int orderId) {
		OrderDTO orderEntity = orderService.getOrderDetails(orderId);
		if (orderEntity == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(orderEntity);
	}

	// Retrieve a list of orders placed by a specific user.
	@GetMapping("/users/{userId}/orders")
	ResponseEntity<List<OrderDTO>> getAllOrderPlacedByUser(@PathVariable("userId") int userId) {
		List<OrderDTO> orders = orderService.getAllOrderPlacedByUser(userId);
		if (orders == null) {
			return ResponseEntity.notFound().build(); // Create an empty list to return
		}
		return ResponseEntity.ok(orders);

	}

	// Track the delivery status of a specific order.
	@GetMapping("/orders/{id}/track")
	ResponseEntity<String> getOrderStatus(@PathVariable("id") int orderId) {
		String deliveryStatus = orderService.getDeliveryStatus(orderId);
		if (deliveryStatus == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(deliveryStatus);
	}

	// Cancel a specific order.
	@PatchMapping("/orders/{id}/cancel")
	ResponseEntity<Boolean> cancelOrder(@PathVariable("id") int ordreId) {
		boolean isCanceled = orderService.cancelOrder(ordreId);
		
		return ResponseEntity.ok(isCanceled);
	}

	// Delete Order by orderId
	@DeleteMapping("/orders/{id}")
	ResponseEntity<ResponseDTO> deleteOrder(@PathVariable("id") int orderId) {
		Boolean isOrderDeleted = orderService.deleteOrderById(orderId);

		if(isOrderDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO("200", "Order Deleted Successfully"));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO("400", "Some error occured"));
        }
	}

	// Delete Order By User Id
	@DeleteMapping("/orders/delete/{userId}")
	ResponseEntity<ResponseDTO> deleteOrderByUserId(@PathVariable("userId") int userId) {
		Boolean isOrderDeleted = orderService.deleteOrderByUserId(userId);
		if(isOrderDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO("200", "Order Deleted Successfully"));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO("400", "Some error occured"));
        }
	}

}
