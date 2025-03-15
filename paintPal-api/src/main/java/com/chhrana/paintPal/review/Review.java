package com.chhrana.paintPal.review;

import com.chhrana.paintPal.common.BaseEntity;
import com.chhrana.paintPal.painting.Painting;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review extends BaseEntity {

    private Double note;     //1-5 stars
    private String review;

    @ManyToOne
    @JoinColumn(name = "painting_id")
    private Painting painting;

}
