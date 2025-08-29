package com.example.tokiary.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDTO {

    private int id;
    private Integer no;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime createDate;
}
