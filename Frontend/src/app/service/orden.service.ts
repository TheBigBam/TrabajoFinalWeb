import { Injectable } from '@angular/core';
import { HOST } from './../shared/var.constant';
import { HttpClient } from '@angular/common/http';
import { Orden } from '../model/orden';
import { Subject } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class OrdenService {

  url: string = `${HOST}/ordenes`;

  ordenCambio=new Subject<Orden[]>();
  mensajeCambio = new Subject<string>();

  constructor(private http: HttpClient) { }

  listar() {
    return this.http.get<Orden[]>(this.url);
  }

  listarActivas() {
    return this.http.get<Orden[]>(`${this.url}/activas/`);
  }

  isActiva(id: number) {
    return this.http.get<number>(`${this.url}/activas/${id}`);
  }

  listarOrdenPorId(id: number) {
    return this.http.get<Orden>(`${this.url}/${id}`);
  }

  listarOrdenesPorFecha(date: Date) {
    return this.http.get<Orden[]>(`${this.url}/fecha/${date}`);
  }

  registrar(orden: Orden) {
    return this.http.post(this.url, orden);
  }

  modificar(orden: Orden) {
    return this.http.put(this.url, orden);
  }
  
  eliminar(id: number) {
    return this.http.delete(`${this.url}/${id}`);
  }
}
