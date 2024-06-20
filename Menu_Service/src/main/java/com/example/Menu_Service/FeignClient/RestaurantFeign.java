package com.example.Menu_Service.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Menu_Service.DTO.RestaurantDTO;

@FeignClient(url="http://localhost:8001/" ,name = "restaurant-service")
public interface RestaurantFeign {

	@GetMapping("/restaurants/{id}")
	ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable("id") int id);
}
