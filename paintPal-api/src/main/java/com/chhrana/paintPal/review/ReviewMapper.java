package com.chhrana.paintPal.review;

import com.chhrana.paintPal.painting.Painting;
import org.springframework.data.domain.ManagedTypes;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ReviewMapper {

    public Review toReview(ReviewRequest request) {
        return Review.builder()
                .note(request.note())
                .review(request.review())
                .painting(Painting.builder()
                        .id(request.paintingId())
                        .archived(false)
                        .shareable(false)
                        .build())
                .build();
    }

    public ReviewResponse toReviewResponse(Review review, Integer id) {
        return ReviewResponse.builder()
                .note(review.getNote())
                .review(review.getReview())
                .ownReview(Objects.equals(review.getCreatedBy(), id))
                .build();
    }
}
