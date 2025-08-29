package com.example.tokiary.controller;

import com.example.tokiary.dto.MemberDTO;
import com.example.tokiary.service.JoinService;
import com.example.tokiary.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;


    @Controller
    @RequiredArgsConstructor
    @RequestMapping("/join")

    public class JoinController {

        private final JoinService joinService;

        // 글 작성 폼
        @GetMapping("/chuga")
        public String chuga(Model model) {

            model.addAttribute("member", new MemberDTO());
            return "join/chuga";
        }

        // 글 작성 처리
        @PostMapping("/chugaProc")
        public String chugaProc(@ModelAttribute MemberDTO memberDTO,
                                @RequestParam(required = false) String keyword,
                                RedirectAttributes redirectAttributes,
                                HttpServletRequest request) {


            // ⭐ 1. 아이디 중복 확인
            if (joinService.existsByMemberId(memberDTO.getMemberId())) {
                redirectAttributes.addFlashAttribute("error", "이미 사용 중인 아이디입니다.");
                return "redirect:/join/chuga";
            }

            // ⭐ 2. 저장

            MemberDTO savedMember = joinService.setInsert(memberDTO);

            // 세션 저장 (자동 로그인 효과)
            HttpSession session = request.getSession();
            session.setAttribute("id", savedMember.getId());           // DB PK
            session.setAttribute("loginId", savedMember.getMemberId()); // ✅ loginId로 통일
            session.setAttribute("loginType", "MEMBER");                // ✅ 타입도 같이 저장
            session.setAttribute("memberRank", savedMember.getMemberRank());


            if (keyword != null && !keyword.isEmpty()) {
                redirectAttributes.addAttribute("keyword", keyword);
            }

            return "redirect:/home";
        }

        // 글 수정 폼
        @GetMapping("/sujung/{id}")
        public String sujung(
                @PathVariable Long id,
                @RequestParam(defaultValue = "0")int page,
                @RequestParam(defaultValue = "10")int size,
                @RequestParam(required = false) String keyword,
                Model model,
                HttpServletRequest request){

            // ✅ 로그인 체크
            HttpSession session =request.getSession(false);
            if(session == null || session.getAttribute("loginId") == null){
                String redirectURL = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
                return "redirect:/login?redirectURL=" + URLEncoder.encode(redirectURL, StandardCharsets.UTF_8);
            }

            MemberDTO member = joinService.getSelectOne(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "회원 없음: " + id));
            model.addAttribute("member", member);
            model.addAttribute("keyword", keyword);
            model.addAttribute("page", page);
            model.addAttribute("size", size);
            return "join/sujung";
        }

        // 글 수정 처리
        @PostMapping("/sujungProc")
        public String sujungProc(@ModelAttribute MemberDTO memberDTO,

                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size,
                                 @RequestParam(required = false) String keyword,
                                 RedirectAttributes redirectAttributes,
                                 HttpServletRequest request) {

            // ✅ 로그인 체크
            HttpSession session =request.getSession(false);
            if(session == null || session.getAttribute("loginId") == null){
                String redirectURL = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
                return "redirect:/login?redirectURL=" + URLEncoder.encode(redirectURL, StandardCharsets.UTF_8);
            }


            joinService.setUpdate(memberDTO);

            redirectAttributes.addAttribute("page", page);
            redirectAttributes.addAttribute("size", size);
            if (keyword != null && !keyword.isBlank()) {
                redirectAttributes.addAttribute("keyword", keyword);
            }

            return "redirect:/join/sujung/" + memberDTO.getId();

        }

        // 글 삭제 폼
        @GetMapping("/sakje/{id}")
        public String sakje(
                @PathVariable Long id,
                @RequestParam(defaultValue = "0") int page,
                @RequestParam(defaultValue = "10") int size,
                @RequestParam(required = false) String keyword,
                Model model,
                HttpServletRequest request) {

            // ✅ 로그인 체크
            HttpSession session =request.getSession(false);
            if(session == null || session.getAttribute("loginId") == null){
                String redirectURL = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
                return "redirect:/login?redirectURL=" + URLEncoder.encode(redirectURL, StandardCharsets.UTF_8);
            }

            MemberDTO member = joinService.getSelectOne(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "회원 없음: " + id));

            model.addAttribute("member", member);
            model.addAttribute("keyword", keyword);
            model.addAttribute("page", page);
            model.addAttribute("size", size);
            return "join/sakje";
        }

        // 글 삭제 처리
        @PostMapping("/sakjeProc")
        public String sakjeProc(@ModelAttribute MemberDTO memberDTO,

                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "10") int size,
                                @RequestParam(required = false) String keyword,
                                RedirectAttributes redirectAttributes,
                                HttpServletRequest request) {

            // ✅ 로그인 체크
            HttpSession session =request.getSession(false);
            if(session == null || session.getAttribute("loginId") == null){
                String redirectURL = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
                return "redirect:/login?redirectURL=" + URLEncoder.encode(redirectURL, StandardCharsets.UTF_8);
            }

            joinService.setDeleteByMemberId(memberDTO.getId());
            session.invalidate();
            redirectAttributes.addAttribute("page", page);
            redirectAttributes.addAttribute("size", size);
            if (keyword != null && !keyword.isBlank())
                redirectAttributes.addAttribute("keyword", keyword);

            return "redirect:/home";
        }
}
