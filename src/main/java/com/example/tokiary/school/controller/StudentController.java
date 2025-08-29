package com.example.tokiary.school.controller;

import com.example.tokiary.school.dto.StudentDTO;
import com.example.tokiary.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    // ✅ 학생 목록
    @GetMapping("/list")
    public String list(Model model) {
        List<StudentDTO> result = studentService.getList();
        model.addAttribute("result", result);
        return "school/student/list";   // templates/school/student/list.html
    }

    // ✅ 학생 상세보기
    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id, Model model) {
        StudentDTO student = studentService.getSelectOne(id).orElse(null);
        model.addAttribute("student", student);
        return "school/student/view";   // templates/school/student/view.html
    }

    // ✅ 등록 폼
    @GetMapping("/chuga")
    public String chuga(Model model) {
        model.addAttribute("student", new StudentDTO());
        return "school/student/chuga";  // templates/school/student/chuga.html
    }

    // ✅ 등록 처리
    @PostMapping("/chugaProc")
    public String chugaProc(@ModelAttribute StudentDTO dto) {
        studentService.setInsert(dto);
        return "redirect:/student/list";   // ✅ 절대경로로 수정
    }

    // ✅ 수정 폼
    @GetMapping("/sujung/{id}")
    public String sujung(@PathVariable Long id, Model model) {
        StudentDTO student = studentService.getSelectOne(id).orElse(null);
        model.addAttribute("student", student);
        return "school/student/sujung";  // templates/school/student/sujung.html
    }

    // ✅ 수정 처리
    @PostMapping("/sujungProc")
    public String sujungProc(@ModelAttribute StudentDTO dto) {
        studentService.setUpdate(dto);
        return "redirect:/student/view/" + dto.getId();   // ✅ 절대경로로 수정
    }

    // ✅ 삭제 확인 페이지
    @GetMapping("/sakje/{id}")
    public String sakje(@PathVariable Long id, Model model) {
        StudentDTO student = studentService.getSelectOne(id).orElse(null);
        model.addAttribute("student", student);
        return "school/student/sakje";  // templates/school/student/sakje.html
    }

    // ✅ 삭제 처리
    @PostMapping("/sakjeProc")
    public String sakjeProc(@RequestParam Long id) {
        studentService.setDelete(id);
        return "redirect:/student/list";   // ✅ 절대경로로 수정
    }
}
