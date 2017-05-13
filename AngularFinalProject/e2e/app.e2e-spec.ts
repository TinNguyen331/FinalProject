import { AngularFinalProjectPage } from './app.po';

describe('angular-final-project App', () => {
  let page: AngularFinalProjectPage;

  beforeEach(() => {
    page = new AngularFinalProjectPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
