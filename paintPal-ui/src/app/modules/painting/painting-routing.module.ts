import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './pages/main/main.component';
import { PaintingListComponent } from './pages/painting-list/painting-list.component';
import { MyPaintingsComponent } from './pages/my-paintings/my-paintings.component';
import { ManagePaintingComponent } from './pages/manage-painting/manage-painting.component';
import { BorrowedPaintingListComponent } from './pages/borrowed-painting-list/borrowed-painting-list.component';
import { ReturnPaintingsComponent } from './pages/return-paintings/return-paintings.component';
import { authGuard } from '../../services/guard/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      {
        path: '',
        component: PaintingListComponent,
        canActivate: [authGuard]
      },
      {
        path: 'my-paintings',
        component: MyPaintingsComponent,
        canActivate: [authGuard]
      },
      {
        path: 'manage',
        component: ManagePaintingComponent,
        canActivate: [authGuard]
      },
      {
        path: 'manage/:paintingId',
        component: ManagePaintingComponent,
        canActivate: [authGuard]
      },
      {
        path: 'my-borrowed-paintings',
        component: BorrowedPaintingListComponent,
        canActivate: [authGuard]
      },
      {
        path: 'my-returned-paintings',
        component: ReturnPaintingsComponent,
        canActivate: [authGuard]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PaintingRoutingModule { }
