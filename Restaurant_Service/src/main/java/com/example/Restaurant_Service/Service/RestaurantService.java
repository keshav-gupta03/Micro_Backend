package com.example.Restaurant_Service.Service;

import java.util.List;

import com.example.Restaurant_Service.Model.MenuDTO;
import com.example.Restaurant_Service.Model.RestaurantDTO;
import com.example.Restaurant_Service.Model.Review;


public interface RestaurantService {

	public List<RestaurantDTO> getAllRestaurants();
	public RestaurantDTO getDetailsOfRestaurant(int restaurantId);
	public List<MenuDTO> getMenuOfRestaurant(int restaurantId);
	public List<Review> getReviewOfRestaurant(int restaurantId);
	public RestaurantDTO registerRestaurant(RestaurantDTO restaurant);
	public Boolean deleteRestaurantById(int restaurantId);
}
