package com.chhrana.paintPal.painting;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaintingResponse {

    private Integer id;
    private String title;
    private String artist;
    private String doi;
    private String info;
    private String owner;
    private byte[] image;
    private double rating;
    private boolean archived;
    private boolean shareable;
}
