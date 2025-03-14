package com.chhrana.paintPal.history;

import com.chhrana.paintPal.common.BaseEntity;
import com.chhrana.paintPal.painting.Painting;
import com.chhrana.paintPal.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class PaintingTransactionHistory extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "painting_id")
    private Painting painting;

    private boolean returned;
    private boolean returnApproved;

}
