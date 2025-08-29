package com.example.tokiary.school.controller;

import com.example.tokiary.school.dto.TestDTO;
import com.example.tokiary.school.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class TestController {

    private final TestService testService;

    @GetMapping("/list")
    public String list(Model model){
        List<TestDTO> result = testService.getlist();
        model.addAttribute("result,result");
        return "school/test/list";
    }

    @GetMapping("/view/{id}")
    public String view (@PathVariable Long id,Model model){
        TestDTO test = testService.getSelectOne(id).orElse(null);
        model.addAttribute("test,test");
        return "school/test/view";
    }

    @GetMapping("/chuga")
    public String chuga (Model model){
        model.addAttribute("test" , new TestDTO());
        return "school/test/chuga";
    }

    @PostMapping("/chugaProc")
    public String chugaProc(@ModelAttribute TestDTO dto){
        testService.setInsert(dto);
        return "school/test/list";
    }

    @GetMapping("/sujung{id}")
    public String sujung (@PathVariable Long id, Model model){
        TestDTO test = testService.getSelectOne(id).orElse(null);
        model.addAttribute("test,test");
        return  "school/test/sujung";
    }
    @PostMapping("/sujungProc")
    public String sujungProc(@ModelAttribute TestDTO dto){
        testService.setUpdate(dto);
        return "school/test/list" + dto.getId();
    }

    @GetMapping("/sakje{id}")
    public String sakje (@PathVariable Long id, Model model){
        TestDTO test = testService.getSelectOne(id).orElse(null);
        model.addAttribute("test,test");
        return "school/test/sakje";
    }
    @PostMapping("/sakjeProc")
    public String sakjeProc (@RequestParam Long id){
        testService.setDelte(id);
        return "school/test/list";
    }
}
