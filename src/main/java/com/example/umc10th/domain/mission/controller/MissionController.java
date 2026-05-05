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
    @GetMapping("/missions?userId")//@RequestParam을 쓰고 싶으면 userId 부분은 지우기.
    public String exception(
            @RequestParam String queryParameter
    ){
        return MissionService.singleParameter(queryParameter);
    }

    @PatchMapping("missions/{mission-id}/success")
    public String success(
            @RequestBody MissionReqDTO.RequestBody requestBody(
                    @RequestBody MissionReqDTO.ReqeustBody dto // 이 부분을 빼도 된다.
    ){
                return missionService.requestBody(dto);
    }
    )

}
