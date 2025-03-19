import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PaintingRoutingModule } from './painting-routing.module';
import { MainComponent } from './pages/main/main.component';
import { MenuComponent } from './components/menu/menu.component';
import { PaintingListComponent } from './pages/painting-list/painting-list.component';
import { PaintingCardComponent } from './components/painting-card/painting-card.component';
import { RatingComponent } from './components/rating/rating.component';
import { MyPaintingsComponent } from './pages/my-paintings/my-paintings.component';
import { ManagePaintingComponent } from './pages/manage-painting/manage-painting.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [MainComponent, MenuComponent, PaintingListComponent, PaintingCardComponent, RatingComponent, MyPaintingsComponent, ManagePaintingComponent],
  imports: [
    CommonModule,
    PaintingRoutingModule,
    FormsModule
  ]
})
export class PaintingModule { }
