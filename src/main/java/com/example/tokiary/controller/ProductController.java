package com.example.tokiary.controller;

import com.example.tokiary.entity.Product;
import com.example.tokiary.service.ProductService;
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
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("list")
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

        Page<Product> result = (keyword != null && !keyword.isEmpty())
                ? productService.searchPage(keyword, page, size)
                : productService.getPageAll(page, size);

        model.addAttribute("result", result);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("size", size);

        return "product/list";
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

        Product product = productService.getSelectOne(id).orElse(null);
        model.addAttribute("product", product);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "product/view";
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

        model.addAttribute("product", new Product());
        return "product/chuga";
    }

    // 글 작성 처리
    @PostMapping("/chugaProc")
    public String chugaProc(Product product,
                            @RequestParam(required = false) String keyword,
                            RedirectAttributes redirectAttributes,HttpServletRequest request) {
        // 🔒 세션 체크 임시 해제
        /*
        HttpSession session =request.getSession(false);
        if(session == null || session.getAttribute("loginId") == null){
            String redirectURL = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
            return "redirect:/login?redirectURL=" + URLEncoder.encode(redirectURL, StandardCharsets.UTF_8);
        }
        */

        productService.setInsert(product); // 저장

        if (keyword != null && !keyword.isEmpty()) {
            redirectAttributes.addAttribute("keyword", keyword);
        }

        return "redirect:/product/list";
    }

    // 글 수정 폼
    @GetMapping("/sujung/{id}")
    public String sujung(
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

        Product product = productService.getSelectOne(id).orElse(null);
        model.addAttribute("product", product);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "product/sujung";
    }

    // 글 수정 처리
    @PostMapping("/sujungProc")
    public String sujungProc(Product product,
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

        productService.setUpdate(product);

        redirectAttributes.addAttribute("page", page);
        redirectAttributes.addAttribute("size", size);
        if (keyword != null && !keyword.isBlank()) {
            redirectAttributes.addAttribute("keyword", keyword);
        }

        return "redirect:/product/view/" + product.getId();
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

        Product product = productService.getSelectOne(id).orElse(null);
        model.addAttribute("product", product);
        model.addAttribute("keyword", keyword);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        return "product/sakje";
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

        productService.setDelete(id);

        redirectAttributes.addAttribute("page", page);
        redirectAttributes.addAttribute("size", size);
        if (keyword != null && !keyword.isBlank()) {
            redirectAttributes.addAttribute("keyword", keyword);
        }
        return "redirect:/product/list";
    }
}
