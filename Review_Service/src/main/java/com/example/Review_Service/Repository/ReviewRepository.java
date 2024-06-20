package com.example.Review_Service.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Review_Service.Entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>{

	List<Review> findByRestaurantId(int restaurantId);

	List<Review> findByUserId(int userId);
}
