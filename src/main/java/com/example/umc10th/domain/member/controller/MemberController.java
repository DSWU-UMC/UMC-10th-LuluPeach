package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.*;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;

    //여기 MemeberResDTO 는 아직 작성 안 했으니깐 오류 생기는 게 정상이죠??
    @PostMapping("/users/signup")
    public MemberResDTO.RequestBody requestSignup(
            @RequestBody MemberReqDTO.RequestBodyClass dto
    ) {
        return memberService.requestBody(dto);
    }

    @GetMapping("/mypage/{memberId}")
    public String myPage(@PathVariable String pathVariable) {
        return memberService.singleParameter(pathVariable);
    }
}