package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    // 내가 받은 미션 조회(홈)
    @GetMapping("/missions/me")
    public MissionResDTO.MyMissionListDTO getMyMissions( //최종 반환값은 MyMissionListDTO
            @RequestParam Long memberId //getMyMissions에 파라미터로 memberId를 보낸다.
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

    // 가게 미션 생성
    @PostMapping("v1/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionReqDTO missionReqDTO
    ){
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(MissionSuccessCode.CREATED, null);
    }

    // 가게 내 미션들 조회
//    @GetMapping("/v1/stores/{storeId}/missions")
//    public ApiResponse<MissionResDTO.GetMission> getMissions(
//            @PathVariable Long storeId,
//
//            @RequestParam Integer pageSize,
//            @RequestParam Integer pageNumber,
//            @RequestParam(required = false) String sort
//    ){
//        BaseSuccessCode code = MissionSuccessCode.OK;
//        return ApiResponse.onSuccess(code, missionService.getMissions(storeId));
//    }

    // 내가 진행 중인 미션 조회
//    @GetMapping("/v1/members/{memberId}/missions/ongoing")
//    public ApiResponse<MissionResDTO.MyMissionListDTO> getMyOngoingMissions(
//            @PathVariable Long memberId
//    ) {
//        return ApiResponse.onSuccess(
//                MissionSuccessCode.OK,
//                missionService.getMyOngoingMissions(memberId)
//        );
//    }

    // 내가 진행 중인 미션 조회 (오프셋 기반 페이징)
    @GetMapping("/v1/members/{memberId}/missions/ongoing")
    public ApiResponse<Page<MissionResDTO.MyMissionDTO>> getMyOngoingMissions(
            @PathVariable Long memberId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMyOngoingMissions(memberId, pageSize, pageNumber, sort));
    }





}

