import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BorrowedPaintingListComponent } from './borrowed-painting-list.component';

describe('BorrowedPaintingListComponent', () => {
  let component: BorrowedPaintingListComponent;
  let fixture: ComponentFixture<BorrowedPaintingListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BorrowedPaintingListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BorrowedPaintingListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
