package com.example.Order_Service.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Order_Service.DTO.MenuDTO;
import com.example.Order_Service.DTO.OrderDTO;
import com.example.Order_Service.Entity.OrderEntity;
import com.example.Order_Service.FeignClient.MenuItemFeign;
import com.example.Order_Service.Repository.OrderRepository;
import com.example.Order_Service.exception.OrderNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	MenuItemFeign menuFeign;

	@Override
	@Transactional
	public OrderDTO placeOrder(OrderDTO orderDTO) {
		List<MenuDTO> menusList= menuFeign.getAllMenuItems().getBody();
		
		long totalPrice = orderDTO.getItems().stream()
		        .mapToLong(orderItem -> {
		            // Filter the menu item based on menuItemId and calculate the totalPrice
		            return menusList.stream()
		                .filter(menuItem -> {
		                	 if(menuItem.getId() == orderItem.getMenuItemId()) {
		                		 orderItem.setPrice(menuItem.getPrice());
		                		 return true;
		                	 }else {
		                		 return false;
		                	 }
		                })
		                .mapToLong(menuItem -> menuItem.getPrice())
		                .sum();
		        })
		        .sum();
		
		orderDTO.setTotalPrice(totalPrice);

		OrderEntity orderEntity = mapper.map(orderDTO, OrderEntity.class);
		
		OrderEntity savedOrderEntity = orderRepository.save(orderEntity);
		// Map the saved OrderEntity back to OrderDTO
		OrderDTO savedOrderDTO = mapper.map(savedOrderEntity, OrderDTO.class);
		return savedOrderDTO;
	}

	@Override
	public OrderDTO getOrderDetails(int orderId) {
		// TODO Auto-generated method stub
		OrderEntity orderEntity = orderRepository.findByIdAndIsDeleted(orderId, false).orElseThrow(
				() -> new OrderNotFoundException("order", "orderid", orderId));
		

		return mapper.map(orderEntity, OrderDTO.class);
	}

	@Override
	public List<OrderDTO> getAllOrderPlacedByUser(int userId) {

		List<OrderEntity> ListOfOrders = orderRepository.findByUserIdAndIsDeleted(userId, false);

		if (ListOfOrders.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order for userId:" + userId + " does not found");
		}
		return ListOfOrders.stream().map(orderEntity -> mapper.map(orderEntity, OrderDTO.class))
				.collect(Collectors.toList());

	}

	@Override
	public Boolean cancelOrder(int orderId) {
		OrderEntity orderEntity = orderRepository.findByIdAndIsDeleted(orderId, false).orElseThrow(
				() -> new OrderNotFoundException("order", "orderid", orderId));
		
		orderEntity.setStatus("Canceled");
		try {
			orderRepository.save(orderEntity);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Got some error : " + e.getMessage());
		}

		return true;
	}

	@Override
	public String getDeliveryStatus(int orderId) {
		OrderEntity orderEntity = orderRepository.findByIdAndIsDeleted(orderId, false).orElseThrow(
				() -> new OrderNotFoundException("order", "orderid", orderId));
	

		return orderEntity.getStatus();
	}

	@Override
	public Boolean deleteOrderById(int orderId) {
		// TODO Auto-generated method stub
		OrderEntity orderEntity = orderRepository.findByIdAndIsDeleted(orderId, false).orElseThrow(
				() -> new OrderNotFoundException("order", "orderid", orderId));
		
		orderEntity.setIsDeleted(true);
		try {
			orderRepository.save(orderEntity);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Got some error : " + e.getMessage());
		}
		return true;
	}

	@Override
	public Boolean deleteOrderByUserId(int userId) {
		// TODO Auto-generated method stub
		List<OrderEntity> ListOfOrders = orderRepository.findByUserIdAndIsDeleted(userId, false);

		if (ListOfOrders.size() == 0) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order for userId:" + userId + " does not found");
		}
		ListOfOrders.stream().forEach(order->order.setIsDeleted(true));
		try {
			orderRepository.saveAll(ListOfOrders);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Got some error : " + e.getMessage());
		}
		return true;
	}

	@Override
	public List<OrderDTO> getAllOrders() {
		// TODO Auto-generated method stub
		List<OrderEntity> orderEntities = orderRepository.findAll();
		if (orderEntities.size() != 0) {
			return orderEntities.stream().map(order -> mapper.map(order, OrderDTO.class)).toList();
		}
		return null;
	}

}
