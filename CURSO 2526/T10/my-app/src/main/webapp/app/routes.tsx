import React from 'react';
import { createBrowserRouter, RouterProvider } from 'react-router';
import App from "./app";
import Home from './home/home';
import UsuarioList from './usuario/usuario-list';
import UsuarioAdd from './usuario/usuario-add';
import UsuarioEdit from './usuario/usuario-edit';
import ProductoList from './producto/producto-list';
import ProductoAdd from './producto/producto-add';
import ProductoEdit from './producto/producto-edit';
import Error from './error/error';


export default function AppRoutes() {
  const router = createBrowserRouter([
    {
      element: <App />,
      children: [
        { path: '', element: <Home /> },
        { path: 'usuarios', element: <UsuarioList /> },
        { path: 'usuarios/add', element: <UsuarioAdd /> },
        { path: 'usuarios/edit/:id', element: <UsuarioEdit /> },
        { path: 'productos', element: <ProductoList /> },
        { path: 'productos/add', element: <ProductoAdd /> },
        { path: 'productos/edit/:id', element: <ProductoEdit /> },
        { path: 'error', element: <Error /> },
        { path: '*', element: <Error /> }
      ]
    }
  ]);

  return (
    <RouterProvider router={router} />
  );
}
