package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class MissionController {
    @GetMapping("/missions?userId")
    public String exception(
            @RequestParam String queryParameter
    ){
        return MissionService.singleParameter(queryParameter);
    }

    @PatchMapping("missions/{mission-id}/success")
    public String success(
            @RequestBody MissionResDTO.RequestBody requestBody(
                    @RequestBody MissionReqDTO.ReqeustBody dto
    ){
                return missionService.requestBody(dto);
    }
    )

}
