package com.example.tokiary.school.entity;

import com.example.tokiary.school.dto.StudentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동증가
    private Long id;  // 자동적으로 PK 순서가 붙음.

    @Column(length = 20, nullable = false, unique = true)
    private String studentNo;  // 학번

    @Column(length = 50, nullable = false)
    private String studentName;  // 이름

    private LocalDate studentBirth;  // 생년월일

    @Column(length = 1)
    private String studentGender;  // 성별 (M/F)

    @Column(nullable = false)
    private Integer gradeLevel;  // 학년 (1~3)

    @Column(nullable = false)
    private Integer classNo;  // 반

    @Column(nullable = false)
    private Integer studentNumber;  // 번호

    @Column(length = 20)
    private String studentPhone;  // 연락처

    @Column(length = 255)
    private String studentAddress;  // 주소

    @CreationTimestamp
    private LocalDateTime createDate;  // 등록일


    public StudentDTO toDTO() {
        return new StudentDTO(
                this.id,
                this.studentNo,
                this.studentName,
                this.studentBirth,
                this.studentGender,
                this.gradeLevel,
                this.classNo,
                this.studentNumber,
                this.studentPhone,
                this.studentAddress,
                this.createDate
        );
    }
    // Student.java 안에 추가
    public static Student fromDTO(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setStudentNo(dto.getStudentNo());
        student.setStudentName(dto.getStudentName());
        student.setStudentBirth(dto.getStudentBirth());
        student.setStudentGender(dto.getStudentGender());
        student.setGradeLevel(dto.getGradeLevel());
        student.setClassNo(dto.getClassNo());
        student.setStudentNumber(dto.getStudentNumber());
        student.setStudentPhone(dto.getStudentPhone());
        student.setStudentAddress(dto.getStudentAddress());
        student.setCreateDate(dto.getCreateDate()); // ✅ 추가
        return student;
    }


}
