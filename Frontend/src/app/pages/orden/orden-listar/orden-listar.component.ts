import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatSnackBar } from '@angular/material';
import { OrdenService } from 'src/app/service/orden.service';
import { Orden } from 'src/app/model/orden';
import { FacturaService } from 'src/app/service/factura.service';
import { Factura } from 'src/app/model/factura';

@Component({
  selector: 'app-orden-listar',
  templateUrl: './orden-listar.component.html',
  styleUrls: ['./orden-listar.component.css']
})
export class OrdenListarComponent implements OnInit {

  dataSource: MatTableDataSource<Orden>
  displayedColumns=['id','mesa','mesero','nro_clientes','fecha','detalle_orden','activa','acciones'];
  activas: number[] = [];
  idx: number;

  constructor(private ordenService: OrdenService,private facturaService: FacturaService, private snackBar: MatSnackBar) { }

  ngOnInit() {

  }

  filtrarTodas(){
    this.idx = 0;
    this.activas = [];
    this.ordenService.listar().subscribe(data => {
      for(let i in data){
        this.ordenService.isActiva(data[i].id).subscribe(data => {
          this.activas.push(data);
        });
      }
      this.dataSource = new MatTableDataSource(data);
    });
  }

  filtrarActivas(){
    this.idx = 1;
    this.activas = [];
    this.ordenService.listarActivas().subscribe(data => {
      for(let i in data){
        this.ordenService.isActiva(data[i].id).subscribe(data => {
          this.activas.push(data);
        });
      }
      this.dataSource = new MatTableDataSource(data);
    });
  }

  ordenFinalizada(id: number){
    if(this.activas[id] === 1){
      return false;
    }

    return true;
  }

  isActiva(i: number){
    return this.activas[i];
  }

  emitirFactura(orden: Orden){
    let factura = new Factura();
    factura.orden = orden;
  
    factura.fecha_generacion = new Date(Date.now()).toISOString();

    console.log(factura);
  
    this.facturaService.registrar(factura).subscribe(data => {
      this.snackBar.open("Factura emitida.", "Aviso", { duration: 2000 });
      if(this.idx = 0){
        this.filtrarTodas();
      }
      else{
        this.filtrarActivas();
      }
    });
  }

}
