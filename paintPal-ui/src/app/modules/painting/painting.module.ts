import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PaintingRoutingModule } from './painting-routing.module';
import { MainComponent } from './pages/main/main.component';
import { MenuComponent } from './components/menu/menu.component';
import { PaintingListComponent } from './pages/painting-list/painting-list.component';
import { PaintingCardComponent } from './components/painting-card/painting-card.component';
import { RatingComponent } from './components/rating/rating.component';


@NgModule({
  declarations: [MainComponent, MenuComponent, PaintingListComponent, PaintingCardComponent, RatingComponent],
  imports: [
    CommonModule,
    PaintingRoutingModule
  ]
})
export class PaintingModule { }
