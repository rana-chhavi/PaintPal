import { Component, EventEmitter, Input, Output } from '@angular/core';
import { PaintingResponse } from '../../../../services/models';

@Component({
  selector: 'app-painting-card',
  templateUrl: './painting-card.component.html',
  styleUrl: './painting-card.component.scss'
})
export class PaintingCardComponent {
  private _painting: PaintingResponse = {};
  private _manage = false;
  private _paintingImage: string | undefined;

  get paintingImage(): string | undefined {
      if (this._painting.image) {
        return 'data:image/jpg;base64,' + this._painting.image
      }
    return 'https://images.unsplash.com/photo-1734760444698-ce341bfd1636?q=80&w=2888&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D';
  }

  get painting(): PaintingResponse {
    return this._painting;
  }

  @Input()
  set painting(value: PaintingResponse) {
    this._painting = value;
  }


  get manage(): boolean {
    return this._manage;
  }

  @Input()
  set manage(value: boolean) {
    this._manage = value;
  }

  @Output() private share: EventEmitter<PaintingResponse> = new EventEmitter<PaintingResponse>();
  @Output() private archive: EventEmitter<PaintingResponse> = new EventEmitter<PaintingResponse>();
  @Output() private addToWaitingList: EventEmitter<PaintingResponse> = new EventEmitter<PaintingResponse>();
  @Output() private borrow: EventEmitter<PaintingResponse> = new EventEmitter<PaintingResponse>();
  @Output() private edit: EventEmitter<PaintingResponse> = new EventEmitter<PaintingResponse>();
  @Output() private details: EventEmitter<PaintingResponse> = new EventEmitter<PaintingResponse>();

  onShare() {
    this.share.emit(this._painting);
  }

  onArchive() {
    this.archive.emit(this._painting);
  }

  onAddToWaitingList() {
    this.addToWaitingList.emit(this._painting);
  }

  onBorrow() {
    this.borrow.emit(this._painting);
  }

  onEdit() {
    this.edit.emit(this._painting);
  }

  onShowDetails() {
    this.details.emit(this._painting);
  }
}
