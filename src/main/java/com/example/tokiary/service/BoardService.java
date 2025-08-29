package com.example.tokiary.service;

import com.example.tokiary.entity.Board;
import com.example.tokiary.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

   // public List<Board> getSelectAll() {
   //     return boardRepository.findAll();
   // }
    public void setInsert(Board board) {
        boardRepository.save(board);
    }

    public void setUpdate(Board board) {
        boardRepository.save(board);
    }

    public void setDelete(int id) {
        boardRepository.deleteById(id);
    }


    public List<Board> search(String keyword) {
        return boardRepository.findByTitleContainingOrWriterContaining(keyword, keyword);
    }

    public Page<Board> getPageAll(int  page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return boardRepository.findAll(pageable);
    }


    public Page<Board> searchPage(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return boardRepository.findByTitleContainingOrWriterContaining(keyword, keyword, pageable);
    }

    public Optional<Board> getSelectOne(int id) {
        return boardRepository.findById(id);
    }
}
