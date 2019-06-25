import { Component, OnInit } from '@angular/core';
import { ComidaService } from 'src/app/service/comida.service';
import { MatSnackBar } from '@angular/material';
import { Comida } from 'src/app/model/comida';

@Component({
  selector: 'app-comida',
  templateUrl: './comida.component.html',
  styleUrls: ['./comida.component.css']
})
export class ComidaComponent implements OnInit {

  nombre: string = "";
  descripcion: string = "";
  precio: number = 0;

  constructor(private comidaService: ComidaService,private snackBar: MatSnackBar) { }

  ngOnInit() {}

  estadoBotonComida(){
    if(this.nombre === "" || this.descripcion === "" || this.precio < 0){
      return true;
    }

    return false;
  }

  clearControls(){
    this.nombre = "";
    this.descripcion = "";
    this.precio = 0;
  }

  agregarComida(){
    let comida = new Comida();

    comida.nombre = this.nombre;
    comida.descripcion = this.descripcion;
    comida.precio = this.precio;

    this.comidaService.registrar(comida).subscribe(data => {
      this.snackBar.open("Comida registrada.", "Aviso", { duration: 2000 });
      this.clearControls();
    });
  }
}
