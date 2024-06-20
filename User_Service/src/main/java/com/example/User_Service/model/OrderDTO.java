package com.example.User_Service.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
	private int userId;
	private long totalPrice;
	private String status;
	private String deliveryAddress;
	private String paymentDetails;
	private LocalDateTime createdAt = LocalDateTime.now();
	private List<OrderItemDTO> items = new ArrayList<>();
}