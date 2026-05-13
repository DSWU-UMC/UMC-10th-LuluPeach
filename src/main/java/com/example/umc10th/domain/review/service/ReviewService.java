package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.exception.MissionException;
import com.example.umc10th.domain.mission.exception.code.MissionErrorCode;
import com.example.umc10th.domain.mission.repository.StoreRepository;
import com.example.umc10th.domain.review.converter.ReviewConverter;
import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewPhoto;
import com.example.umc10th.domain.review.exception.ReviewException;
import com.example.umc10th.domain.review.exception.code.ReviewErrorCode;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.review.repository.ReviewPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewPhotoRepository reviewPhotoRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.CreateResultDTO createReview(ReviewReqDTO.CreateDTO request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다"));

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("매장을 찾을 수 없습니다"));

        // Review 생성 및 저장
        Review review = ReviewConverter.toReview(request, member, store);
        Review savedReview = reviewRepository.save(review);

        // ReviewPhoto 생성 및 저장
        List<ReviewPhoto> reviewPhotos = ReviewConverter.toReviewPhotos(savedReview, request.getPhotoUrls());
        reviewPhotoRepository.saveAll(reviewPhotos);

        return ReviewConverter.toCreateResultDTO(savedReview);
    }


    // 내가 생성한 리뷰 조회 (오프셋 기반 페이징)
    public ReviewResDTO.Pagination<ReviewResDTO.MyReviewDTO> getMyReviews(
            Long memberId,
            Integer pageSize,
            String cursor,
            String query
    ) {

        // 페이지 정보들을 PageRequest 로 만들기.
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Review> reviewList;
        String nextCursor;

        // 커서가 있는 경우
        if (!cursor.equals("-1")) {
            String[] cursorSplit = cursor.split(":");

            switch (query.toLowerCase()) {
                case "id":
                    idCursor = Long.parseLong(cursorSplit[1]);

                    reviewList = reviewRepository.findAllByMemberIdAndIdLessThanOrderByIdDesc(
                            memberId,
                            idCursor,
                            pageRequest
                    );
                    break;

                default:
                    throw new ReviewException(ReviewErrorCode.QUERY_NOT_VALID);
            }
        }

        // 커서 없이 조회
        else {
            reviewList = reviewRepository.findAllByMemberIdOrderByIdDesc(
                    memberId,
                    pageRequest
            );
        }


        // 다음 커서 계산
        nextCursor = reviewList.getContent().getLast().getId() + ":" + reviewList.getContent().getLast().getId();

        // 리뷰들 응답 DTO로 포장하기
        return ReviewConverter.toPagination(
                reviewList.map(ReviewConverter::toMyReviewDTO).toList(),
                reviewList.hasNext(),
                nextCursor,
                reviewList.getSize()
        );

    }


}
