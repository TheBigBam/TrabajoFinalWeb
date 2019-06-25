import { Injectable } from '@angular/core';
import { HOST } from '../shared/var.constant';
import { HttpClient } from '@angular/common/http';
import { Factura } from '../model/factura';

@Injectable({
  providedIn: 'root'
})
export class FacturaService {

  url: string = `${HOST}/facturas`;

  constructor(private http: HttpClient) { }

  listar(){
    return this.http.get<Factura[]>(this.url);
  }

  listarFacturaPorId(id: number) {
    return this.http.get<Factura>(`${this.url}/${id}`);
  }

  registrar(factura: Factura) {
    return this.http.post(this.url, factura);
  }

  modificar(factura: Factura) {
    return this.http.put(this.url, factura);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.url}/${id}`);
  }
}
