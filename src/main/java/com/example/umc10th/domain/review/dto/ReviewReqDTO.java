package com.example.umc10th.domain.review.dto;

import lombok.Getter;

import java.util.List;

public class ReviewReqDTO {
    @Getter
    public static class RequestBodyClass{
            private Long userId;
            private String content;
            private Float star;
            private Long storeId;
            private List<ReviewResDTO.Photo> photos;
    }

    @Getter
    public static class Photo {
        private String photo_url;
    }
}
