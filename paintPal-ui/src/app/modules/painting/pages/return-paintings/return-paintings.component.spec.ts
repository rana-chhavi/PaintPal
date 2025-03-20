import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReturnPaintingsComponent } from './return-paintings.component';

describe('ReturnPaintingsComponent', () => {
  let component: ReturnPaintingsComponent;
  let fixture: ComponentFixture<ReturnPaintingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReturnPaintingsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReturnPaintingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
