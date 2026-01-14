import React, { useEffect, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { Link, useNavigate } from 'react-router';
import { handleServerError } from 'app/common/utils';
import { UsuarioDTO } from 'app/usuario/usuario-model';
import axios from 'axios';
import useDocumentTitle from 'app/common/use-document-title';


export default function UsuarioList() {
  const { t } = useTranslation();
  useDocumentTitle(t('usuario.list.headline'));

  const [usuarios, setUsuarios] = useState<UsuarioDTO[]>([]);
  const navigate = useNavigate();

  const getAllUsuarios = async () => {
    try {
      const response = await axios.get('/api/usuarios');
      setUsuarios(response.data);
    } catch (error: any) {
      handleServerError(error, navigate);
    }
  };

  const confirmDelete = async (id: number) => {
    if (!confirm(t('delete.confirm'))) {
      return;
    }
    try {
      await axios.delete('/api/usuarios/' + id);
      navigate('/usuarios', {
            state: {
              msgInfo: t('usuario.delete.success')
            }
          });
      getAllUsuarios();
    } catch (error: any) {
      handleServerError(error, navigate);
    }
  };

  useEffect(() => {
    getAllUsuarios();
  }, []);

  return (<>
    <div className="d-flex flex-wrap mb-4">
      <h1 className="flex-grow-1">{t('usuario.list.headline')}</h1>
      <div>
        <Link to="/usuarios/add" className="btn btn-primary ms-2">{t('usuario.list.createNew')}</Link>
      </div>
    </div>
    {!usuarios || usuarios.length === 0 ? (
    <div>{t('usuario.list.empty')}</div>
    ) : (
    <div className="table-responsive">
      <table className="table table-striped table-hover align-middle">
        <thead>
          <tr>
            <th scope="col">{t('usuario.id.label')}</th>
            <th scope="col">{t('usuario.nombre.label')}</th>
            <th scope="col">{t('usuario.pass.label')}</th>
            <th scope="col">{t('usuario.correo.label')}</th>
            <th scope="col">{t('usuario.productoid.label')}</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {usuarios.map((usuario) => (
          <tr key={usuario.id}>
            <td>{usuario.id}</td>
            <td>{usuario.nombre}</td>
            <td>{usuario.pass}</td>
            <td>{usuario.correo}</td>
            <td>{usuario.productoid}</td>
            <td>
              <div className="float-end text-nowrap">
                <Link to={'/usuarios/edit/' + usuario.id} className="btn btn-sm btn-secondary">{t('usuario.list.edit')}</Link>
                <span> </span>
                <button type="button" onClick={() => confirmDelete(usuario.id!)} className="btn btn-sm btn-secondary">{t('usuario.list.delete')}</button>
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
