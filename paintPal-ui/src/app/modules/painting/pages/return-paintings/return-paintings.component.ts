import { BorrowedPaintingResponse } from './../../../../services/models/borrowed-painting-response';
import { Component } from '@angular/core';
import { PageResponseBorrowedPaintingResponse } from '../../../../services/models';
import { PaintingService } from '../../../../services/services';

@Component({
  selector: 'app-return-paintings',
  templateUrl: './return-paintings.component.html',
  styleUrl: './return-paintings.component.scss'
})
export class ReturnPaintingsComponent {
  page = 0;
  size = 5;
  pages: any = [];
  returnedPaintings: PageResponseBorrowedPaintingResponse = {};
  message = '';
  level: 'success' |'error' = 'success';
  constructor(
    private paintingService: PaintingService
  ) {
  }

  ngOnInit(): void {
    this.findAllReturnedPaintings();
  }

  private findAllReturnedPaintings() {
    this.paintingService.findAllReturnedPaintings({
      page: this.page,
      size: this.size
    }).subscribe({
      next: (resp) => {
        this.returnedPaintings = resp;
        this.pages = Array(this.returnedPaintings.totalPages)
          .fill(0)
          .map((x, i) => i);
      }
    });
  }

  gotToPage(page: number) {
    this.page = page;
    this.findAllReturnedPaintings();
  }

  goToFirstPage() {
    this.page = 0;
    this.findAllReturnedPaintings();
  }

  goToPreviousPage() {
    this.page --;
    this.findAllReturnedPaintings();
  }

  goToLastPage() {
    this.page = this.returnedPaintings.totalPages as number - 1;
    this.findAllReturnedPaintings();
  }

  goToNextPage() {
    this.page++;
    this.findAllReturnedPaintings();
  }

  get isLastPage() {
    return this.page === this.returnedPaintings.totalPages as number - 1;
  }

  approvePaintingReturn(painting: BorrowedPaintingResponse) {
    if (!painting.returned) {
      return;
    }
    this.paintingService.approveReturnBorrowedPainting({
      id: painting.id as number
    }).subscribe({
      next: () => {
        this.level = 'success';
        this.message = 'Painting return approved';
        this.findAllReturnedPaintings();
      }
    });
  }
}
