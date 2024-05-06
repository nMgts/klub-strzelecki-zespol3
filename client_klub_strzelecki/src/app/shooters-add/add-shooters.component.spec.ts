import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddShooterComponent } from './add-shooters.component';

describe('AddShooterComponent', () => {
  let component: AddShooterComponent;
  let fixture: ComponentFixture<AddShooterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddShooterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddShooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
