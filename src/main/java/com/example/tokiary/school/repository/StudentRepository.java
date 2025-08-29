package com.example.tokiary.school.repository;

import com.example.tokiary.school.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // 학번으로 찾기
    Student findByStudentNo(String studentNo);
    // 이름으로 찾기 (동명이인 대비 여러 명 가능)
    List<Student> findByStudentName(String studentName);
    List<Student> findByNameContainingOrGradeLevelContaining(String name, String gradeLevel);
}
