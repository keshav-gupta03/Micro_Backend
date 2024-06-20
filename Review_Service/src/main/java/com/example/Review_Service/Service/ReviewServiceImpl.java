package com.example.Review_Service.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Review_Service.Entity.Review;
import com.example.Review_Service.Repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository repository;

    @Override
    public ResponseEntity<Review> postReview(Review review) {
        try {
            Review savedReview = repository.save(review);
            return ResponseEntity.ok(savedReview);
        } catch (Exception e) {
            // Handle database-related exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Review> getReviewById(int reviewId) {
        try {
            Review review = repository.findById(reviewId).orElse(null);

            if (review != null) {
                return ResponseEntity.ok(review);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle database-related exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<List<Review>> getAllReviewOfRestaurant(int restaurantId) {
        try {
            List<Review> reviews = repository.findByRestaurantId(restaurantId);

            if (!reviews.isEmpty()) {
                return ResponseEntity.ok(reviews);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle database-related exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<List<Review>> getAllReviewOfUser(int userId) {
        try {
            List<Review> reviews = repository.findByUserId(userId);

            if (!reviews.isEmpty()) {
                return ResponseEntity.ok(reviews);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle database-related exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Boolean> deleteReview(int reviewId) {
        try {
            Review review = repository.findById(reviewId).orElse(null);

            if (review != null) {
                repository.delete(review);
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle database-related exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
