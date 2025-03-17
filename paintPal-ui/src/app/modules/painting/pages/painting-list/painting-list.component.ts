import { Component } from '@angular/core';
import { PageResponsePaintingResponse, PaintingResponse } from '../../../../services/models';
import { PaintingService } from '../../../../services/services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-painting-list',
  templateUrl: './painting-list.component.html',
  styleUrl: './painting-list.component.scss'
})
export class PaintingListComponent {
  paintingResponse: PageResponsePaintingResponse = {};
  page = 0;
  size = 5;
  pages: any = [];
  message = '';
  level: 'success' |'error' = 'success';

  constructor(
    private paintingService: PaintingService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.findAllPaintings();
  }

  private findAllPaintings() {
    this.paintingService.findAllPaintings({
      page: this.page,
      size: this.size
    })
      .subscribe({
        next: (paintings) => {
          this.paintingResponse = paintings;
          this.pages = Array(this.paintingResponse.totalPages)
            .fill(0)
            .map((x, i) => i);
        }
      });
  }

  gotToPage(page: number) {
    this.page = page;
    this.findAllPaintings();
  }

  goToFirstPage() {
    this.page = 0;
    this.findAllPaintings();
  }

  goToPreviousPage() {
    this.page --;
    this.findAllPaintings();
  }

  goToLastPage() {
    this.page = this.paintingResponse.totalPages as number - 1;
    this.findAllPaintings();
  }

  goToNextPage() {
    this.page++;
    this.findAllPaintings();
  }

  get isLastPage() {
    return this.page === this.paintingResponse.totalPages as number - 1;
  }

  borrowPainting(painting: PaintingResponse) {
    this.message = '';
    this.level = 'success';
    this.paintingService.borrowPainting({
      'id': painting.id as number
    }).subscribe({
      next: () => {
        this.level = 'success';
        this.message = 'Painting successfully added to your list';
      },
      error: (err) => {
        console.log(err);
        this.level = 'error';
        this.message = err.error.error;
      }
    });
  }

  displayPaintingDetails(painting: PaintingResponse) {
    this.router.navigate(['paintings', 'details', painting.id]);
  }
}
