package com.example.dto;

import com.example.enums.Department;
import com.example.enums.Location;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatchDto {

	    private String firstName;
	    private Department department;
	    private Location location;
	    private Double salary;
	    
}
