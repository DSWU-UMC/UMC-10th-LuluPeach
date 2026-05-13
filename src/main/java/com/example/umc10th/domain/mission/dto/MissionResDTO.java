package com.example.umc10th.domain.mission.dto;

import com.sun.tools.javac.comp.MatchBindingsComputer;
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
