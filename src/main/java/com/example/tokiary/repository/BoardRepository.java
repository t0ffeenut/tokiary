package com.example.tokiary.repository;

import com.example.tokiary.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    List<Board> findByTitleContainingOrWriterContaining(String title, String writer);
    Page<Board> findAll(Pageable pageable);
    Page<Board> findByTitleContainingOrWriterContaining(String title, String writer, Pageable pageable);
}

