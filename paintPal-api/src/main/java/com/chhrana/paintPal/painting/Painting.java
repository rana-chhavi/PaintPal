package com.chhrana.paintPal.painting;

import com.chhrana.paintPal.comment.Comment;
import com.chhrana.paintPal.common.BaseEntity;
import com.chhrana.paintPal.history.PaintingTransactionHistory;
import com.chhrana.paintPal.user.User;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
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

    private String genre;   //bookCover

    private boolean archived;

    private boolean shareable;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "painting")
    private List<Comment> comments;

    @OneToMany(mappedBy = "painting")
    private List<PaintingTransactionHistory> histories;

}
