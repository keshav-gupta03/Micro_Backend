package com.example.Order_Service.Service;

import java.util.List;

import com.example.Order_Service.DTO.OrderDTO;

public interface OrderService {
	
	List<OrderDTO> getAllOrders();
	OrderDTO placeOrder(OrderDTO orderDTO);
	OrderDTO getOrderDetails(int orderId);
	List<OrderDTO> getAllOrderPlacedByUser(int userId);
	Boolean cancelOrder(int orderId);
	String getDeliveryStatus(int orderId);
	Boolean deleteOrderById(int orderId);
	Boolean deleteOrderByUserId(int userId);
	
}
