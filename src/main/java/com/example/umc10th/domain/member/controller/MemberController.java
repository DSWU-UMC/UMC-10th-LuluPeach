package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public MemberResDTO.SignupResultDTO signup(
            @RequestBody MemberReqDTO.SignupDTO request
    ) {
        return memberService.signup(request);
    }

    // 내 포인트 조회
    @GetMapping("/me/points")
    public MemberResDTO.PointDTO getMyPoint(
            @RequestParam Long memberId // 나중에 JWT 방식으로 바꾸면 여기를 없앨 예정.
    ) {
        return memberService.getMyPoint(memberId);
    }

    // 내 정보 변경
    @PatchMapping("/me")
    public MemberResDTO.UpdateResultDTO updateMyInfo(
            @RequestParam Long memberId, // 나중에 JWT 방식으로 바꾸면 여기를 없앨 예정.
            @RequestBody MemberReqDTO.UpdateDTO request
    ) {
        return memberService.updateMyInfo(memberId, request);
    }

    // 계정 탈퇴
    @DeleteMapping("/me")
    public MemberResDTO.DeleteResultDTO deleteMember(
            @RequestBody MemberReqDTO.DeleteDTO request
    ) {
        return memberService.deleteMember(request);
    }


}
