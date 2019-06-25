import { Injectable } from '@angular/core';
import { HOST } from './../shared/var.constant';
import { HttpClient } from '@angular/common/http';
import { Mesero } from '../model/Mesero';

@Injectable({
  providedIn: 'root'
})
export class MeseroService {

  url: string = `${HOST}/meseros`;

  constructor(private http: HttpClient) { }

  listar(){
    return this.http.get<Mesero[]>(this.url);
  }

  listarMeseroPorId(id: number) {
    return this.http.get<Mesero>(`${this.url}/${id}`);
  }

  registrar(mesero: Mesero) {
    return this.http.post(this.url, mesero);
  }

  modificar(mesero: Mesero) {
    return this.http.put(this.url, mesero);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.url}/${id}`);
  }
}
