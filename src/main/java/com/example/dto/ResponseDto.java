package com.example.dto;

import java.time.LocalDateTime;

import com.example.enums.Department;
import com.example.enums.Location;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto {

    private Long id;

    private String firstName;

    private Department department;

    private Location location;

    private Double salary;

    private LocalDateTime joiningDate;

    private LocalDateTime updatedAt;
}
