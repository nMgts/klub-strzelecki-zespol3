import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompetitionDetailsDialogComponent } from './competition-details-dialog.component';

describe('CompetitionDetailsDialogComponent', () => {
  let component: CompetitionDetailsDialogComponent;
  let fixture: ComponentFixture<CompetitionDetailsDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CompetitionDetailsDialogComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CompetitionDetailsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
