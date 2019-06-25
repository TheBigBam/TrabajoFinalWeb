import { Injectable } from '@angular/core';
import { HOST } from './../shared/var.constant';
import { HttpClient } from '@angular/common/http';
import { Comida } from '../model/Comida';

@Injectable({
  providedIn: 'root'
})
export class ComidaService {

  url: string = `${HOST}/comidas`;

  constructor(private http: HttpClient) { }

  listar(){
    return this.http.get<Comida[]>(this.url);
  }

  listarComidaPorId(id: number) {
    return this.http.get<Comida>(`${this.url}/${id}`);
  }

  registrar(comida: Comida) {
    return this.http.post(this.url, comida);
  }

  modificar(comida: Comida) {
    return this.http.put(this.url, comida);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.url}/${id}`);
  }
}
