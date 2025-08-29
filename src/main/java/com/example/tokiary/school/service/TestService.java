package com.example.tokiary.school.service;


import com.example.tokiary.school.dto.TestDTO;
import com.example.tokiary.school.entity.Test;
import com.example.tokiary.school.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;

    public List<TestDTO>getlist(){
        return testRepository.findAll()
                .stream()
                .map(Test::toDTO)
                .collect(Collectors.toList());
    }
    public Optional<TestDTO>getSelectOne(Long id){
        return testRepository.findById(id)
                .map(Test::toDTO);
    }
    public void setInsert(TestDTO dto){
        Test test = Test.fromDTO(dto);
        testRepository.save(test);
    }
    public void setUpdate(TestDTO dto){
        Test test = Test.fromDTO(dto);
        testRepository.save(test);
    }
    public void setDelte(Long id){
        testRepository.deleteById(id);
    }

}
