package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.entity.Employee;
import com.example.enums.Department;
import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>,JpaSpecificationExecutor<Employee>{
	
List<Employee> findByDepartment(Department department);
List<Employee> findByFirstName(String firstName);
List<Employee> findByDepartmentAndFirstName(Department department,String firstName);
Page<Employee> findAll(Pageable pageable);
 

}
