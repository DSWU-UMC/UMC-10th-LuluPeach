package com.example.umc10th.domain.review.exception;

import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;

public class ReviewException extends RuntimeException {
    public ReviewException(ReviewErrorCode message) {
        super(String.valueOf(message));
    }
}
