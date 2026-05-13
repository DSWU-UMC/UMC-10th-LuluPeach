package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @EntityGraph(attributePaths = {"store"})
    Slice<Review> findAllByMemberIdAndIdLessThanOrderByIdDesc(
            Long memberId,
            Long idCursor,
            Pageable pageable
    );

    @EntityGraph(attributePaths = {"store"})
    Slice<Review> findAllByMemberIdOrderByIdDesc(
            Long memberId,
            Pageable pageable
    );

}
