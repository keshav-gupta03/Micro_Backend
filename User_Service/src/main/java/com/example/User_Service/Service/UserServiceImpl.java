package com.example.User_Service.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.User_Service.DTO.UserDTO;
import com.example.User_Service.Entity.User;
import com.example.User_Service.FeignClient.OrderServiceClient;
import com.example.User_Service.Repository.UserRepository;
import com.example.User_Service.exceptions.UserNotFoundException;
import com.example.User_Service.model.OrderDTO;
import com.example.User_Service.model.Review;

import feign.FeignException;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository repository;
	private OrderServiceClient orderServiceClient;
	private ModelMapper mapper;
	
	@Autowired
	public UserServiceImpl(UserRepository repository, OrderServiceClient orderServiceClient, ModelMapper mapper) {
		this.repository = repository;
		this.orderServiceClient = orderServiceClient;
		this.mapper = mapper;
	}

	@Override
	public List<UserDTO> getAllUser() {
		// TODO Auto-generated method stub
		List<User> listOfUsers = repository.findAll();

		List<UserDTO> userDTOList = listOfUsers.stream().map(user -> mapper.map(user, UserDTO.class))
				.collect(Collectors.toList());

		return userDTOList;
	}

	@Override
	public UserDTO createUser(UserDTO userDto) {

		// TODO Auto-generated method stub
		User user = mapper.map(userDto, User.class);
		try {
			User savedUser = repository.save(user);
			return mapper.map(savedUser, UserDTO.class);
		} catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Got some error");
		}
		

	}

	@Override
	public Boolean authenticateUser(Integer userId, String password) {
		// TODO Auto-generated method stub
		User user = repository.findByIdAndIsDeleted(userId, false).orElseThrow(
				() -> new UserNotFoundException("User", "userId", userId));
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");
		} else {
			if (user.getPassword().equals(password)) {
				return true;
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password is Wrong");
			}

		}
	}

	@Override
	public UserDTO getUserDetails(int userId) {
		// TODO Auto-generated method stub
		User user = repository.findByIdAndIsDeleted(userId, false).orElseThrow(
				() -> new UserNotFoundException("User", "userId", userId));
	
		return mapper.map(user, UserDTO.class);
	}

	@Override
	public List<OrderDTO> getAllOrders(int userId) {
		// TODO Auto-generated method stub
		User user = repository.findByIdAndIsDeleted(userId, false).orElseThrow(
				() -> new UserNotFoundException("User", "userId", userId));

		try {
			ResponseEntity<List<OrderDTO>> response = orderServiceClient.getAllOrdersForUser(userId);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				return response.getBody();
			} else {
				throw new ResponseStatusException(response.getStatusCode(),
						"Error while fetching orders for userId: " + userId);
			}
		} catch (FeignException.NotFound e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No orders found for userId: " + userId);
		}
	}

	@Override
	public List<Review> getAllReview(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Boolean deleteUserById(int userId) {
		// TODO Auto-generated method stub
		User user = repository.findByIdAndIsDeleted(userId, false).orElseThrow(
				() -> new UserNotFoundException("User", "userId", userId));
		user.setIsDeleted(true);
		user.getAddress().setIsDeleted(true);
		repository.save(user);
		try {
			ResponseEntity<Boolean> response = orderServiceClient.deleteOrderByUserId(userId);

			if (response.getStatusCode().equals(HttpStatus.OK)) {
				return response.getBody();
			} else {
				throw new ResponseStatusException(response.getStatusCode(),
						"Error while fetching orders for userId: " + userId);
			}
		} catch (FeignException.NotFound e) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No orders found for userId: " + userId);
			return true;
		}
	}

}
