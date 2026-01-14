import { useEffect } from 'react';
import { useTranslation } from 'react-i18next';


const useDocumentTitle = (title?: string) => {
  const { t } = useTranslation();
  const titleSuffix = t('app.title');

  useEffect(() => {
    if (title) {
      document.title = title + ' - ' + titleSuffix;
    } else {
      document.title = titleSuffix;
    }
  }, [title]);
};

export default useDocumentTitle;
