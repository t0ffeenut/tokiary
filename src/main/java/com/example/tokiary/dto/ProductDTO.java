package com.example.tokiary.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class ProductDTO {



    private int id;
    private Integer no;
    private String title;
    private String price;
    private String content;
    private LocalDateTime createDate;
    private String writer; // 글쓴이

}
