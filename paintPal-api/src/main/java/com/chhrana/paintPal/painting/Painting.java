package com.chhrana.paintPal.painting;

import com.chhrana.paintPal.review.Review;
import com.chhrana.paintPal.common.BaseEntity;
import com.chhrana.paintPal.history.PaintingTransactionHistory;
import com.chhrana.paintPal.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Painting extends BaseEntity {

    private String title;

    private String artist;

    private String doi;  //digital object identifier - isbn

    private String info;    // synopsis

    private String image;

    private boolean archived;

    private boolean shareable;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "painting")
    private List<Review> reviews;

    @OneToMany(mappedBy = "painting")
    private List<PaintingTransactionHistory> histories;

    @Transient
    public double getRating() {
        if (reviews == null || reviews.isEmpty()) {
            return 0.0;
        }

        var rate = this.reviews.stream()
                .mapToDouble(Review::getNote)
                .average()
                .orElse(0.0);

        double roundedRate = Math.round(rate * 10.0) / 10.0;
        return roundedRate;
    }

}
