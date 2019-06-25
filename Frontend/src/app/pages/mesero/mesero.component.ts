import { Component, OnInit } from '@angular/core';
import { MeseroService } from 'src/app/service/mesero.service';
import { MatSnackBar } from '@angular/material';
import { Mesero } from 'src/app/model/mesero';

@Component({
  selector: 'app-mesero',
  templateUrl: './mesero.component.html',
  styleUrls: ['./mesero.component.css']
})
export class MeseroComponent implements OnInit {
  maxFecha: Date = new Date();

  nombre: string = "";
  apellido: string = "";
  dni: string = "";
  fecha_nacimiento: Date = new Date();

  constructor(private meseroService: MeseroService,private snackBar: MatSnackBar) { }

  ngOnInit() {
  }
  
  clearControls(){
    this.nombre = "";
    this.apellido = "";
    this.dni = "";
    this.fecha_nacimiento = new Date();
  }

  agregarMesero(){
    let mesero = new Mesero();
    mesero.nombre = this.nombre;
    mesero.apellido = this.apellido;
    mesero.dni = this.dni;

    let tzoffset = (this.fecha_nacimiento).getTimezoneOffset() * 60000;
    let localISOTime = (new Date(Date.now() - tzoffset)).toISOString()

    mesero.fecha_nacimiento = localISOTime;

    this.meseroService.registrar(mesero).subscribe(data => {
      this.snackBar.open("Mesero registrado.", "Aviso", { duration: 2000 });
      this.clearControls();
    });
  }
  
  estadoBotonMesero(){
    if(!(this.dni.length === 8)){
      return true;
    }

    for(let c of this.dni){
      if(!(c === '0' || c === '1' || c === '2' || c === '3' || c === '4' || c === '5' || c === '6' || c === '7' || c === '8' || c === '9')){
        return true;
      }
    }

    if(this.nombre === "" || this.apellido == ""){
      return true;
    }

    return false;
  }
}
