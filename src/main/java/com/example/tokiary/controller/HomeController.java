package com.example.tokiary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

        @GetMapping("/home")
        public String home() {
            // 로그인 여부 상관없이 그냥 홈 페이지 보여줌
            return "home/home"; // templates/home/home.html
        }
    }


