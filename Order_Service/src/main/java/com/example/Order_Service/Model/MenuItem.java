package com.example.Order_Service.Model;

import java.util.Date;

import com.example.Order_Service.Entity.OrderEntity;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {

	private int id;
	private String name;
	private String desc;
	private int price;
	private int restaurantId;
	private Date createdAt;

}
