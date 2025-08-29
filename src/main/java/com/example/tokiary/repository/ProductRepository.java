package com.example.tokiary.repository;

import com.example.tokiary.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>  {
    List<Product> findByTitleContainingOrContentContaining(String title, String content);
    Page<Product> findAll(Pageable pageable);
    Page<Product> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);


}
