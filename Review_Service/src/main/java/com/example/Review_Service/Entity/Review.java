package com.example.Review_Service.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
	@Id
	private int id;
	private int userId;
	private float rating;
	private String comment;
	private int restaurantId;
	private Date createdAt;

}
