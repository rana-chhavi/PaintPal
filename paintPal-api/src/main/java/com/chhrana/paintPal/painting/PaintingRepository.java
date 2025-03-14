package com.chhrana.paintPal.painting;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PaintingRepository extends JpaRepository<Painting, Integer>, JpaSpecificationExecutor<Painting> {

    @Query("""
            SELECT painting 
            FROM PAINTING painting
            WHERE painting.archived = false
            AND painting.shareable = true
            AND painting.owner.id != :userId
            """)
    Page<Painting> findAllDisplayablePainting(int page, Integer id);
}
