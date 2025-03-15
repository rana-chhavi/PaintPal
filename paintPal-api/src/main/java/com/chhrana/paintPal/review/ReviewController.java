package com.chhrana.paintPal.review;

import com.chhrana.paintPal.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

@RestController
@RequestMapping("reviews")
@RequiredArgsConstructor
@Tag(name="Review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Integer> saveReview (
            @Valid @RequestBody ReviewRequest request,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(reviewService.save(request, connectedUser));
    }

    @GetMapping("/paitnings/{id}/reviews")
    public ResponseEntity<PageResponse<ReviewResponse>> findAllReviewsByPainting(
            @PathVariable("id") Integer id,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
            ) {
        return ResponseEntity.ok(reviewService.findAllReviewsByPainting(id, page, size, connectedUser));
    }
}
