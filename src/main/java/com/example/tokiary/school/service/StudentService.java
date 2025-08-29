package com.example.tokiary.school.service;

import com.example.tokiary.school.dto.StudentDTO;
import com.example.tokiary.school.entity.Student;
import com.example.tokiary.school.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    // 1. 전체 목록 조회
    public List<StudentDTO> getList() {
        return studentRepository.findAll()
                .stream()
                .map(Student::toDTO)
                .collect(Collectors.toList());
    }

    // 2. 단일 조회
    public Optional<StudentDTO> getSelectOne(Long id) {
        return studentRepository.findById(id)
                .map(Student::toDTO);
    }

    // 3. 등록
    public void setInsert(StudentDTO dto) {
        Student student = Student.fromDTO(dto);
        studentRepository.save(student);
    }

    // 4. 수정
    public void setUpdate(StudentDTO dto) {
        Student student = Student.fromDTO(dto);
        studentRepository.save(student);
    }

    // 5. 삭제
    public void setDelete(Long id) {
        studentRepository.deleteById(id);
    }



}
