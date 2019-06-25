import { Component, OnInit } from '@angular/core';
import { Orden } from 'src/app/model/orden';
import { OrdenService } from 'src/app/service/orden.service';
import { FormGroup } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { DetalleOrden } from 'src/app/model/detalle-orden';
import { ComidaService } from 'src/app/service/comida.service';
import { Comida } from 'src/app/model/comida';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-orden-modificar',
  templateUrl: './orden-modificar.component.html',
  styleUrls: ['./orden-modificar.component.css']
})
export class OrdenModificarComponent implements OnInit {

  id: number;
  form: FormGroup;

  orden: Orden;

  comidas: Comida[] = [];

  comidaSeleccionada: Comida;
  cantidadComidaSeleccionada: number = 0;

  constructor(private route: ActivatedRoute, private router:Router, private ordenService: OrdenService, private comidaService: ComidaService, private snackBar: MatSnackBar) {}

  ngOnInit() {
    this.orden = new Orden();

    this.route.params.subscribe((params: Params) => {
      this.id = params['id'];
      this.ordenService.listarOrdenPorId(this.id).subscribe(data => { this.orden = data; });
    });

    this.listarComidas();
  }

  listarComidas(){
    this.comidaService.listar().subscribe(data => {
      this.comidas = data;
    });
  }

  estadoBotonComida(){
    if(this.comidaSeleccionada === null || this.cantidadComidaSeleccionada <= 0){
      return true;
    }

    return false;
  }

  agregarComida(){
    let d = new DetalleOrden();
    d.comida = this.comidaSeleccionada;
    d.cantidad = this.cantidadComidaSeleccionada;
    this.orden.detalle_orden.push(d);
  }

  removerComida(index: number){
    this.orden.detalle_orden.splice(index, 1);
  }

  aceptar(){
    if(this.orden.detalle_orden.length === 0){
      this.snackBar.open("ERROR: Debe haber al menos una comida en el detalle.", "Aviso", { duration: 2000 });
      return;
    }

    this.ordenService.modificar(this.orden).subscribe(data => {
      this.ordenService.listar().subscribe(ordenes => {
        this.ordenService.ordenCambio.next(ordenes);
      });
      this.snackBar.open("Orden modificada con éxito.", "Aviso", { duration: 2000 });
    });
    this.router.navigate(['ordenes/listar']);
  }
}


/*
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatSnackBar } from '@angular/material';
import { Paciente } from 'src/app/_model/paciente';
import { PacienteService } from 'src/app/_service/paciente.service';

@Component({
  selector: 'app-paciente',
  templateUrl: './paciente.component.html',
  styleUrls: ['./paciente.component.css']
})
export class PacienteComponent implements OnInit {

  dataSource: MatTableDataSource<Paciente>;
  displayedColumns=['idPaciente','nombres','apellidos','acciones'];


  constructor(private pacienteService: PacienteService, private snackBar: MatSnackBar) { }

  ngOnInit() {

    this.pacienteService.pacienteCambio.subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
    });

    this.pacienteService.mensajeCambio.subscribe(data => {
      this.snackBar.open(data, 'Aviso', { duration: 2000 });
    });

    this.pacienteService.listar().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
    });


  }

  applyFilter(filterValue: string){
    filterValue=filterValue.trim();
    filterValue=filterValue.toLowerCase();
    this.dataSource.filter=filterValue;
  }


}

*/
