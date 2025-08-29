package com.example.tokiary.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class Board {



    @Id //id = pk 이다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //no 자동증가
    private int id;

    @Column(length = 20)
    private String title;

    @Column(length = 10)
    private String writer;

    @Column(length = 255) // 컬럼이니까 컬럼 어노테이션 붙여줘
    private String content;

    @CreationTimestamp
    @Column(name = "createDate" , updatable = false) //날짜 수정못하게
    private LocalDateTime createDate;


}
