import { TestBed } from '@angular/core/testing';

import { ShootersService } from './shooter.service';

describe('ShootersService', () => {
  let service: ShootersService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ShootersService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
