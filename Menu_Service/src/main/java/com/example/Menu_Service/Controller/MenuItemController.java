package com.example.Menu_Service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Menu_Service.DTO.MenuDTO;
import com.example.Menu_Service.Model.Review;
import com.example.Menu_Service.Service.MenuService;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class MenuItemController {

	@Autowired
	MenuService menuService;

	// Create a new menu item.
	@PostMapping("/menus")
	ResponseEntity<MenuDTO> registerMenuItem(@RequestBody MenuDTO item) {
		MenuDTO dto=menuService.registerMenuItem(item);
		return ResponseEntity.ok(dto);
	}

	// Get a list of all menu items from all restaurants
	@GetMapping("/menus")
	ResponseEntity<List<MenuDTO>> getAllMenuItems() {
		List<MenuDTO> dtos=menuService.getAllMenuFromAllRestaurant();
		return ResponseEntity.ok(dtos);
	}

	// Get details of a specific menu item
	@GetMapping("/menus/{id}")
	ResponseEntity<MenuDTO> getMenuItemById(@PathVariable("id") int id) {
		MenuDTO dto=menuService.getMenuItemById(id);
		return ResponseEntity.ok(dto);
	}

	// Update an existing menu item.
	@PutMapping("/menus/{id}")
	ResponseEntity<MenuDTO> updateMenuItem(@PathVariable("id") int id, @RequestBody MenuDTO item) {
		MenuDTO dto=menuService.updateMenuItem(id, item);
		return ResponseEntity.ok(dto);
	}

	// Delete a menu item.
	@DeleteMapping("/menus/{id}")
	ResponseEntity<Boolean> deleteMenuItem(@PathVariable("id") int id) {
		Boolean isMenuItemDeleted=menuService.deleteMenuItem(id);
		return ResponseEntity.ok(isMenuItemDeleted);
	}
	
	//Get Menu Items for a Restaurant
	@GetMapping("/restaurants/{restaurantId}/menus")
	public ResponseEntity<List<MenuDTO>> getMenuItemsByRestaurant(@PathVariable int restaurantId) {
        List<MenuDTO> menuItems = menuService.getMenuItemsByRestaurant(restaurantId);
        return ResponseEntity.ok(menuItems); 
    }

	// Get reviews for a specific menu item.
	@GetMapping("/menus/{id}/reviews")
	ResponseEntity<List<Review>> getAllReviewOfMenuItem(@PathVariable("id") int id) {
		return null;
	}
}
