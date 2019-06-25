import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { MesaService } from 'src/app/service/mesa.service';
import { Mesa } from 'src/app/model/mesa';

@Component({
  selector: 'app-mesa',
  templateUrl: './mesa.component.html',
  styleUrls: ['./mesa.component.css']
})
export class MesaComponent implements OnInit {
  capacidad: number = 0;

  constructor(private mesaService: MesaService,private snackBar: MatSnackBar) { }

  ngOnInit() {}

  estadoBotonMesa(){
    if(this.capacidad <= 0){
      return true;
    }

    return false;
  }

  clearControls(){
    this.capacidad = 0;
  }

  agregarMesa(){
    let mesa = new Mesa();

    mesa.capacidad = this.capacidad;
    mesa.estado = "Disponible";

    this.mesaService.registrar(mesa).subscribe(data => {
      this.snackBar.open("Mesa registrada.", "Aviso", { duration: 2000 });
      this.clearControls();
    });
  }
}
