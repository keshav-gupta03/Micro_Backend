package com.example.User_Service.Service;

import java.util.List;

import com.example.User_Service.DTO.UserDTO;
import com.example.User_Service.model.OrderDTO;
import com.example.User_Service.model.Review;


public interface UserService {
	List<UserDTO> getAllUser();
	UserDTO createUser(UserDTO userDto);
	Boolean authenticateUser(Integer id, String password);
	UserDTO getUserDetails(int userId);
	List<OrderDTO> getAllOrders(int uerId);
	List<Review> getAllReview(int userId);
	Boolean deleteUserById(int userId);

}
