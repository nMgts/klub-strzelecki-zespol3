import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignShootersComponent } from './assign-shooters.component';

describe('AssignShootersComponent', () => {
  let component: AssignShootersComponent;
  let fixture: ComponentFixture<AssignShootersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AssignShootersComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AssignShootersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
