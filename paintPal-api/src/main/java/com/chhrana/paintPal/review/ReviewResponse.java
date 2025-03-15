package com.chhrana.paintPal.review;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
    private Double note;
    private String review;
    private boolean ownReview;
}
