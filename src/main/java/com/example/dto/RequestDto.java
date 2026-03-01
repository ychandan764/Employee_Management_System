package com.example.dto;

import com.example.enums.Department;
import com.example.enums.Location;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
	
	@NotBlank(message="Name cannot be Blank")
	@Size(max = 50)
	private String firstName;
	
	@NotNull(message="Department cannot be Null")
	private Department department;
	
	@NotNull(message="Location cannot be Null")
	private Location location;
	
	@NotNull(message="Salary cannot be Null")
	@Positive(message = "Salary must be greater than zero")
	private Double salary;
	
	

}
