package com.example.tokiary.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column (length = 20)
    private String title;
    @Column (length = 20)
    private String price;
    @Column (length = 255)
    private String content;
    @Column(length = 50)
    private String writer; // 글쓴이


    @CreationTimestamp
    @Column(name = "createDate" , updatable = false) //날짜 수정못하게
    private LocalDateTime createDate;



}
