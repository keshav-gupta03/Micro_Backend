package com.example.User_Service.DTO;

import lombok.Data;

@Data
public class UserDTO {

	private int id;
	private String name;
	private String email;
	private String password;
	private long phone;
	private AddressDTO address;
}
