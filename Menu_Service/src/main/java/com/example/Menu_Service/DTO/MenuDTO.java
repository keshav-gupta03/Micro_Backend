package com.example.Menu_Service.DTO;

import java.time.LocalDateTime;
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
public class MenuDTO {

	private Integer id;
	private String name;
	private String description;
	private int price;
	private int restaurantId;
	private LocalDateTime createdAt;
}
