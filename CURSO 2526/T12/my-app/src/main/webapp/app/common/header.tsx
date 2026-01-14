import React from 'react';
import { Link } from 'react-router';
import { useTranslation } from 'react-i18next';


export default function Header() {
  const { t } = useTranslation();

  return (
    <header className="bg-light">
      <div className="container">
        <nav className="navbar navbar-light navbar-expand-md">
          <Link to="/" className="navbar-brand">
            <img src="/images/logo.png" alt={t('app.title')} width="30" height="30" className="d-inline-block align-top" />
            <span className="ps-1">{t('app.title')}</span>
          </Link>
          <button type="button" className="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarToggle"
              aria-label={t('navigation.toggle')} aria-controls="navbarToggle" aria-expanded="false">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarToggle">
            <ul className="navbar-nav ms-auto">
              <li className="navbar-item">
                <Link to="/" className="nav-link">{t('navigation.home')}</Link>
              </li>
              <li className="navbar-item dropdown">
                <button type="button" className="nav-link dropdown-toggle" data-bs-toggle="dropdown" id="navbarEntitiesLink"
                    aria-expanded="false">{t('navigation.entities')}</button>
                <ul className="dropdown-menu dropdown-menu-end" aria-labelledby="navbarEntitiesLink">
                  <li><Link to="/usuarios" className="dropdown-item">{t('usuario.list.headline')}</Link></li>
                  <li><Link to="/productos" className="dropdown-item">{t('producto.list.headline')}</Link></li>
                </ul>
              </li>
            </ul>
          </div>
        </nav>
      </div>
    </header>
  );
}
