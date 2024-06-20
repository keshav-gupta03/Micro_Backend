package com.example.User_Service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.User_Service.DTO.ResponseDTO;
import com.example.User_Service.DTO.UserDTO;
import com.example.User_Service.Service.UserService;
import com.example.User_Service.model.OrderDTO;
import com.example.User_Service.model.Review;



@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
@Validated
public class UserController {

	@Autowired
	UserService service;
	
	@GetMapping("/users")
	ResponseEntity<List<UserDTO>> getAllUsers(){
		List<UserDTO> listOfUser= service.getAllUser();
		if(listOfUser!=null)
		return ResponseEntity.ok(listOfUser);
		
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/users/register")
	ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto){
		UserDTO createdUser = service.createUser(dto);
        if (createdUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
	}
	
	
	@PostMapping("/users/login/")
	ResponseEntity<Boolean> authenticateUser(@RequestParam(name = "id") Integer id, @RequestParam(name="password") String password){
		Boolean isAuthenticated = service.authenticateUser(id,password);
        if (isAuthenticated) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
	}
	
	@DeleteMapping("/users/{id}")
	ResponseEntity<ResponseDTO> deleteUser(@PathVariable("id") int id){
		Boolean isDeleted= service.deleteUserById(id);
		if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO("200", "User Deleted Successfully"));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO("400", "Some error occured"));
        }
	}
	
	@GetMapping("/users/{id}")
	ResponseEntity<UserDTO> getUserDetails(
			 @PathVariable("id") int id){
		
		UserDTO userDetails = service.getUserDetails(id);
        if (userDetails != null) {
            return ResponseEntity.ok(userDetails);
        } else {
        	System.out.println("not foutn");
            return ResponseEntity.notFound().build();
        }
	}
	
	
	@GetMapping("/users/{id}/orders")
	ResponseEntity<List<OrderDTO>> getAllOrderByUser(@PathVariable("id") int id){
		List<OrderDTO> userOrders = service.getAllOrders(id);
        return ResponseEntity.ok(userOrders);
	}
	
	
	@GetMapping("/users/{id}/reviews")
	ResponseEntity<List<Review>> getAllReviewByUser(@PathVariable("id") int id){
		List<Review> userReviews = service.getAllReview(id);
        return ResponseEntity.ok(userReviews);
	}
}
