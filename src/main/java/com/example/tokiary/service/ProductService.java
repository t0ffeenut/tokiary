package com.example.tokiary.service;


import com.example.tokiary.entity.Board;
import com.example.tokiary.entity.Product;
import com.example.tokiary.repository.ProductRepository;
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
public class ProductService {

    private final ProductRepository productRepository;

    // 전체 조회
    public List<Product> getSelectAll() {
        return productRepository.findAll();
    }

    // 등록
    public void setInsert(Product product) {
        productRepository.save(product);
    }

    // 수정
    public void setUpdate(Product product) {
        productRepository.save(product);
    }

    // 삭제
    public void setDelete(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> search(String keyword) {
        return productRepository.findByTitleContainingOrContentContaining(keyword, keyword);
    }

    public Page<Product> getPageAll(int  page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return productRepository.findAll(pageable);
    }

    public Page<Product> searchPage(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return productRepository.findByTitleContainingOrContentContaining(keyword, keyword, pageable);
    }


    public Optional<Product> getSelectOne(int id) {
        return productRepository.findById(id);
    }
}
