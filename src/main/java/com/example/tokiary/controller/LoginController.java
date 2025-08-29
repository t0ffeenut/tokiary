package com.example.tokiary.controller;


import com.example.tokiary.dto.LoginDTO;
import com.example.tokiary.entity.Member;
import com.example.tokiary.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;


    /**
     * 로그인 페이지 (GET /login)
     */
    @GetMapping("")
    public String loginPage(Model model) {
        if (!model.containsAttribute("loginRequest")) {
            model.addAttribute("loginRequest", new LoginDTO()); // userId, userPw
        }
        model.addAttribute("loginType", "session-login");
        model.addAttribute("pageName", "로그인");
        return "login/login"; // templates/login.html
    }

    /**
     * 로그인 처리 (POST /login/loginProc)
     */
    @PostMapping("/loginProc")
    public String loginProc(@ModelAttribute("loginRequest") LoginDTO loginRequest,
                            HttpServletRequest request,
                            @RequestParam(defaultValue = "/home") String redirectURL,
                            RedirectAttributes redirectAttributes) {

        if (loginRequest.getUserId() == null || loginRequest.getUserId().isBlank()
                || loginRequest.getUserPw() == null || loginRequest.getUserPw().isBlank()) {
            redirectAttributes.addFlashAttribute("msg", "아이디와 비밀번호를 입력하세요.");
            return "redirect:/login";
        }

        // ✅ Member 로그인 시도
        else if (loginService.authenticateMember(loginRequest.getUserId(), loginRequest.getUserPw(), request)) {
            // 🔽 여기서 직접 세션 발급
            HttpSession old = request.getSession(false);
            if (old != null) old.invalidate();

            HttpSession session = request.getSession(true);
            Member member = loginService.getMemberById(loginRequest.getUserId());
            session.setAttribute("id", member.getId());
            session.setAttribute("loginId", member.getMemberId());
            session.setAttribute("memberRank", member.getMemberRank());
            session.setAttribute("loginType", "MEMBER");
            session.setMaxInactiveInterval(1800);
        }
        else {
            redirectAttributes.addFlashAttribute("msg", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "redirect:/login";
        }

        return "redirect:" + redirectURL;
    }



    /** 로그아웃 (GET /login/logout) */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return "redirect:/login"; // 로그아웃 후 로그인 페이지로 이동
    }


}
