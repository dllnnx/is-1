import React from 'react';
import ReactDOM from 'react-dom/client';
import { ApiProvider } from '@reduxjs/toolkit/query/react';
import { BrowserRouter } from 'react-router-dom';
import './app.css';
import { Provider } from 'react-redux';
import { store } from './store/store';
import { Toaster } from 'react-hot-toast';
import { ApplicationRoutes } from '~/routes';
import { api } from '~/baseApi';
import 'tippy.js/dist/tippy.css';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement,
);

root.render(
  <React.StrictMode>
    <Toaster />
    <ApiProvider api={api}>
      <Provider store={store}>
        <BrowserRouter>
          <ApplicationRoutes />
        </BrowserRouter>
      </Provider>
    </ApiProvider>
  </React.StrictMode>,
);
