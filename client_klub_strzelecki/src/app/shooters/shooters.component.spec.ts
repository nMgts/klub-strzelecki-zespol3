import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShootersComponent } from './shooters.component';

describe('ShootersComponent', () => {
  let component: ShootersComponent;
  let fixture: ComponentFixture<ShootersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ShootersComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ShootersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
