package com.chhrana.paintPal.history;

import com.chhrana.paintPal.painting.Painting;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaintingTransactionHistoryRepository extends JpaRepository<PaintingTransactionHistory, Integer> {

    @Query("""
            SELECT history
            FROM PaintingTransactionHistory history
            WHERE history.user.id = :userId
            """)
    Page<PaintingTransactionHistory> findAllBorrowedPaintings(int page, Integer userId);
}
