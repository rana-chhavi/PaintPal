import { Component } from '@angular/core';
import { BorrowedPaintingResponse, PageResponseBorrowedPaintingResponse, PaintingResponse, ReviewRequest } from '../../../../services/models';
import { PaintingService, ReviewService } from '../../../../services/services';

@Component({
  selector: 'app-borrowed-painting-list',
  templateUrl: './borrowed-painting-list.component.html',
  styleUrl: './borrowed-painting-list.component.scss'
})
export class BorrowedPaintingListComponent {
  page = 0;
  size = 5;
  pages: any = [];
  borrowedPaintings: PageResponseBorrowedPaintingResponse = {};
  selectedPainting: PaintingResponse | undefined = undefined;
  reviewRequest: ReviewRequest = {paintingId: 0, review: '', note: 0};
  constructor(
    private paintingService: PaintingService,
    private reviewService: ReviewService
  ) {
  }
  ngOnInit(): void {
    this.findAllBorrowedPaintings();
  }

  private findAllBorrowedPaintings() {
    this.paintingService.findAllBorrowedPaintings({
      page: this.page,
      size: this.size
    }).subscribe({
      next: (resp:any) => {
        this.borrowedPaintings = resp;
        this.pages = Array(this.borrowedPaintings.totalPages)
          .fill(0)
          .map((x, i) => i);
      }
    });
  }

  gotToPage(page: number) {
    this.page = page;
    this.findAllBorrowedPaintings();
  }

  goToFirstPage() {
    this.page = 0;
    this.findAllBorrowedPaintings();
  }

  goToPreviousPage() {
    this.page --;
    this.findAllBorrowedPaintings();
  }

  goToLastPage() {
    this.page = this.borrowedPaintings.totalPages as number - 1;
    this.findAllBorrowedPaintings();
  }

  goToNextPage() {
    this.page++;
    this.findAllBorrowedPaintings();
  }

  get isLastPage() {
    return this.page === this.borrowedPaintings.totalPages as number - 1;
  }

  returnBorrowedPainting(painting: BorrowedPaintingResponse) {
    this.selectedPainting = painting;
    this.reviewRequest.paintingId = painting.id as number;
  }

  returnPainting(withReview: boolean) {
    this.paintingService.returnBorrowPainting({
      id: this.selectedPainting?.id as number
    }).subscribe({
      next: () => {
        if (withReview) {
          this.giveReview();
        }
        this.selectedPainting = undefined;
        this.findAllBorrowedPaintings();
      }
    });
  }

  private giveReview() {
    this.reviewService.saveReview({
      body: this.reviewRequest
    }).subscribe({
      next: () => {
      }
    });
  }
}
