package com.example.tokiary.school.entity;

import com.example.tokiary.school.dto.TestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter

public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동증가
    private Long id;  // 자동적으로 PK 순서가 붙음.
    @Column(length = 20, nullable = false, unique = true)
    private String testNo;
    @Column(length = 20, nullable = false)
    private String testName;    // 시험 이름 (예: 중간고사, 기말고사)
    @Column(length = 50, nullable = false)
    private String subject;     // 과목 (예: 국어, 영어, 수학)
    @Column(length = 50, nullable = false)
    private String gradeLevel;  // 학년 (1,2,3학년)
    @Column(length = 50, nullable = false)
    private String classNo;     // 반 (1반, 2반, ...)
    @Column(length = 20)
    private LocalDate testDate; // 시험 날짜
    @CreationTimestamp
    private LocalDateTime createDate;  // 등록일

    public TestDTO toDTO(){
        return new TestDTO(
                this.id,
                this.testNo,
                this.testName,
                this.subject,
                this.gradeLevel,
                this.classNo,
                this.testDate,
                this.createDate
        );
    }
    public static Test fromDTO(TestDTO dto){
        Test test = new Test();
        test.setId(dto.getId());
        test.setTestNo(dto.getTestNo());
        test.setTestName(dto.getTestName());
        test.setTestDate(dto.getTestDate());
        test.setSubject(dto.getSubject());
        test.setGradeLevel(dto.getGradeLevel());
        test.setClassNo(dto.getClassNo());
        test.setCreateDate(dto.getCreateDate());
        return test;
    }

}
