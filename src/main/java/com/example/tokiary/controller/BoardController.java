package com.example.tokiary.controller;

import com.example.tokiary.entity.Board;
import com.example.tokiary.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    // 목록
    @GetMapping("/list")
    public String list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword,
            Model model,
            HttpServletRequest request
    ) {
        // 🔒 세션 체크 임시 해제
        /*
        HttpSession session =request.getSession(false);
        if(session == null || session.getAttribute("loginId") == null){
            String redirectURL = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
            return "redirect:/login?redirectURL=" + URLEncoder.encode(redirectURL, StandardCharsets.UTF_8);
        }
        */

        Page<Board> result = (keyword != null && !keyword.isEmpty())
                ? boardService.searchPage(keyword, page, size)
                : boardService.getPageAll(page, size);

        model.addAttribute("result", result);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "board/list";
    }

    // 상세 보기
    @GetMapping("/view/{id}")
    public String view(
            @PathVariable int id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String keyword,
            Model model,
            HttpServletRequest request
    ) {
        // 🔒 세션 체크 임시 해제
        /*
        HttpSession session =request.getSession(false);
        if(session == null || session.getAttribute("loginId") == null){
            String redirectURL = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
            return "redirect:/login?redirectURL=" + URLEncoder.encode(redirectURL, StandardCharsets.UTF_8);
        }
        */

        Board board = boardService.getSelectOne(id).orElse(null);
        model.addAttribute("board", board);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "board/view";
    }

    // 글 작성 폼
    @GetMapping("/chuga")
    public String chuga(Model model, HttpServletRequest request) {
        // 🔒 세션 체크 임시 해제
        /*
        HttpSession session =request.getSession(false);
        if(session == null || session.getAttribute("loginId") == null){
            String redirectURL = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
            return "redirect:/login?redirectURL=" + URLEncoder.encode(redirectURL, StandardCharsets.UTF_8);
        }
        */

        model.addAttribute("board", new Board());
        return "board/chuga";
    }

    // 글 작성 처리
    @PostMapping("/chugaProc")
    public String chugaProc(Board board,
                            @RequestParam(required = false) String keyword,
                            RedirectAttributes redirectAttributes, HttpServletRequest request) {
        // 🔒 세션 체크 임시 해제
        /*
        HttpSession session =request.getSession(false);
        if(session == null || session.getAttribute("loginId") == null){
            String redirectURL = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
            return "redirect:/login?redirectURL=" + URLEncoder.encode(redirectURL, StandardCharsets.UTF_8);
        }
        */

        boardService.setInsert(board);

        if (keyword != null && !keyword.isEmpty()) {
            redirectAttributes.addAttribute("keyword", keyword);
        }
        return "redirect:/board/list";
    }

    // 글 수정 폼
    @GetMapping("/sujung/{id}")
    public String sujung(@PathVariable int id,
                         @RequestParam(defaultValue = "0") int page,
                         @RequestParam(defaultValue = "10") int size,
                         @RequestParam(required = false) String keyword,
                         Model model,
                         HttpServletRequest request) {
        // 🔒 세션 체크 임시 해제
        /*
        HttpSession session =request.getSession(false);
        if(session == null || session.getAttribute("loginId") == null){
            String redirectURL = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
            return "redirect:/login?redirectURL=" + URLEncoder.encode(redirectURL, StandardCharsets.UTF_8);
        }
        */

        Board board = boardService.getSelectOne(id).orElse(null);
        model.addAttribute("board", board);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "board/sujung";
    }

    // 글 수정 처리
    @PostMapping("/sujungProc")
    public String sujungProc(Board board,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "10") int size,
                             @RequestParam(required = false) String keyword,
                             RedirectAttributes redirectAttributes, HttpServletRequest request) {
        // 🔒 세션 체크 임시 해제
        /*
        HttpSession session =request.getSession(false);
        if(session == null || session.getAttribute("loginId") == null){
            String redirectURL = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
            return "redirect:/login?redirectURL=" + URLEncoder.encode(redirectURL, StandardCharsets.UTF_8);
        }
        */

        boardService.setUpdate(board);

        redirectAttributes.addAttribute("page", page);
        redirectAttributes.addAttribute("size", size);
        if (keyword != null && !keyword.isBlank()) {
            redirectAttributes.addAttribute("keyword", keyword);
        }
        return "redirect:/board/view/" + board.getId();
    }

    // 글 삭제 폼
    @GetMapping("/sakje/{id}")
    public String sakje(@PathVariable int id,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "10") int size,
                        @RequestParam(required = false) String keyword,
                        Model model,
                        HttpServletRequest request) {
        // 🔒 세션 체크 임시 해제
        /*
        HttpSession session =request.getSession(false);
        if(session == null || session.getAttribute("loginId") == null){
            String redirectURL = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
            return "redirect:/login?redirectURL=" + URLEncoder.encode(redirectURL, StandardCharsets.UTF_8);
        }
        */

        Board board = boardService.getSelectOne(id).orElse(null);
        model.addAttribute("board", board);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "board/sakje";
    }

    // 글 삭제 처리
    @PostMapping("/sakjeProc")
    public String sakjeProc(@RequestParam int id,
                            @RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "10") int size,
                            @RequestParam(required = false) String keyword,
                            RedirectAttributes redirectAttributes, HttpServletRequest request) {
        // 🔒 세션 체크 임시 해제
        /*
        HttpSession session =request.getSession(false);
        if(session == null || session.getAttribute("loginId") == null){
            String redirectURL = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
            return "redirect:/login?redirectURL=" + URLEncoder.encode(redirectURL, StandardCharsets.UTF_8);
        }
        */

        boardService.setDelete(id);

        redirectAttributes.addAttribute("page", page);
        redirectAttributes.addAttribute("size", size);
        if (keyword != null && !keyword.isBlank()) {
            redirectAttributes.addAttribute("keyword", keyword);
        }
        return "redirect:/board/list";
    }
}
