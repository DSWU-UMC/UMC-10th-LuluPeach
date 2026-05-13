package com.example.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;

public class MissionReqDTO {
    // 가게 미션 생성
    public record CreateMission(
            LocalDate deadline,
            Integer point,
            String conditional
    ){}

    // 가게 내 미션 조회
    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String conditional
    ){}
}
