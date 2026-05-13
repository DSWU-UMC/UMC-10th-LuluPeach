package com.example.umc10th.domain.mission.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionListDTO {
        private Long memberId;
        private List<MyMissionDTO> missions;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionDTO {

        private Long memberMissionId;

        private Long missionId;

        private String storeName;

        private String condition;

        private Integer point;

        private LocalDate deadline;

        private Boolean isComplete;
    }

    // 페이지네이션 툴 (오프셋 기반)
    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){}




    @Getter
    @Builder
    @AllArgsConstructor
    public static class CompleteMissionResultDTO {

        private Long memberMissionId;

        private Boolean isComplete;

        private String message;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class GetMission {
        private Long storeId;
        private List<MissionInfo> missions;


    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class MissionInfo {
        private Long missionId;
        private LocalDate deadline;
        private String conditional;
        private Integer point;
    }
}
