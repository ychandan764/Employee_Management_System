package com.example.mapper;

import org.springframework.stereotype.Component;

import com.example.dto.PatchDto;
import com.example.dto.RequestDto;
import com.example.dto.ResponseDto;
import com.example.entity.Employee;

@Component
public class EmployeeMapper {
	
	public static Employee toEntity(RequestDto dto) {
		return Employee.builder()
				.firstName(dto.getFirstName())
				.department(dto.getDepartment())
				.location(dto.getLocation())
				.salary(dto.getSalary())
				.build();
	}
	
	public static ResponseDto toDto(Employee emp) {
		return ResponseDto.builder()
				.id(emp.getId())
				.firstName(emp.getFirstName())
				.department(emp.getDepartment())
				.location(emp.getLocation())
				.salary(emp.getSalary())
				.joiningDate(emp.getJoiningDate())
				.updatedAt(emp.getUpdatedAt())
				.build();
	}
	
	public static void updateEntity(Employee emp, RequestDto dto){

	    emp.setFirstName(dto.getFirstName());
	    emp.setDepartment(dto.getDepartment());
	    emp.setLocation(dto.getLocation());
	    emp.setSalary(dto.getSalary());
	}
	
	public static void patchEntity(Employee emp, PatchDto dto) {

	    if (dto.getFirstName() != null) {
	        emp.setFirstName(dto.getFirstName());
	    }

	    if (dto.getDepartment() != null) {
	        emp.setDepartment(dto.getDepartment());
	    }

	    if (dto.getLocation() != null) {
	        emp.setLocation(dto.getLocation());
	    }

	    if (dto.getSalary() != null) {
	        emp.setSalary(dto.getSalary());
	    }
	}

}
