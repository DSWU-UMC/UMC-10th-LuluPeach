package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class ReviewController {
    //리뷰 작성 관련 controller
    @PostMapping("/store/{storeId}/review")
    public ReviewResDTO.RequestBody requestBody(
            @RequestBody ReviewReqDTO.RequestBodyClass dto
    ) {
        return ReviewService.requestBody(dto);
    }
}
