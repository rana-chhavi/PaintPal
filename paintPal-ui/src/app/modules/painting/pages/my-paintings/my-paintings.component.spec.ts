import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyPaintingsComponent } from './my-paintings.component';

describe('MyPaintingsComponent', () => {
  let component: MyPaintingsComponent;
  let fixture: ComponentFixture<MyPaintingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MyPaintingsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MyPaintingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
