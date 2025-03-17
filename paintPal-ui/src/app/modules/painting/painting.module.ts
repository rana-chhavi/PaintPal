import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PaintingRoutingModule } from './painting-routing.module';
import { MainComponent } from './pages/main/main.component';
import { MenuComponent } from './components/menu/menu.component';
import { PaintingListComponent } from './pages/painting-list/painting-list.component';


@NgModule({
  declarations: [MainComponent, MenuComponent, PaintingListComponent],
  imports: [
    CommonModule,
    PaintingRoutingModule
  ]
})
export class PaintingModule { }
