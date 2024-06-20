package com.example.User_Service.Entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
public class User  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String password;
	private long phone;
	private LocalDateTime createdAt = LocalDateTime.now();
	private Boolean isDeleted=false;
	
	@OneToOne(cascade = CascadeType.ALL) // Cascade operations to the associated Address
    @JoinColumn(name = "address_id") // Name of the foreign key column in UserEntity table
	private Address address;
	
	
	
	public User() {
		super();
	}



	public User(int id, String name, String email, String password, Address address, long phone, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.createdAt = createdAt;
	}



	
	
	
	
}
