package com.example.Restaurant_Service.Model;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {

	private String name;
	private String description;
	private int price;
	private int restaurantId;
	private LocalDateTime createdAt;
}

