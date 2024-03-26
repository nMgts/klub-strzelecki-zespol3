import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShootersTableComponent } from './shooters-table.component';

describe('ShootersTableComponent', () => {
  let component: ShootersTableComponent;
  let fixture: ComponentFixture<ShootersTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ShootersTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ShootersTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
