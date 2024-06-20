package com.example.User_Service.FeignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.User_Service.model.OrderDTO;

@FeignClient(url="http://localhost:8002/" ,name = "order-service")
public interface OrderServiceClient {

	@GetMapping("/users/{userId}/orders") 
	ResponseEntity<List<OrderDTO>> getAllOrdersForUser(@PathVariable("userId") int userId);
	
	@DeleteMapping("/orders/delete/{userId}")
	ResponseEntity<Boolean> deleteOrderByUserId(@PathVariable("userId") int userId);
}
