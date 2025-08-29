package com.example.tokiary.school.repository;

import com.example.tokiary.school.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    Test findByTestNo(String TestNo);

    List<Test> findByTestName(String TestName);
}
