package com.example.tokiary.entity;

import com.example.tokiary.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기술적 pk

    @Column(length = 30, nullable = false, unique = true)
    private String memberId;

    @Column(length = 50, nullable = false)
    private String memberNickname;       // 회원 닉네임

    @Column(length = 100, nullable = false)
    private String memberPw;     // 회원 비밀번호

    @Column(length = 20, nullable = false)
    private String memberName;   // 회원 이름

    private LocalDate memberBirth; // 회원 생년월일

    @Column(length = 50)
    private String memberEmail;  // 회원 이메일

    @Column(length = 255)
    private String memberAdd;    // 회원 주소

    @Column(length = 20)
    private String memberPhone;  // 회원 전화번호

    @Column(length = 20)
    private String memberRank;   // 회원 등급

    private Integer memberPoint; // 회원 포인트


    @CreationTimestamp
    @Column(name = "createDate", updatable = false)
    private LocalDateTime createDate; // 가입일 (자동 생성)


    // ✅ DTO → Entity 변환
    public static Member fromDTO(MemberDTO dto) {
        Member member = new Member();
        if (dto.getId() != null) member.setId(dto.getId());
        member.setMemberId(dto.getMemberId());
        member.setMemberPw(dto.getMemberPw());
        member.setMemberNickname(dto.getMemberNickname());
        member.setMemberName(dto.getMemberName());
        member.setMemberBirth(dto.getMemberBirth());
        member.setMemberEmail(dto.getMemberEmail());
        member.setMemberAdd(dto.getMemberAdd());
        member.setMemberPhone(dto.getMemberPhone());
        member.setMemberRank(dto.getMemberRank());
        member.setMemberPoint(dto.getMemberPoint());
        return member;
    }

    // ✅ Entity → DTO 변환
    public MemberDTO toDTO() {
        return new MemberDTO(
                this.id,
                this.memberId,
                this.memberNickname,
                this.memberPw,
                this.memberName,
                this.memberBirth,
                this.memberEmail,
                this.memberAdd,
                this.memberPhone,
                this.memberRank,
                this.memberPoint,
                this.createDate
        );
    }

}
