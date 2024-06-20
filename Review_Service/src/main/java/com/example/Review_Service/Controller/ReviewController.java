package com.example.Review_Service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Review_Service.Entity.Review;
import com.example.Review_Service.Service.ReviewService;

@RestController
@RequestMapping("/")
public class ReviewController {

	@Autowired
	ReviewService reviewService;

	// Submit a new review for a restaurant.
	@PostMapping("/reviews")
	ResponseEntity<Review> postReview(@RequestBody Review review) {
		return reviewService.postReview(review);
	}

	// Get details of a specific review by its ID.
	@GetMapping("/reviews/{reviewId}")
	ResponseEntity<Review> getReviewById(@PathVariable("reviewId") int reviewId) {
		return reviewService.getReviewById(reviewId);
	}

	// Get a list of reviews for a specific restaurant.
	@GetMapping("/reviews/restaurant/{restaurantId}")
	ResponseEntity<List<Review>> getAllReviewByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
		return reviewService.getAllReviewOfRestaurant(restaurantId);
	}

	// Get a list of reviews submitted by a specific user.
	@GetMapping("/reviews/users/{userId}")
	ResponseEntity<List<Review>> getAllReviewOfUser(@PathVariable("userId") int userId) {
		return reviewService.getAllReviewOfUser(userId);
	}

}
