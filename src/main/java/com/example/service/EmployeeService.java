package com.example.service;


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import com.example.dto.PatchDto;
import com.example.dto.RequestDto;
import com.example.dto.ResponseDto;
import com.example.entity.Employee;
import com.example.enums.Department;
import com.example.enums.Location;
import com.example.exception.EmployeeNotFoundException;
import com.example.mapper.EmployeeMapper;
import com.example.repository.EmployeeRepository;
import com.example.repository.EmployeeSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	private final EmployeeMapper mapper;
	

	public Page<ResponseDto> fetchAllEmployee(
			int pageNo,
	        int pageSize,
	        String sortBy,
	        String sortDir,
	        Department department,
	        Location location,
	        String search) {
		
		Sort sort = sortDir.equalsIgnoreCase("asc")
	            ? Sort.by(sortBy).ascending()
	            : Sort.by(sortBy).descending();
		
		 Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		 
		 Specification<Employee> spec = Specification
		            .where(EmployeeSpecification.hasDepartment(department))
		            .or(EmployeeSpecification.hasLocation(location))
		            .and(EmployeeSpecification.firstNameContains(search));
		 
		Page<Employee> employee = employeeRepository.findAll(spec,pageable);
		
//		if(employee.isEmpty()) {
//			throw new EmployeeNotFoundException(id);
//		}
		
		return employee.map(EmployeeMapper::toDto);
	}

	public ResponseDto add(RequestDto dto) {
		 Employee employee = EmployeeMapper.toEntity(dto);
		 employeeRepository.save(employee);
		return EmployeeMapper.toDto(employee);
	}

	public ResponseDto updateEmployee(Long id, RequestDto dto) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException(id));
	 
		EmployeeMapper.updateEntity(employee, dto);
		
		Employee updatedemployee = employeeRepository.save(employee);
		return EmployeeMapper.toDto(updatedemployee);
	}
	
	public ResponseDto patchEmployee(Long id, PatchDto dto) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException(id));
	 
		EmployeeMapper.patchEntity(employee, dto);
		
		Employee patchedemployee = employeeRepository.save(employee);
		return EmployeeMapper.toDto(patchedemployee);
	}
 
}
