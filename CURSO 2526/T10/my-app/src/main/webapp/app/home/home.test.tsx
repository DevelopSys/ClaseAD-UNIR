import React from 'react';
import Home from './home';


test('should create', () => {
  const { container } = renderWithRouter(<Home />);
  expect(container).toBeTruthy();
});

test('should render title', () => {
  const { container } = renderWithRouter(<Home />);
  expect(container.querySelector('h1')).toHaveTextContent('home.index.headline');
});
