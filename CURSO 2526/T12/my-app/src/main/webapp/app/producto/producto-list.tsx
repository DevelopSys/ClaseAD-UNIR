import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { Link, useNavigate } from 'react-router';
import { handleServerError } from 'app/common/utils';
import { ProductoDTO } from 'app/producto/producto-model';
import axios from 'axios';
import useDocumentTitle from 'app/common/use-document-title';


export default function ProductoList() {
  const { t } = useTranslation();
  useDocumentTitle(t('producto.list.headline'));

  const [productoes, setProductoes] = useState<ProductoDTO[]>([]);
  const navigate = useNavigate();

  const getAllProductoes = async () => {
    try {
      const response = await axios.get('/api/productos');
      setProductoes(response.data);
    } catch (error: any) {
      handleServerError(error, navigate);
    }
  };

  const confirmDelete = async (id: number) => {
    if (!confirm(t('delete.confirm'))) {
      return;
    }
    try {
      await axios.delete('/api/productos/' + id);
      navigate('/productos', {
            state: {
              msgInfo: t('producto.delete.success')
            }
          });
      getAllProductoes();
    } catch (error: any) {
      if (error?.response?.data?.code === 'REFERENCED') {
        const messageParts = error.response.data.message.split(',');
        navigate('/productos', {
              state: {
                msgError: t(messageParts[0]!, { id: messageParts[1]! })
              }
            });
        return;
      }
      handleServerError(error, navigate);
    }
  };

  useEffect(() => {
    getAllProductoes();
  }, []);

  return (<>
    <div className="d-flex flex-wrap mb-4">
      <h1 className="flex-grow-1">{t('producto.list.headline')}</h1>
      <div>
        <Link to="/productos/add" className="btn btn-primary ms-2">{t('producto.list.createNew')}</Link>
      </div>
    </div>
    {!productoes || productoes.length === 0 ? (
    <div>{t('producto.list.empty')}</div>
    ) : (
    <div className="table-responsive">
      <table className="table table-striped table-hover align-middle">
        <thead>
          <tr>
            <th scope="col">{t('producto.id.label')}</th>
            <th scope="col">{t('producto.nombre.label')}</th>
            <th scope="col">{t('producto.precio.label')}</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {productoes.map((producto) => (
          <tr key={producto.id}>
            <td>{producto.id}</td>
            <td>{producto.nombre}</td>
            <td>{producto.precio}</td>
            <td>
              <div className="float-end text-nowrap">
                <Link to={'/productos/edit/' + producto.id} className="btn btn-sm btn-secondary">{t('producto.list.edit')}</Link>
                <span> </span>
                <button type="button" onClick={() => confirmDelete(producto.id!)} className="btn btn-sm btn-secondary">{t('producto.list.delete')}</button>
              </div>
            </td>
          </tr>
          ))}
        </tbody>
      </table>
    </div>
    )}
  </>);
}
