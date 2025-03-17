import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PaintingRoutingModule } from './painting-routing.module';
import { MainComponent } from './pages/main/main.component';
import { MenuComponent } from './components/menu/menu.component';


@NgModule({
  declarations: [MainComponent, MenuComponent],
  imports: [
    CommonModule,
    PaintingRoutingModule
  ]
})
export class PaintingModule { }
