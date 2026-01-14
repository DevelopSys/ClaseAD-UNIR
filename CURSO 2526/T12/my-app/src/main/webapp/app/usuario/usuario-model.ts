export class UsuarioDTO {

  constructor(data:Partial<UsuarioDTO>) {
    Object.assign(this, data);
  }

  id?: number|null;
  nombre?: string|null;
  pass?: string|null;
  correo?: string|null;
  productoid?: number|null;

}
