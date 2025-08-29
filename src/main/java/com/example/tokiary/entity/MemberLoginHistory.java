package com.example.tokiary.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_login_history")
@Getter
@Setter
public class MemberLoginHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String memberId;

    private String result;            // SUCCESS / FAILURE
    private LocalDateTime loginTime;  // 로그인 시각
    private String ipAddress;
    private String userAgentHash;
    private String redirectUrl;
}


