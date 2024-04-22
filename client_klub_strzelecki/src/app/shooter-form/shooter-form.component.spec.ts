import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShooterFormComponent } from './shooter-form.component';

describe('ShooterFormComponent', () => {
  let component: ShooterFormComponent;
  let fixture: ComponentFixture<ShooterFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShooterFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ShooterFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
