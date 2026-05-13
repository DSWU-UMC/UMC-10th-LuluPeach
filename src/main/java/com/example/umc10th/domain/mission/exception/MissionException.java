package com.example.umc10th.domain.mission.exception;

import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;

public class MissionException extends RuntimeException {
    public MissionException(ReviewErrorCode message) {
        super(String.valueOf(message));
    }
}
