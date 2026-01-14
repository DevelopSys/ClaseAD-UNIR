import React from 'react';
import ReactDOM from 'react-dom/client';
import { initReactI18next } from 'react-i18next';
import i18n from 'i18next';
import axios from 'axios';
import translation from './translation.json';
import AppRoutes from './app/routes';
import 'bootstrap';
import './index.scss';


i18n
  .use(initReactI18next)
  .init({
    resources: {
      en: { translation: translation },
    },
    lng: 'en',
    fallbackLng: 'en',
    interpolation: {
      escapeValue: false
    }
  });

axios.defaults.baseURL = process.env.API_PATH;

const root = document.getElementById('root')!!;
ReactDOM.createRoot(root).render(
  <AppRoutes />
);
