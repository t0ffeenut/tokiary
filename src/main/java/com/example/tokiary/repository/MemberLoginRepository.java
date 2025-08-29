package com.example.tokiary.repository;

import com.example.tokiary.entity.MemberLoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberLoginRepository extends JpaRepository<MemberLoginHistory, Long> {
    List<MemberLoginHistory> findByMemberId(String memberId);

}
