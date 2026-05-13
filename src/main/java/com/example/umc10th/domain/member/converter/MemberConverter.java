package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.Gender;

public class MemberConverter {

    public static Member toMember(MemberReqDTO.SignupDTO request) {
        return new Member(
                request.getName(),
                toGender(request.getGender()),
                request.getBirth(),
                request.getAddress(),
                0,
                request.getEmail(),
                request.getPassword(),
                request.getPhoneNum(),
                request.getProfileUrl()
        );
    }

    public static MemberResDTO.SignupResultDTO toSignupResultDTO(Member member) {
        return MemberResDTO.SignupResultDTO.builder()
                .memberId(member.getId())
                .message("회원가입이 완료되었습니다.")
                .build();
    }

    public static MemberResDTO.PointDTO toPointDTO(Member member) {
        return MemberResDTO.PointDTO.builder()
                .point(member.getPoint())
                .build();
    }

    public static MemberResDTO.UpdateResultDTO toUpdateResultDTO(Member member) {
        return MemberResDTO.UpdateResultDTO.builder()
                .memberId(member.getId())
                .message("회원 정보가 변경되었습니다.")
                .build();
    }

    public static MemberResDTO.DeleteResultDTO toDeleteResultDTO(Member member) {
        return MemberResDTO.DeleteResultDTO.builder()
                .memberId(member.getId())
                .message("계정 탈퇴가 완료되었습니다.")
                .build();
    }

    public static MemberResDTO.LogoutResultDTO toLogoutResultDTO() {
        return MemberResDTO.LogoutResultDTO.builder()
                .message("로그아웃이 완료되었습니다.")
                .build();
    }

    private static Gender toGender(String gender) {
        if (gender == null || gender.isBlank()) {
            return Gender.NONE;
        }

        return Gender.valueOf(gender.toUpperCase());
    }
}
