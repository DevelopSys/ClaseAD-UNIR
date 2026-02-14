import React from 'react';
import { Outlet, useLocation } from 'react-router';
import Header from 'app/common/header';
import ErrorBoundary from 'app/error/error-boundary';
import './app.scss';


/**
 * Provide the app layout and some general functionality.
 */
export default function App() {
  const { state } = useLocation();
  const msgSuccess = state?.msgSuccess || null;
  const msgInfo = state?.msgInfo || null;
  const msgError = state?.msgError || null;

  return (<>
    <Header />
    <main className="my-5">
      <div className="container">
        {msgSuccess && <p className="alert alert-success mb-4" role="alert">{msgSuccess}</p>}
        {msgInfo && <p className="alert alert-info mb-4" role="alert">{msgInfo}</p>}
        {msgError && <p className="alert alert-danger mb-4" role="alert">{msgError}</p>}
        <ErrorBoundary>
          <Outlet/>
        </ErrorBoundary>
      </div>
    </main>
  </>);
}
