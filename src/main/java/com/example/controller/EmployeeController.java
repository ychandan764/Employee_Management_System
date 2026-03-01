package com.example.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.dto.PatchDto;
import com.example.dto.RequestDto;
import com.example.dto.ResponseDto;
import com.example.enums.Department;
import com.example.enums.Location;
import com.example.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
	
	private final EmployeeService employeeService;


	@GetMapping("/")
	public ResponseEntity<Page<ResponseDto>> fetchAllEmployee(
			  @RequestParam(defaultValue = "0") int pageNo,
		        @RequestParam(defaultValue = "5") int pageSize,
		        @RequestParam(defaultValue = "id") String sortBy,
		        @RequestParam(defaultValue = "asc") String sortDir,
		        
		        @RequestParam(required = false) Department department,
		        @RequestParam(required = false) Location location,
		        @RequestParam(required = false) String search) {
		
		Page<ResponseDto> response = employeeService.fetchAllEmployee(pageNo, pageSize, sortBy, sortDir, department,location,search);
		return ResponseEntity.ok(response);
	}
	

	@PostMapping("/")
	public ResponseEntity<ResponseDto> addEmployee(@RequestBody @Valid RequestDto dto){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(employeeService.add(dto));
	}
	

	@PutMapping("/{id}")
	public ResponseEntity<ResponseDto> updateEmployee(@PathVariable Long id, @RequestBody @Valid RequestDto dto){
		return ResponseEntity.status(HttpStatus.OK)
				.body(employeeService.updateEmployee(id,dto));	
	}
	
	
	@PatchMapping("/{id}")
	public ResponseEntity<ResponseDto> patchEmployee(@PathVariable Long id, @RequestBody @Valid PatchDto dto){
		return ResponseEntity.status(HttpStatus.OK)
				.body(employeeService.patchEmployee(id,dto));
	
	
}}
