package com.example.Restaurant_Service.FeignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.Restaurant_Service.Model.MenuDTO;


@FeignClient(url = "http://localhost:8003/" ,name = "menu-service")
public interface MenuItemFeign {

	@GetMapping("/restaurants/{restaurantId}/menus")
	public ResponseEntity<List<MenuDTO>> getMenuItemsByRestaurant(@PathVariable int restaurantId);
}
