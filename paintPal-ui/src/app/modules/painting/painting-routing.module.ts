import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './pages/main/main.component';
import { PaintingListComponent } from './pages/painting-list/painting-list.component';
import { MyPaintingsComponent } from './pages/my-paintings/my-paintings.component';
import { ManagePaintingComponent } from './pages/manage-painting/manage-painting.component';

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      {
        path: '',
        component: PaintingListComponent
      },
      {
        path: 'my-paintings',
        component: MyPaintingsComponent
      },
      {
        path: 'manage',
        component: ManagePaintingComponent
      },
      {
        path: 'manage/:paintingId',
        component: ManagePaintingComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PaintingRoutingModule { }
