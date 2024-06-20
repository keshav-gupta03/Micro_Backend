package com.example.Menu_Service.DTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO {

	private int id;
    private String name;
    private String description;
    private String location;
    private long contactNumber;
    private LocalDateTime createdAt;
}
