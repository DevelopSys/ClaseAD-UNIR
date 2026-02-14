import React, { useEffect } from 'react';
import { useTranslation } from 'react-i18next';
import { Link, useNavigate, useParams } from 'react-router';
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

export default function ProductoEdit() {
  const { t } = useTranslation();
  useDocumentTitle(t('producto.edit.headline'));

  const navigate = useNavigate();
  const params = useParams();
  const currentId = +params.id!;

  const useFormResult = useForm({
    resolver: yupResolver(getSchema()),
  });

  const prepareForm = async () => {
    try {
      const data = (await axios.get('/api/productos/' + currentId)).data;
      useFormResult.reset(data);
    } catch (error: any) {
      handleServerError(error, navigate);
    }
  };

  useEffect(() => {
    prepareForm();
  }, []);

  const updateProducto = async (data: ProductoDTO) => {
    window.scrollTo(0, 0);
    try {
      await axios.put('/api/productos/' + currentId, data);
      navigate('/productos', {
            state: {
              msgSuccess: t('producto.update.success')
            }
          });
    } catch (error: any) {
      handleServerError(error, navigate, useFormResult.setError, t);
    }
  };

  return (<>
    <div className="d-flex flex-wrap mb-4">
      <h1 className="flex-grow-1">{t('producto.edit.headline')}</h1>
      <div>
        <Link to="/productos" className="btn btn-secondary">{t('producto.edit.back')}</Link>
      </div>
    </div>
    <form onSubmit={useFormResult.handleSubmit(updateProducto)} noValidate>
      <InputRow useFormResult={useFormResult} object="producto" field="id" disabled={true} type="number" />
      <InputRow useFormResult={useFormResult} object="producto" field="nombre" />
      <InputRow useFormResult={useFormResult} object="producto" field="precio" />
      <input type="submit" value={t('producto.edit.headline')} className="btn btn-primary mt-4" />
    </form>
  </>);
}
