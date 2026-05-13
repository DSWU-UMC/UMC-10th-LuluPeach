package com.example.umc10th.domain.review.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    QUERY_NOT_VALID(
            HttpStatus.BAD_REQUEST,
            "REVIEW400_1",
            "쿼리 파라미터가 올바르지 않습니다."
    ),

    NOT_FOUND(
            HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "해당 리뷰가 존재하지 않습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
