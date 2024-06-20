package com.example.Restaurant_Service.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.Restaurant_Service.Entity.Restaurant;
import com.example.Restaurant_Service.FeignClient.MenuItemFeign;
import com.example.Restaurant_Service.Model.MenuDTO;
import com.example.Restaurant_Service.Model.RestaurantDTO;
import com.example.Restaurant_Service.Model.Review;
import com.example.Restaurant_Service.Repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	MenuItemFeign menuItemFeign;

	@Override
	public List<RestaurantDTO> getAllRestaurants() {
		List<Restaurant> restaurants= restaurantRepository.findByIsDeleted(false);
		restaurants.forEach(System.out::println);
		List<RestaurantDTO> dtos= restaurants.stream().map(res-> mapper.map(res, RestaurantDTO.class))
				.collect(Collectors.toList());
		return dtos;
	}
	
	@Override
	public RestaurantDTO registerRestaurant(RestaurantDTO restaurant) {
		// TODO Auto-generated method stub
		Restaurant restaurantToBeSaved=mapper.map(restaurant, Restaurant.class);
		restaurantToBeSaved.setCreatedAt(LocalDateTime.now());
		try {
			Restaurant savedRestaurant= restaurantRepository.save(restaurantToBeSaved);
			return mapper.map(savedRestaurant, RestaurantDTO.class);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to register restaurant");
		}
	}

	@Override
	public RestaurantDTO getDetailsOfRestaurant(int restaurantId) {
		// TODO Auto-generated method stub
		Restaurant restaurant= restaurantRepository.findByIdAndIsDeleted(restaurantId, false);
		if(restaurant==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
		}
			return mapper.map(restaurant, RestaurantDTO.class);
	}

	@Override
	public List<MenuDTO> getMenuOfRestaurant(int restaurantId) {
		// TODO Auto-generated method stub
		ResponseEntity<List<MenuDTO>> menuDTOs = menuItemFeign.getMenuItemsByRestaurant(restaurantId);
		if(menuDTOs.getStatusCode()!=HttpStatus.OK) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
		}
		List<MenuDTO> dtos=menuDTOs.getBody();
		return dtos;
	}

	@Override
	public List<Review> getReviewOfRestaurant(int restaurantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteRestaurantById(int restaurantId) {
		Restaurant restaurant = restaurantRepository.findByIdAndIsDeleted(restaurantId, false);
		System.out.println(restaurant);
		if(restaurant==null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurant not found");
		}
		restaurant.setIsDeleted(true);
		try {
			restaurantRepository.save(restaurant);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "some error occurred");
		}
		
		return true;
	}

	

}
