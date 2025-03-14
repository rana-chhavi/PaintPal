package com.chhrana.paintPal.painting;

import com.chhrana.paintPal.common.PageResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("painting")
@RequiredArgsConstructor
@Tag(name="Painting")
public class PaintingController {

    private final PaintingService paintingService;

    @PostMapping
    public ResponseEntity<Integer> savePainting(
            @Valid @RequestBody PaintingRequest request,
            Authentication connectedUser
            ) {
        return ResponseEntity.ok(paintingService.save(request, connectedUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaintingResponse> getPaintingById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(paintingService.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<PaintingResponse>> findAllPaintings(
            @RequestParam(name="page", defaultValue = "0", required = false) int page,
            @RequestParam(name="size", defaultValue = "10", required = false) int size,
            Authentication connectedUser

    ) {
        return ResponseEntity.ok(paintingService.findAllPaintings(page, size, connectedUser));
    }

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<PaintingResponse>> findAllPaintingsByOwner(
            @RequestParam(name="page", defaultValue = "0", required = false) int page,
            @RequestParam(name="size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(paintingService.findAllPaintingsByOwner(page, size, connectedUser));
    }

    @GetMapping("/borrowed-paintings")
    public ResponseEntity<PageResponse<BorrowedPaintingResponse>> findAllBorrowedPaintings(
            @RequestParam(name="page", defaultValue = "0", required = false) int page,
            @RequestParam(name="size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ) {

        return ResponseEntity.ok(paintingService.findAllBorrowedPaintings(page, size, connectedUser));
    }

}
