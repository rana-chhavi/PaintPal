package com.chhrana.paintPal.painting;

import com.chhrana.paintPal.common.PageResponse;
import com.chhrana.paintPal.history.PaintingTransactionHistory;
import com.chhrana.paintPal.history.PaintingTransactionHistoryRepository;
import com.chhrana.paintPal.user.User;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PaintingService {

    private final PaintingRepository paintingRepository;
    private final PaintingTransactionHistoryRepository paintingTransactionHistoryRepository;
    private final PaintingMapper paintingMapper;
    public Integer save(PaintingRequest request, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Painting painting = paintingMapper.toPainting(request);
        painting.setOwner(user);
        return paintingRepository.save(painting).getId();
    }

    public PaintingResponse findById(Integer id) {
        return paintingRepository.findById(id).map(paintingMapper::toPaintingResponse)
                .orElseThrow(() -> new EntityNotFoundException("No painting found with the ID:: " + id));
    }

    public PageResponse<PaintingResponse> findAllPaintings(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createDate").descending());
        Page<Painting> paintings = paintingRepository.findAllDisplayablePainting(page, user.getId());
        List<PaintingResponse> paintingResponse = paintings.stream()
                .map(paintingMapper::toPaintingResponse).toList();

        return new PageResponse<>(
                paintingResponse,
                paintings.getNumber(),
                paintings.getSize(),
                paintings.getTotalElements(),
                paintings.getTotalPages(),
                paintings.isFirst(),
                paintings.isLast()
        );
    }

    public PageResponse<PaintingResponse> findAllPaintingsByOwner(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page,size,Sort.by("createdDate").descending());
        Page<Painting> paintings = paintingRepository.findAll(PaintingSpecification.withOwnerId(user.getId()), pageable);
        List<PaintingResponse> paintingResponse = paintings.stream()
                .map(paintingMapper::toPaintingResponse).toList();

        return new PageResponse<>(
                paintingResponse,
                paintings.getNumber(),
                paintings.getSize(),
                paintings.getTotalElements(),
                paintings.getTotalPages(),
                paintings.isFirst(),
                paintings.isLast()
        );
    }

    public PageResponse<BorrowedPaintingResponse> findAllBorrowedPaintings(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page,size,Sort.by("createdDate").descending());
        Page< PaintingTransactionHistory> allBorrowedPaintings = paintingTransactionHistoryRepository.findAllBorrowedPaintings(page, user.getId());
        List<BorrowedPaintingResponse> paintingResponse = allBorrowedPaintings.stream()
                .map(paintingMapper::toBorrowedPaintingResponse)
                .toList();

        return new PageResponse<>(
                paintingResponse,
                allBorrowedPaintings.getNumber(),
                allBorrowedPaintings.getSize(),
                allBorrowedPaintings.getTotalElements(),
                allBorrowedPaintings.getTotalPages(),
                allBorrowedPaintings.isFirst(),
                allBorrowedPaintings.isLast()
        );
    }
}
