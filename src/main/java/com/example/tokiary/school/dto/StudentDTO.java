package com.example.tokiary.school.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String studentNo;
    private String studentName;
    private LocalDate studentBirth;
    private String studentGender;
    private Integer gradeLevel;
    private Integer classNo;
    private Integer studentNumber;
    private String studentPhone;
    private String studentAddress;
    private LocalDateTime createDate;
}
