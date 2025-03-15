package com.chhrana.paintPal.review;

import com.chhrana.paintPal.common.PageResponse;
import com.chhrana.paintPal.exception.OperationNotPermittedException;
import com.chhrana.paintPal.painting.Painting;
import com.chhrana.paintPal.painting.PaintingRepository;
import com.chhrana.paintPal.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final PaintingRepository paintingRepository;
    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;

    public Integer save(ReviewRequest request, Authentication connectedUser) {
        return null;
    }

    public PageResponse<ReviewResponse> findAllReviewsByPainting(Integer id, int page, int size, Authentication connectedUser) {
        Pageable pageable = PageRequest.of(page, size);
        User user = ((User) connectedUser.getPrincipal());
        Page<Review> reviews = reviewRepository.findAllByPaintingId(id, pageable);
        List<ReviewResponse> reviewResponses = reviews.stream()
                .map(f -> reviewMapper.toReviewResponse(f, user.getId()))
                        .toList();
        return new PageResponse<>(
                reviewResponses,
                reviews.getNumber(),
                reviews.getSize(),
                reviews.getTotalElements(),
                reviews.getTotalPages(),
                reviews.isFirst(),
                reviews.isLast()
        );
    }
}
