package com.example.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.enums.Department;
import com.example.enums.Location;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="first_name",nullable = false)
	private String firstName;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Department department;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Location location;
	
	@Column(nullable = false)
	private Double salary;
	
	@CreatedDate
	@Column(nullable = false,updatable = false)
	private LocalDateTime joiningDate;
	
	@LastModifiedDate
	@Column(nullable = false)
	private LocalDateTime updatedAt;

}

 
