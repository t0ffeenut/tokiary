package com.example.tokiary.repository;

import com.example.tokiary.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JoinRepository extends JpaRepository<Member, Long>{


        Optional<Member> findById(Long id);        // PK 조회


        //로그인용
        Optional<Member> findByMemberId(String memberId);
        boolean existsByMemberId(String memberId);
        void deleteByMemberId(String memberId);

        //검색용
        List<Member> findByMemberIdContainingOrMemberNicknameContaining(String memberId, String memberNickname);
        Page<Member> findByMemberIdContainingOrMemberNicknameContaining(String memberId, String memberNickname, Pageable pageable);




    }
