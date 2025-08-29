package com.example.tokiary.service;

import com.example.tokiary.dto.MemberDTO;
import com.example.tokiary.entity.Member;
import com.example.tokiary.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class MemberService {

    private final MemberRepository memberRepository;

    // 전체 조회
    public List<MemberDTO> getSelectAll() {
        return memberRepository.findAll()
                .stream()
                .map(Member::toDTO)   // 엔티티 → DTO
                .toList();
    }


    // 등록
    public MemberDTO setInsert(MemberDTO dto) {
        if (dto.getMemberRank() == null) {
            dto.setMemberRank("1"); // ⭐ 일반회원
        }

        // DTO → Entity 변환
        Member entity =  Member.fromDTO(dto);

        // 저장
        Member savedEntity = memberRepository.save(entity);

        // Entity → DTO 변환
        return savedEntity.toDTO();

    }

    // 수정
    public MemberDTO setUpdate(MemberDTO dto) {
        // DTO → Entity 변환
        Member entity = Member.fromDTO(dto);

        // 저장
        Member savedEntity = memberRepository.save(entity);

        // Entity → DTO 변환
        return savedEntity.toDTO();

    }

    // 삭제
    public void setDeleteByMemberId(Long id) {
        memberRepository.deleteById(id);
    }

    //검색페이지
    public Page<MemberDTO> searchPage(String keyword, int page, int size) {
        Pageable pageable =  PageRequest.of(page, size, Sort.by("id").descending());
        return memberRepository
                .findByMemberIdContainingOrMemberNicknameContaining(keyword, keyword, pageable)
                .map(Member::toDTO);
    }
    //전체페이지
    public Page<MemberDTO> getPageAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return memberRepository.findAll(pageable)
                .map(Member::toDTO);
    }


    // 아이디로 단건조회
    public Optional<MemberDTO> getSelectOne(Long id) {
        return memberRepository.findById(id)
                .map(Member::toDTO);
    }

    // 유저아이디로 조회
    /** 로그인: 성공 시 Member, 실패 시 null 반환(최소 구현) */
    public Member authenticate(String memberId, String rawPw) {
        return memberRepository.findByMemberId(memberId)
                .filter(member -> Objects.equals(member.getMemberPw(), rawPw))   // 평문 비교 (최소 버전)
                .orElse(null);
    }

    /** 비회원 차단용 */
    public boolean existsByMemberId(String memberId) {
        return memberRepository.existsByMemberId(memberId);
    }


}
