package com.example.Restaurant_Service.Model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Review {

	private int id;
	private int userId;
	private double rating;
	private String comment;
	private int restaurantId;
	private Date createdAt;
}
