import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditShooterComponent } from './edit-shooters.component';

describe('EditShooterComponent', () => {
  let component: EditShooterComponent;
  let fixture: ComponentFixture<EditShooterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditShooterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EditShooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
