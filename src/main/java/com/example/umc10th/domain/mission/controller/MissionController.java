package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 내가 받은 미션 조회
    @GetMapping("/missions/me")
    public MissionResDTO.MyMissionListDTO getMyMissions(
            @RequestParam Long memberId
    ) {
        return missionService.getMyMissions(memberId);
    }

    // 미션 성공 누르기
    @PatchMapping("/member-missions/{memberMissionId}/complete")
    public MissionResDTO.CompleteMissionResultDTO completeMission(
            @PathVariable Long memberMissionId
    ) {
        return missionService.completeMission(memberMissionId);
    }
}

