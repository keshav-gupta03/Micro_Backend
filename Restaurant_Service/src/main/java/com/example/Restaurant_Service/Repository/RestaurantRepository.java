package com.example.Restaurant_Service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Restaurant_Service.Entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{

	Restaurant findByIdAndIsDeleted(int id,Boolean isDeleted);
	List<Restaurant> findByIsDeleted(Boolean isDeleted);
}
