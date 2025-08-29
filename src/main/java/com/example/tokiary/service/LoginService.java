package com.example.tokiary.service;

import com.example.tokiary.entity.MemberLoginHistory;
import com.example.tokiary.entity.Member;
import com.example.tokiary.repository.MemberLoginRepository;
import com.example.tokiary.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final MemberLoginRepository memberLoginRepository;

    public Member getMemberById(String memberId) {
        return memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new RuntimeException("회원 없음"));
    }

    /**
     * 로그인 검증 + 기록 남기기
     */
    public boolean authenticateMember(String memberId, String memberPw, HttpServletRequest request) {
        Optional<Member> memberOpt = memberRepository.findByMemberId(memberId);
        if (memberOpt.isPresent()) {
            Member member = memberOpt.get();
            boolean success = memberPw.equals(member.getMemberPw());
            recordLoginAttempt(member, success ? "SUCCESS" : "FAILURE", request);
            return success;
        }
        return false;
    }

    /**
     * 로그인 기록 저장
     */
    private void recordLoginAttempt(Member member, String result, HttpServletRequest request) {
        MemberLoginHistory history = new MemberLoginHistory();

        history.setMemberId(member.getMemberId());
        history.setResult(result);
        history.setLoginTime(LocalDateTime.now());
        history.setIpAddress(request.getRemoteAddr());
        history.setRedirectUrl(request.getRequestURI());

        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null) {
            history.setUserAgentHash(hashUa(userAgent));
        } else {
            history.setUserAgentHash("UNKNOWN");
        }

        memberLoginRepository.save(history);
    }

    /**
     * 세션 무효화 (로그아웃)
     */
    public void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
    }

    /**
     * User-Agent 해시 함수
     */
    private String hashUa(String userAgent) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(userAgent.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                sb.append(String.format("%02x", hash[i]));
            }
            return sb.toString();
        } catch (Exception e) {
            return "UNKNOWN";
        }
    }
}


