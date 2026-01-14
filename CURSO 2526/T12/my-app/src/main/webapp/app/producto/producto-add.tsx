import React from 'react';
import { useTranslation } from 'react-i18next';
import { Link, useNavigate } from 'react-router';
import { handleServerError, setYupDefaults } from 'app/common/utils';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import { ProductoDTO } from 'app/producto/producto-model';
import axios from 'axios';
import InputRow from 'app/common/input-row/input-row';
import useDocumentTitle from 'app/common/use-document-title';
import * as yup from 'yup';


function getSchema() {
  setYupDefaults();
  return yup.object({
    nombre: yup.string().emptyToNull().max(255),
    precio: yup.number().emptyToNull()
  });
}

export default function ProductoAdd() {
  const { t } = useTranslation();
  useDocumentTitle(t('producto.add.headline'));

  const navigate = useNavigate();

  const useFormResult = useForm({
    resolver: yupResolver(getSchema()),
  });

  const createProducto = async (data: ProductoDTO) => {
    window.scrollTo(0, 0);
    try {
      await axios.post('/api/productos', data);
      navigate('/productos', {
            state: {
              msgSuccess: t('producto.create.success')
            }
          });
    } catch (error: any) {
      handleServerError(error, navigate, useFormResult.setError, t);
    }
  };

  return (<>
    <div className="d-flex flex-wrap mb-4">
      <h1 className="flex-grow-1">{t('producto.add.headline')}</h1>
      <div>
        <Link to="/productos" className="btn btn-secondary">{t('producto.add.back')}</Link>
      </div>
    </div>
    <form onSubmit={useFormResult.handleSubmit(createProducto)} noValidate>
      <InputRow useFormResult={useFormResult} object="producto" field="nombre" />
      <InputRow useFormResult={useFormResult} object="producto" field="precio" />
      <input type="submit" value={t('producto.add.headline')} className="btn btn-primary mt-4" />
    </form>
  </>);
}
