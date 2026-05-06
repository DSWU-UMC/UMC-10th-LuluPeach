package com.example.umc10th.domain.review.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    // 어떤 리뷰에 답글이 달리거나 Or 말거나 이기 때문에 Reply에서 @JoinColumn(name = "review_id", nullable = false) 설정을 통해 reply가 review_id를 가지게 하는 것이 맞다 판단함.
    // 그래서 스터디북과 내용이 약간 다름.
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    public Reply(String content) {
        this.content = content;
    }

    //Reply 안의 Review에 값을 넣기 위해.
    protected void setReview(Review review) {
        this.review = review;
    }
}


