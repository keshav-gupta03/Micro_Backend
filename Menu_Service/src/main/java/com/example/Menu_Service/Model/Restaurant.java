package com.example.Menu_Service.Model;

import java.util.Date;

import lombok.Data;

@Data
public class Restaurant {

	private int id;
	private String name;
	private String description;
	private String location;
	private long contactNumber;
	private Date createdAt;
}
