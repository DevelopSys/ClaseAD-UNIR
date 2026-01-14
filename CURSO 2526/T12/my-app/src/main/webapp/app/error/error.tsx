import React from 'react';
import { useLocation } from 'react-router';
import { useTranslation } from 'react-i18next';
import { getReasonPhrase } from 'http-status-codes';
import useDocumentTitle from 'app/common/use-document-title';


export default function Error() {
  const { t } = useTranslation();
  useDocumentTitle(t('error.page.headline'));

  const location = useLocation();
  let status = '404';
  let error = getReasonPhrase(status);

  // keep 404 for every url except /error
  if (location.pathname === '/error') {
    status = location.state?.errorStatus || '503';
    error = location.state?.errorMessage || getReasonPhrase(status);
  }

  return (<>
    <h1 className="mb-4">{status} - {error}</h1>
    <p>{t('error.page.message')}</p>
  </>);
}
