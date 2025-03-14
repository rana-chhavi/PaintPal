package com.chhrana.paintPal.painting;

import com.chhrana.paintPal.history.PaintingTransactionHistory;
import org.springframework.stereotype.Service;

@Service
public class PaintingMapper {

    public Painting toPainting(PaintingRequest request) {
        return Painting.builder()
                .id(request.id())
                .title(request.title())
                .artist(request.artist())
                .info(request.info())
                .archived(false)
                .shareable(request.shareable())
                .build();
    }

    public PaintingResponse toPaintingResponse(Painting painting) {
        return PaintingResponse.builder()
                .id(painting.getId())
                .title(painting.getTitle())
                .artist(painting.getArtist())
                .doi(painting.getDoi())
                .info(painting.getInfo())
                .rating(painting.getRating())
                .archived(painting.isArchived())
                .shareable(painting.isShareable())
                .owner(painting.getOwner().fullName())
                .build();
    }

    public BorrowedPaintingResponse toBorrowedPaintingResponse(PaintingTransactionHistory paintingTransactionHistory) {
        return BorrowedPaintingResponse.builder()
                .id(paintingTransactionHistory.getPainting().getId())
                .title(paintingTransactionHistory.getPainting().getTitle())
                .artist(paintingTransactionHistory.getPainting().getArtist())
                .doi(paintingTransactionHistory.getPainting().getDoi())
                .rating(paintingTransactionHistory.getPainting().getRating())
                .returned(paintingTransactionHistory.isReturned())
                .returnApproved(paintingTransactionHistory.isReturned())
                .build();
    }
}
