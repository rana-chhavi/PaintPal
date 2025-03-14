package com.chhrana.paintPal.painting;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowedPaintingResponse {
    private Integer id;
    private String title;
    private String artist;
    private String doi;
    private double rating;
    private boolean returned;
    private boolean returnApproved;
}
