package com.chhrana.paintPal.painting;

import com.chhrana.paintPal.common.PageResponse;
import com.chhrana.paintPal.exception.OperationNotPermittedException;
import com.chhrana.paintPal.file.FileStorageService;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class PaintingService {

    private final PaintingRepository paintingRepository;
    private final PaintingTransactionHistoryRepository paintingTransactionHistoryRepository;
    private final PaintingMapper paintingMapper;
    private final FileStorageService fileStorageService;
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
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Painting> paintings = paintingRepository.findAllDisplayablePainting(pageable, user.getId());
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
        Page< PaintingTransactionHistory> allBorrowedPaintings = paintingTransactionHistoryRepository.findAllBorrowedPaintings(pageable, user.getId());
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

    public PageResponse<BorrowedPaintingResponse> findAllReturnedPaintings(int page, int size, Authentication connectedUser) {
        User user = ((User) connectedUser.getPrincipal());
        Pageable pageable = PageRequest.of(page,size,Sort.by("createdDate").descending());
        Page< PaintingTransactionHistory> allReturnedPaintings = paintingTransactionHistoryRepository.findAllReturnedPaintings(pageable, user.getId());
        List<BorrowedPaintingResponse> paintingResponse = allReturnedPaintings.stream()
                .map(paintingMapper::toBorrowedPaintingResponse)
                .toList();

        return new PageResponse<>(
                paintingResponse,
                allReturnedPaintings.getNumber(),
                allReturnedPaintings.getSize(),
                allReturnedPaintings.getTotalElements(),
                allReturnedPaintings.getTotalPages(),
                allReturnedPaintings.isFirst(),
                allReturnedPaintings.isLast()
        );
    }

    public Integer updateShareableStatus(Integer paintingId, Authentication connectedUser) {
        Painting painting = paintingRepository.findById(paintingId)
                .orElseThrow(() -> new EntityNotFoundException("No painting found with ID:: " + paintingId));
        User user = ((User) connectedUser.getPrincipal());
        if(!Objects.equals(painting.getOwner().getId(), user.getId())){
            throw new OperationNotPermittedException("You cannot update paintings shareable status");
        }
        painting.setShareable(!painting.isShareable());
        paintingRepository.save(painting);
        return paintingId;
    }

    public Integer updateArchivedStatus(Integer paintingId, Authentication connectedUser) {
        Painting painting = paintingRepository.findById(paintingId)
                .orElseThrow(() -> new EntityNotFoundException("No painting found with ID:: " + paintingId));
        User user = ((User) connectedUser.getPrincipal());
        if(!Objects.equals(painting.getOwner().getId(), user.getId())){
            throw new OperationNotPermittedException("You cannot update others paintings archived status");
        }
        painting.setArchived(!painting.isArchived());
        paintingRepository.save(painting);
        return paintingId;
    }

    public Integer borrowPainting(Integer paintingId, Authentication connectedUser) {
        Painting painting = paintingRepository.findById(paintingId)
                .orElseThrow(() -> new EntityNotFoundException("No painting found with ID:: " + paintingId));

        if(painting.isArchived() || !painting.isShareable()) {
            throw new OperationNotPermittedException("The requested painting cannot be borrowed because it is archived or not shareable.");
        }

        User user = ((User) connectedUser.getPrincipal());
        if(Objects.equals(painting.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot borrow your own painting");
        }

        final boolean isAlreadyBorrowed = paintingTransactionHistoryRepository.isAlreadyBorrowedByUser(paintingId, user.getId());
        if(isAlreadyBorrowed) {
            throw new OperationNotPermittedException("The requested painting is already borrowed.");
        }
        PaintingTransactionHistory paintingTransactionHistory = PaintingTransactionHistory.builder()
                .user(user)
                .painting(painting)
                .returned(false)
                .returnApproved(false)
                .build();

        return paintingTransactionHistoryRepository.save(paintingTransactionHistory).getId();
    }

    public Integer returnBorrowedPainting(Integer paintingId, Authentication connectedUser) {
        Painting painting = paintingRepository.findById(paintingId)
                .orElseThrow(() -> new EntityNotFoundException("No painting found with ID:: " + paintingId));

        if(painting.isArchived() || !painting.isShareable()) {
            throw new OperationNotPermittedException("The requested painting cannot be returned because it is archived or not shareable.");
        }

        User user = ((User) connectedUser.getPrincipal());
        if(Objects.equals(painting.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot return your own painting");
        }

        PaintingTransactionHistory paintingTransactionHistory = paintingTransactionHistoryRepository.findByPaintingIdAndUserId(paintingId, user.getId())
                .orElseThrow(()-> new OperationNotPermittedException("You did not borrow this painting"));

        paintingTransactionHistory.setReturned(true);
        return paintingTransactionHistoryRepository.save(paintingTransactionHistory).getId();

    }

    public Integer approveReturnBorrowedPainting(Integer paintingId, Authentication connectedUser) {
        Painting painting = paintingRepository.findById(paintingId)
                .orElseThrow(() -> new EntityNotFoundException("No painting found with ID:: " + paintingId));

        if(painting.isArchived() || !painting.isShareable()) {
            throw new OperationNotPermittedException("The requested painting cannot be approved because it is archived or not shareable.");
        }

        User user = ((User) connectedUser.getPrincipal());
        if(Objects.equals(painting.getOwner().getId(), user.getId())) {
            throw new OperationNotPermittedException("You cannot approve return of your own painting");
        }
        PaintingTransactionHistory paintingTransactionHistory = paintingTransactionHistoryRepository.findByPaintingIdAndOwnerId(paintingId, user.getId())
                .orElseThrow(()-> new OperationNotPermittedException("You did not borrow this painting"));

        paintingTransactionHistory.setReturnApproved(true);
        return paintingTransactionHistoryRepository.save(paintingTransactionHistory).getId();

    }

    public void uploadPaintingImage(MultipartFile file, Authentication connectedUser, Integer paintingId) {
        Painting painting = paintingRepository.findById(paintingId)
                .orElseThrow(() -> new EntityNotFoundException("No painting found with ID:: " + paintingId));

        User user = ((User) connectedUser.getPrincipal());
        var image = fileStorageService.saveFile(file, painting, user.getId());
        painting.setImage(image);
        paintingRepository.save(painting);
    }
}
