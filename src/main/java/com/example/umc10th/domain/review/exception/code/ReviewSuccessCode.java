package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    CREATED(
            HttpStatus.OK,
            "REVIEW200_1",
            "성공적으로 리뷰를 생성했습니다."
    ),

    OK(
            HttpStatus.OK,
            "REVIEW200_2",
            "성공적으로 리뷰를 조회했습니다."
    );

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}

