package com.chhrana.paintPal.history;

import com.chhrana.paintPal.painting.Painting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PaintingTransactionHistoryRepository extends JpaRepository<PaintingTransactionHistory, Integer> {

    @Query("""
            SELECT history
            FROM PaintingTransactionHistory history
            WHERE history.user.id = :userId
            """)
    Page<PaintingTransactionHistory> findAllBorrowedPaintings(Pageable page, Integer userId);

    @Query("""
            SELECT history
            FROM PaintingTransactionHistory history
            WHERE history.painting.owner.id = :userId
            """)
    Page<PaintingTransactionHistory> findAllReturnedPaintings(Pageable page, Integer userId);

    @Query("""
            SELECT
            (COUNT(*) > 0) AS isBorrowed
            FROM PaintingTransactionHistory paintingTransactionHistory
            WHERE paintingTransactionHistory.user.id = :userId
            AND paintingTransactionHistory.painting.id = :paintingId
            AND paintingTransactionHistory.returnApproved = false
            """)
    boolean isAlreadyBorrowedByUser(Integer paintingId, @Param("userId")Integer userId);

    @Query("""
            SELECT transaction
            FROM PaintingTransactionHistory transaction
            WHERE transaction.user.id = :userId
            AND transaction.painting.id = :paintingId
            AND transaction.returned = false
            AND transaction.returnApproved = false
            """)
    Optional<PaintingTransactionHistory> findByPaintingIdAndUserId(Integer paintingId, Integer userId);

    @Query("""
            SELECT transaction
            FROM PaintingTransactionHistory transaction
            WHERE transaction.painting.owner.id = :userId
            AND transaction.painting.id = :paintingId
            AND transaction.returned = true
            AND transaction.returnApproved = false
            """)
    Optional<PaintingTransactionHistory> findByPaintingIdAndOwnerId(Integer paintingId, Integer userId);
}
