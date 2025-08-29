package com.example.tokiary.school.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestDTO {

    private Long id;            // 시험 번호 (PK)
    private String TestNo;
    private String testName;    // 시험 이름 (예: 중간고사, 기말고사)
    private String subject;     // 과목 (예: 국어, 영어, 수학)
    private String gradeLevel;  // 학년 (1,2,3학년)
    private String classNo;     // 반 (1반, 2반, ...)
    private LocalDate testDate; // 시험 날짜
    private LocalDateTime createDate;  // 등록일


}
