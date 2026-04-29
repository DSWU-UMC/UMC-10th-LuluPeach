package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.*;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {


    //마이페이지에 필요한 QueryParameter 처리하는 코드
    public String singleParameter(String singleParameter) {
        return singleParameter;
    }

    //회원가입에 필요한 RequestBody 처리하는 부분
    public MemberResDTO.RequestBody requestBody(
            MemberReqDTO.RequestBodyClass dto
    ){
        return MemberConverter.toRequestBody(dto.toRequestBody(), dto.longTest());
    }
    //질문! 여기 위에 toRequestBody, toRequestBody(), longTest() 는 아직 안 만들었으니 오류 나는 게 정상 맞죠??
}
