import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { Link, useNavigate, useParams } from 'react-router';
import { handleServerError, setYupDefaults } from 'app/common/utils';
import { useForm } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import { UsuarioDTO } from 'app/usuario/usuario-model';
import axios from 'axios';
import InputRow from 'app/common/input-row/input-row';
import useDocumentTitle from 'app/common/use-document-title';
import * as yup from 'yup';


function getSchema() {
  setYupDefaults();
  return yup.object({
    nombre: yup.string().emptyToNull().max(255),
    pass: yup.string().emptyToNull().max(255),
    correo: yup.string().emptyToNull().max(255),
    productoid: yup.number().integer().emptyToNull()
  });
}

export default function UsuarioEdit() {
  const { t } = useTranslation();
  useDocumentTitle(t('usuario.edit.headline'));

  const navigate = useNavigate();
  const [productoidValues, setProductoidValues] = useState<Map<number,string>>(new Map());
  const params = useParams();
  const currentId = +params.id!;

  const useFormResult = useForm({
    resolver: yupResolver(getSchema()),
  });

  const getMessage = (key: string) => {
    const messages: Record<string, string> = {
      USUARIO_CORREO_UNIQUE: t('exists.usuario.correo'),
      USUARIO_PRODUCTOID_UNIQUE: t('Exists.usuario.productoid')
    };
    return messages[key];
  };

  const prepareForm = async () => {
    try {
      const productoidValuesResponse = await axios.get('/api/usuarios/productoidValues');
      setProductoidValues(productoidValuesResponse.data);
      const data = (await axios.get('/api/usuarios/' + currentId)).data;
      useFormResult.reset(data);
    } catch (error: any) {
      handleServerError(error, navigate);
    }
  };

  useEffect(() => {
    prepareForm();
  }, []);

  const updateUsuario = async (data: UsuarioDTO) => {
    window.scrollTo(0, 0);
    try {
      await axios.put('/api/usuarios/' + currentId, data);
      navigate('/usuarios', {
            state: {
              msgSuccess: t('usuario.update.success')
            }
          });
    } catch (error: any) {
      handleServerError(error, navigate, useFormResult.setError, t, getMessage);
    }
  };

  return (<>
    <div className="d-flex flex-wrap mb-4">
      <h1 className="flex-grow-1">{t('usuario.edit.headline')}</h1>
      <div>
        <Link to="/usuarios" className="btn btn-secondary">{t('usuario.edit.back')}</Link>
      </div>
    </div>
    <form onSubmit={useFormResult.handleSubmit(updateUsuario)} noValidate>
      <InputRow useFormResult={useFormResult} object="usuario" field="id" disabled={true} type="number" />
      <InputRow useFormResult={useFormResult} object="usuario" field="nombre" />
      <InputRow useFormResult={useFormResult} object="usuario" field="pass" />
      <InputRow useFormResult={useFormResult} object="usuario" field="correo" />
      <InputRow useFormResult={useFormResult} object="usuario" field="productoid" type="select" options={productoidValues} />
      <input type="submit" value={t('usuario.edit.headline')} className="btn btn-primary mt-4" />
    </form>
  </>);
}
