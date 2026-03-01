package com.example.repository;

import com.example.entity.Employee;
import com.example.enums.Department;
import com.example.enums.Location;

import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification {

    public static Specification<Employee> hasDepartment(Department department) {
        return (root, query, cb) ->
                department == null ? null :
                cb.equal(root.get("department"), department);
    }

    public static Specification<Employee> hasLocation(Location location) {
        return (root, query, cb) ->
                location == null ? null :
                cb.equal(root.get("location"), location);
    }

    public static Specification<Employee> firstNameContains(String search) {
        return (root, query, cb) ->
                (search == null || search.isBlank()) ? null :
                cb.like(cb.lower(root.get("firstName")),
                        "%" + search.toLowerCase() + "%");
    }
}