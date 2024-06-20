package com.example.Menu_Service.Service;

import java.util.List;

import com.example.Menu_Service.DTO.MenuDTO;
import com.example.Menu_Service.DTO.RestaurantDTO;
import com.example.Menu_Service.Model.Restaurant;
import com.example.Menu_Service.Model.Review;


public interface MenuService {

	public MenuDTO registerMenuItem(MenuDTO item);
	public MenuDTO updateMenuItem(int id,MenuDTO item);
	public Boolean deleteMenuItem(int menuItemId);
	public List<MenuDTO> getAllMenuFromAllRestaurant();
	public List<MenuDTO> getMenuItemsByRestaurant(int restaurantId);
	public MenuDTO getMenuItemById(int menuItemId);
	public Review getReviewForMenuItem(int menuItemId);
	public RestaurantDTO getRestaurantDetailsForMenuItem(int menuItemId);
}
