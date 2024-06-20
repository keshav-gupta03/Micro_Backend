package com.example.Menu_Service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Menu_Service.Entity.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

	List<MenuItem> findByRestaurantIdAndIsDeleted(int restaurantId,Boolean isDeleted);
	List<MenuItem> findByIsDeleted(Boolean isDeleted);
	MenuItem findByIdAndIsDeleted(int id,Boolean isDeleted);
}
