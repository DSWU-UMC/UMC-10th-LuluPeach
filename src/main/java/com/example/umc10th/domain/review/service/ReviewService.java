package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;

public class ReviewService {
    public ReviewResDTO.RequestBodyClass requestBody(
            ReviewReqDTO.RequestBodyClass dto
    ){
        return ReviewConverter.toRequestBody(dto.stringTest(), dto.longTest());
    }
}
