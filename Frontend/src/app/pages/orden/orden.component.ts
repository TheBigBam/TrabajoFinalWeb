
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { OrdenService } from "src/app/service/orden.service";
import { MeseroService } from 'src/app/service/mesero.service';
import { ComidaService } from 'src/app/service/comida.service';
import { MesaService } from 'src/app/service/mesa.service';
import { Mesero } from 'src/app/model/mesero';
import { Mesa } from 'src/app/model/mesa';
import { Comida } from 'src/app/model/comida';
import { DetalleOrden } from 'src/app/model/detalle-orden';
import { Orden } from 'src/app/model/orden';

@Component({
  selector: 'app-orden',
  templateUrl: './orden.component.html',
  styleUrls: ['./orden.component.css']
})
export class OrdenComponent implements OnInit {

  meseros: Mesero[] = [];
  mesas: Mesa[] = [];
  comidas: Comida[] = [];

  meseroSeleccionado: Mesero;
  mesaSeleccionada: Mesa;
  nroClientes: number = 0;

  detallesOrden: DetalleOrden[] = [];
  comidaSeleccionada: Comida;
  cantidadComidaSeleccionada: number = 0;

  constructor(private ordenService: OrdenService,
    private meseroService: MeseroService,
    private comidaService: ComidaService,
    private mesaService: MesaService,
    private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.listarMeseros();
    this.listarMesas();
    this.listarComidas();
  }

  listarMeseros(){
    this.meseroService.listar().subscribe(data => {
      this.meseros = data;
    });
  }

  listarMesas(){
    this.mesaService.listar().subscribe(data => {
      this.mesas = data;
    });
  }

  listarComidas(){
    this.comidaService.listar().subscribe(data => {
      this.comidas = data;
    });
  }

  clearControls(){
    this.meseroSeleccionado = null;
    this.mesaSeleccionada = null;
    this.nroClientes = 0;
    this.detallesOrden = [];
    this.comidaSeleccionada = null;
    this.cantidadComidaSeleccionada = 0;
  }

  estadoBotonOrden(){
    if(this.meseroSeleccionado === null || this.mesaSeleccionada === null || this.nroClientes <= 0 || this.detallesOrden.length === 0){
      return true;
    }

    return false;
  }

  agregarOrden(){
    let orden = new Orden();

    orden.mesa = this.mesaSeleccionada;
    orden.mesero = this.meseroSeleccionado;

    orden.fecha_generacion = new Date(Date.now()).toISOString();
        
    orden.nro_clientes = this.nroClientes;
    orden.detalle_orden = this.detallesOrden;

    this.ordenService.registrar(orden).subscribe(data => {
      this.snackBar.open("Orden registrada.", "Aviso", { duration: 2000 });
      this.clearControls();
      this.listarMesas();
    });

    console.log(orden);
  }

  mesaOcupada(id: number){
    if(this.mesas[id].estado === "Ocupada"){
      return true
    }

    return false
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

    this.detallesOrden.push(d);
  }

  removerComida(index: number){
    this.detallesOrden.splice(index, 1);
  }

}
/*
xport class ProgramadorComponent implements OnInit {

  lenguajes: LenguajeProgramacion[] = [];
  lenguajesparaagregar: LenguajeProgramacion[] = [];

  idLenguajeSeleccionado: number;
  nombre: string;

  constructor(
    private programadorService: ProgramadorService,
    private lenguajeprogramacionService: LenguajeProgramacionService,
    private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.listarLenguajes();
    this.idLenguajeSeleccionado = 0;
  }

  listarLenguajes(){
    this.lenguajeprogramacionService.listar().subscribe(data => {
      this.lenguajes = data;
    });
  }

  agregarLenguaje(){
    if (this.idLenguajeSeleccionado > 0){

      for(let i = 0; i < this.lenguajesparaagregar.length; ++i){
        let l = this.lenguajesparaagregar[i];
        if (l.id === this.idLenguajeSeleccionado){
          this.snackBar.open("El lenguaje seleccionado ya se encuentra en la lista", "Aviso", { duration: 2000 });
          return;
        }
      }

      let leng = new LenguajeProgramacion();
      leng.id = this.idLenguajeSeleccionado;
      this.lenguajeprogramacionService.listarLenguajePorId(this.idLenguajeSeleccionado).subscribe(data => {
        leng.nombre = data.nombre;
        this.lenguajesparaagregar.push(leng);
      });
    }
  }

  agregarProgramador(){
    let programmer = new Programador();
    programmer.nombre = this.nombre;
    let programmerDTO = new ProgramadorPreferencia();

    programmerDTO.lenguajeprogramacion = this.lenguajesparaagregar;
    programmerDTO.programador = programmer;

    console.log(programmerDTO.lenguajeprogramacion);
    console.log(programmer);

    this.programadorService.registrar(programmerDTO).subscribe();
  }

  limpiarControles(){
    this.lenguajes = [];
    this.lenguajesparaagregar = [];
    this.idLenguajeSeleccionado = 0;
    this.nombre = '';
  }

  estadoBotonRegistrar(){
    if(this.lenguajesparaagregar.length === 0 || this.nombre === ""){
      return true;
    }

    return false;
  }
}
*/