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

    @GetMapping("/borrowed")
    public ResponseEntity<PageResponse<BorrowedPaintingResponse>> findAllBorrowedPaintings(
            @RequestParam(name="page", defaultValue = "0", required = false) int page,
            @RequestParam(name="size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ) {

        return ResponseEntity.ok(paintingService.findAllBorrowedPaintings(page, size, connectedUser));
    }

    @GetMapping("/returned")
    public ResponseEntity<PageResponse<BorrowedPaintingResponse>> findAllReturnedPaintings(
            @RequestParam(name="page", defaultValue = "0", required = false) int page,
            @RequestParam(name="size", defaultValue = "10", required = false) int size,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(paintingService.findAllReturnedPaintings(page, size, connectedUser));
    }

    @PatchMapping("/shareable/{id}")
    public ResponseEntity<Integer> updateShareableStatus(
            @PathVariable("id") Integer paintingId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(paintingService.updateShareableStatus(paintingId, connectedUser));
    }

    @PatchMapping("/archived/{id}")
    public ResponseEntity<Integer> updateArchivedStatus(
            @PathVariable("id") Integer paintingId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(paintingService.updateArchivedStatus(paintingId, connectedUser));
    }

    @PostMapping("/borrow/{id}")
    public ResponseEntity<Integer> borrowPainting(
            @PathVariable("id") Integer paintingId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(paintingService.borrowPainting(paintingId, connectedUser));
    }

    @PatchMapping("/borrow/return/{id}")
    public ResponseEntity<Integer> returnBorrowPainting(
            @PathVariable("id") Integer paintingId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(paintingService.returnBorrowedPainting(paintingId, connectedUser));
    }

    @PatchMapping("/borrow/return/approve/{id}")
    public ResponseEntity<Integer> approveReturnBorrowedPainting(
            @PathVariable("id") Integer paintingId,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(paintingService.approveReturnBorrowedPainting(paintingId, connectedUser));
    }

}
