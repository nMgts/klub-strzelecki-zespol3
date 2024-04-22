import { News } from './news';

describe('Shooter', () => {
  it('should create an instance', () => {
    expect(new News(1, 'Sample Title', 'Sample Content')).toBeTruthy();
  });
});
