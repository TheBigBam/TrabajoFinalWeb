import { Injectable } from '@angular/core';
import { HOST } from './../shared/var.constant';
import { HttpClient } from '@angular/common/http';
import { Mesa } from '../model/Mesa';

@Injectable({
  providedIn: 'root'
})
export class MesaService {

  url: string = `${HOST}/mesas`;

  constructor(private http: HttpClient) { }

  listar(){
    return this.http.get<Mesa[]>(this.url);
  }

  listarMesaPorId(id: number) {
    return this.http.get<Mesa>(`${this.url}/${id}`);
  }

  registrar(mesa: Mesa) {
    return this.http.post(this.url, mesa);
  }

  modificar(mesa: Mesa) {
    return this.http.put(this.url, mesa);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.url}/${id}`);
  }
}
