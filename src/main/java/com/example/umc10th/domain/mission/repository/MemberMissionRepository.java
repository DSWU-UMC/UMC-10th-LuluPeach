package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    List<MemberMission> findByMemberId(Long memberId);

//    List<MemberMission> findAllByMemberIdAndIsCompleteFalse(Long memberId);

    Page <MemberMission> findAllByMemberIdAndIsCompleteFalse(
            Long memberId,
            PageRequest pageRequest // gpt 는 PageRequest 보다 상위 타입인 Pageable 쓰기를 추천함.
    );
}
