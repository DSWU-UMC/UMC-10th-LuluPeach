package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import lombok.Builder;


public class MissionConverter {

    public static MissionResDTO.MyMissionDTO toMyMissionDTO(MemberMission memberMission) {
        return MissionResDTO.MyMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .condition(memberMission.getMission().getConditional())
                .point(memberMission.getMission().getPoint())
                .deadline(memberMission.getMission().getDeadline())
                .isComplete(memberMission.getIsComplete())
                .build();
    }

    public static MissionResDTO.CompleteMissionResultDTO toCompleteMissionResultDTO(MemberMission memberMission) {
        return MissionResDTO.CompleteMissionResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .isComplete(memberMission.getIsComplete())
                .message(memberMission.getIsComplete() ? "미션 완료 처리되었습니다" : "미션 완료 처리에 실패했습니다")
                .build();
    }

    // 가게 미션 생성
    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ){
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }
}
