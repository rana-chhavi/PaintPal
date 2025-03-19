import { Component } from '@angular/core';
import { PaintingRequest } from '../../../../services/models';
import { PaintingService } from '../../../../services/services';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-manage-painting',
  templateUrl: './manage-painting.component.html',
  styleUrl: './manage-painting.component.scss'
})
export class ManagePaintingComponent {
  errorMsg: Array<string> = [];
  paintingRequest: PaintingRequest = {
    artist: '',
    doi: '',
    info: '',
    title: ''
  };
  selectedPaintingImage: any;
  selectedPicture: string | undefined;

  constructor(
    private paintingService: PaintingService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    const paintingId = this.activatedRoute.snapshot.params['paintingId'];
    if (paintingId) {
      this.paintingService.getPaintingById({
        id: paintingId
      }).subscribe({
        next: (painting: any) => {
         this.paintingRequest = {
           id: painting.id,
           title: painting.title as string,
           artist: painting.artist as string,
           doi: painting.doi as string,
           info: painting.info as string,
           shareable: painting.shareable
         };
         this.selectedPicture='data:image/jpg;base64,' + painting.image;
        }
      });
    }
  }

  savePainting() {
    this.paintingService.savePainting({
      body: this.paintingRequest
    }).subscribe({
      next: (paintingId) => {
        this.paintingService.uploadPaintingImage({
          'id': paintingId,
          body: {
            file: this.selectedPaintingImage
          }
        }).subscribe({
          next: () => {
            this.router.navigate(['/paintings/my-paintings']);
          }
        });
      },
      error: (err) => {
        console.log(err.error);
        this.errorMsg = err.error.validationErrors;
      }
    });
  }

  onFileSelected(event: any) {
    this.selectedPaintingImage = event.target.files[0];
    console.log(this.selectedPaintingImage);

    if (this.selectedPaintingImage) {

      const reader = new FileReader();
      reader.onload = () => {
        this.selectedPicture = reader.result as string;
      };
      reader.readAsDataURL(this.selectedPaintingImage);
    }
  }
}
