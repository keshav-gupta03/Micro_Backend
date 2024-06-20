package com.example.Restaurant_Service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Restaurant_Service.Model.MenuDTO;
import com.example.Restaurant_Service.Model.RestaurantDTO;
import com.example.Restaurant_Service.Model.Review;
import com.example.Restaurant_Service.Service.RestaurantService;

@RestController
@RequestMapping("/")
public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;

	@GetMapping("/restaurants")
	ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
		List<RestaurantDTO> restaurants = restaurantService.getAllRestaurants();
		return new ResponseEntity<>(restaurants, HttpStatus.OK);
	}

	@PostMapping("/restaurants")
	ResponseEntity<RestaurantDTO> registerRestaurant(@RequestBody RestaurantDTO restaurant) {
		RestaurantDTO registeredRestaurant = restaurantService.registerRestaurant(restaurant);
		return new ResponseEntity<>(registeredRestaurant, HttpStatus.CREATED);

	}
	
	@DeleteMapping("/restaurants/{id}")
	ResponseEntity<Boolean> deleteRestaurantById(@PathVariable("id") int restaurantId){
		Boolean isDeleted = restaurantService.deleteRestaurantById(restaurantId);
		return ResponseEntity.ok(isDeleted);
	}

	@GetMapping("/restaurants/{id}")
	ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable("id") int id) {
		RestaurantDTO restaurant = restaurantService.getDetailsOfRestaurant(id);
		if (restaurant != null) {
			return new ResponseEntity<>(restaurant, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/restaurants/{id}/menu")
	ResponseEntity<List<MenuDTO>> getMenuOfRestaurant(@PathVariable("id") int id) {
		List<MenuDTO> menuDTOs = restaurantService.getMenuOfRestaurant(id);
		if (menuDTOs != null) {
			return new ResponseEntity<>(menuDTOs, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/restaurants/{id}/reviews")
	ResponseEntity<List<Review>> getAllReviewOfRestaurant(@PathVariable("id") int id) {
		List<Review> reviews = restaurantService.getReviewOfRestaurant(id);
		if (reviews != null) {
			return new ResponseEntity<>(reviews, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
