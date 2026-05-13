package com.example.umc10th.domain.review.converter;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewPhoto;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.member.entity.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.CreateDTO request, Member member, Store store) {
        Review review = new Review(
                request.getContent(),
                request.getStar(),
                store,
                member
        );
        return review;
    }

    public static List<ReviewPhoto> toReviewPhotos(Review review, List<String> photoUrls) {
        List<ReviewPhoto> photos = new ArrayList<>();
        if (photoUrls != null) {
            for (String photoUrl : photoUrls) {
                photos.add(new ReviewPhoto(photoUrl, review));
            }
        }
        return photos;
    }

    public static ReviewResDTO.CreateResultDTO toCreateResultDTO(Review review) {
        return ReviewResDTO.CreateResultDTO.builder()
                .reviewId(review.getId())
                .message("리뷰 작성이 완료되었습니다")
                .build();
    }

    // 내가 쓴 리뷰 조회 DTO
    public static ReviewResDTO.MyReviewDTO toMyReviewDTO(Review review) {
        return ReviewResDTO.MyReviewDTO.builder()
                .reviewId(review.getId())
                .storeId(review.getStore().getId())
                .storeName(review.getStore().getName())
                .content(review.getContent())
                .star(review.getStar())
                .createdAt(review.getCreatedAt())
                .build();
    }


    //페이지네이션 틀 생성
    public static <T> ReviewResDTO.Pagination toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){
        return ReviewResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }


}
