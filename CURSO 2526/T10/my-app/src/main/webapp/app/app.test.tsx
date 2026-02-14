import React from 'react';
import { act } from '@testing-library/react';
import App from './app';


test('should create the app', async () => {
  const { container } = await act(async () => renderWithRouter(<App/>));
  expect(container).toBeTruthy();
});
