package com.example.tokiary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class MemberDTO {

        private Long id;
        private String memberId;     // 회원 아이디
        private String memberNickname;       // 회원 닉네임
        private String memberPw;     // 회원 비밀번호
        private String memberName;   // 회원 이름
        private LocalDate memberBirth; // 회원 생년월일
        private String memberEmail;  // 회원 이메일
        private String memberAdd;    // 회원 주소
        private String memberPhone;  // 회원 전화번호
        private String memberRank;   // 회원 등급
        private Integer memberPoint; // 회원 포인트
        private LocalDateTime createDate;

}
