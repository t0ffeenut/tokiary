package com.example.tokiary.service;

import com.example.tokiary.dto.MemberDTO;
import com.example.tokiary.entity.Member;
import com.example.tokiary.repository.JoinRepository;
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
public class JoinService {



        private final JoinRepository joinRepository;

        // 전체 조회
        public List<MemberDTO> getSelectAll() {
            return joinRepository.findAll()
                    .stream()
                    .map(Member::toDTO)   // 엔티티 → DTO
                    .toList();
        }


        // 등록
        public MemberDTO setInsert(MemberDTO dto) {
            // 무조건 등급 기본값 1
            dto.setMemberRank(
                    (dto.getMemberRank() == null || dto.getMemberRank().isBlank())
                            ? "1"
                            : dto.getMemberRank()
            );

            // DTO → Entity 변환
            Member entity =  Member.fromDTO(dto);

            // 저장
            Member savedEntity = joinRepository.save(entity);

            // Entity → DTO 변환
            return savedEntity.toDTO();

        }

        // 수정
        public MemberDTO setUpdate(MemberDTO dto) {
            // DTO → Entity 변환
            Member entity = Member.fromDTO(dto);

            // 저장
            Member savedEntity = joinRepository.save(entity);

            // Entity → DTO 변환
            return savedEntity.toDTO();

        }

        // 삭제
        public void setDeleteByMemberId(Long id) {
            joinRepository.deleteById(id);
        }

        //검색페이지
        public Page<MemberDTO> searchPage(String keyword, int page, int size) {
            Pageable pageable =  PageRequest.of(page, size, Sort.by("id").descending());
            return joinRepository
                    .findByMemberIdContainingOrMemberNicknameContaining(keyword, keyword, pageable)
                    .map(Member::toDTO);
        }
        //전체페이지
        public Page<MemberDTO> getPageAll(int page, int size) {
            Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
            return joinRepository.findAll(pageable)
                    .map(Member::toDTO);
        }


        // 아이디로 단건조회
        public Optional<MemberDTO> getSelectOne(Long id) {
            return joinRepository.findById(id)
                    .map(Member::toDTO);
        }

        // 유저아이디로 조회
        /** 로그인: 성공 시 Member, 실패 시 null 반환(최소 구현) */
        public Member authenticate(String memberId, String rawPw) {
            return joinRepository.findByMemberId(memberId)
                    .filter(member -> Objects.equals(member.getMemberPw(), rawPw))   // 평문 비교 (최소 버전)
                    .orElse(null);
        }

        /** 비회원 차단용 */
        public boolean existsByMemberId(String memberId) {
            return joinRepository.existsByMemberId(memberId);
        }

}
