import { Component } from '@angular/core';
import { PageResponsePaintingResponse, PaintingResponse } from '../../../../services/models';
import { PaintingService } from '../../../../services/services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-my-paintings',
  templateUrl: './my-paintings.component.html',
  styleUrl: './my-paintings.component.scss'
})
export class MyPaintingsComponent {
  paintingResponse: PageResponsePaintingResponse = {};
  page = 0;
  size = 5;
  pages: any = [];

  constructor(
    private paintingService: PaintingService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.findAllPaintings();
  }

  private findAllPaintings() {
    this.paintingService.findAllPaintingsByOwner({
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

  archivePainting(painting: PaintingResponse) {
    this.paintingService.updateArchivedStatus({
      id: painting.id as number
    }).subscribe({
      next: () => {
        painting.archived = !painting.archived;
      }
    });
  }

  sharePainting(painting: PaintingResponse) {
    this.paintingService.updateShareableStatus({
      id: painting.id as number
    }).subscribe({
      next: () => {
        painting.shareable = !painting.shareable;
      }
    });
  }

  editPainting(painting: PaintingResponse) {
    this.router.navigate(['paintings', 'manage', painting.id]);
  }
}
