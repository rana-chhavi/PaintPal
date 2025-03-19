import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagePaintingComponent } from './manage-painting.component';

describe('ManagePaintingComponent', () => {
  let component: ManagePaintingComponent;
  let fixture: ComponentFixture<ManagePaintingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManagePaintingComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ManagePaintingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
