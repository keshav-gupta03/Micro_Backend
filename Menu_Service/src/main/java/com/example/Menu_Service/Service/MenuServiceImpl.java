package com.example.Menu_Service.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.Menu_Service.DTO.MenuDTO;
import com.example.Menu_Service.DTO.RestaurantDTO;
import com.example.Menu_Service.Entity.MenuItem;
import com.example.Menu_Service.FeignClient.RestaurantFeign;
import com.example.Menu_Service.Model.Review;
import com.example.Menu_Service.Repository.MenuItemRepository;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuItemRepository itemRepository;

	@Autowired
	ModelMapper mapper;
	
	@Autowired
	RestaurantFeign restaurantFeign; 

	@Override
	public MenuDTO registerMenuItem(MenuDTO item) {
		// TODO Auto-generated method stub
		MenuItem menuItem = mapper.map(item, MenuItem.class);
		menuItem.setCreatedAt(LocalDateTime.now());
		try {
			menuItem = itemRepository.save(menuItem);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to register Menu Item");
		}
		return mapper.map(menuItem, MenuDTO.class);
	}

	@Override
	public MenuDTO updateMenuItem(int id, MenuDTO item) {
		// TODO Auto-generated method stub
		MenuItem menuItem = itemRepository.findByIdAndIsDeleted(id, false);
		if(menuItem==null) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"MenuItem with given id not found");
		}
		menuItem = mapper.map(item, MenuItem.class);
		itemRepository.save(menuItem);

		return item;
	}

	@Override
	public Boolean deleteMenuItem(int menuItemId) {
		// TODO Auto-generated method stub
		try {
			MenuItem menuItem = itemRepository.findByIdAndIsDeleted(menuItemId, false);
			if(menuItem==null) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
						"MenuItem with given id not found");
			}
			menuItem.setIsDeleted(true);
			itemRepository.save(menuItem);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Some Error occured");
		}

		return true;
	}

	@Override
	public List<MenuDTO> getAllMenuFromAllRestaurant() {
		// TODO Auto-generated method stub
		List<MenuItem> menuItems = null;
		try {
			menuItems = itemRepository.findByIsDeleted(false);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Some Error occured");
		}
		List<MenuDTO> dtos = menuItems.stream().map(item -> mapper.map(item, MenuDTO.class))
				.collect(Collectors.toList());
		return dtos;
	}

	@Override
	public List<MenuDTO> getMenuItemsByRestaurant(int restaurantId) {
		// TODO Auto-generated method stub
		List<MenuDTO> dtos = null;
		try {
			List<MenuItem> items = itemRepository.findByRestaurantIdAndIsDeleted(restaurantId,false);
			dtos = items.stream().map(item -> mapper.map(item, MenuDTO.class)).toList();
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Some Error occured");
		}

		return dtos;
	}

	@Override
	public MenuDTO getMenuItemById(int menuItemId) {
		// TODO Auto-generated method stub
		MenuItem menuItem = itemRepository.findByIdAndIsDeleted(menuItemId,false);
		if(menuItem==null) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"MenuItem with given id not found");
		}
		return mapper.map(menuItem, MenuDTO.class);
	}

	@Override
	public Review getReviewForMenuItem(int menuItemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantDTO getRestaurantDetailsForMenuItem(int menuItemId) {
		// TODO Auto-generated method stub
		MenuItem menuItem= itemRepository.findByIdAndIsDeleted(menuItemId, false);
		if(menuItem==null) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
					"MenuItem with given id not found");
		}
		ResponseEntity<RestaurantDTO> responseEntity= restaurantFeign.getRestaurantById(menuItem.getRestaurantId());
		if(responseEntity.getStatusCode()!=HttpStatus.OK) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Some Error occured");
		}
		RestaurantDTO restaurant= responseEntity.getBody();
		
		return restaurant;
	}

}
