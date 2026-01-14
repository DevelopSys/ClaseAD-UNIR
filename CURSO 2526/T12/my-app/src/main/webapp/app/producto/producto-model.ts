export class ProductoDTO {

  constructor(data:Partial<ProductoDTO>) {
    Object.assign(this, data);
  }

  id?: number|null;
  nombre?: string|null;
  precio?: number|null;

}
