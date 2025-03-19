package com.chhrana.paintPal.painting;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaintingRepository extends JpaRepository<Painting, Integer>, JpaSpecificationExecutor<Painting> {

    @Query("""
            SELECT painting
            FROM Painting painting
            WHERE painting.archived = false
            AND painting.shareable = true
            AND painting.createdBy != :userId
            """)
    Page<Painting> findAllDisplayablePainting(Pageable page, @Param("userId")Integer userId);
}
