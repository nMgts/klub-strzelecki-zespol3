import { Shooter } from './shooter';

describe('Shooter', () => {
  it('should create an instance', () => {
    expect(new Shooter(1, 'Sample firstName', 'Sample lastName', 'Sample email')).toBeTruthy();
  });
});
