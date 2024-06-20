package com.example.Review_Service.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.Review_Service.Entity.Review;

public interface ReviewService {

	ResponseEntity<Review> postReview(Review review);
	ResponseEntity<Review> getReviewById(int reviewId);
	ResponseEntity<List<Review>> getAllReviewOfRestaurant(int restaurantId);
	ResponseEntity<List<Review>> getAllReviewOfUser(int userId);
	ResponseEntity<Boolean> deleteReview(int reviewId);
}
