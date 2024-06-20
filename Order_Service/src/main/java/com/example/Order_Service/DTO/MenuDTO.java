package com.example.Order_Service.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuDTO {

	private Integer id;
	private String name;
	private String description;
	private int price;
	private int restaurantId;
	private LocalDateTime createdAt;
}
