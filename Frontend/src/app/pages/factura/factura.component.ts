import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatSnackBar } from '@angular/material';
import { FacturaService } from 'src/app/service/factura.service';
import { Factura } from 'src/app/model/factura';

@Component({
  selector: 'app-factura',
  templateUrl: './factura.component.html',
  styleUrls: ['./factura.component.css']
})
export class FacturaComponent implements OnInit {
  maxFecha: Date = new Date();
  fecha: Date = new Date();

  dataSource: MatTableDataSource<Factura>
  displayedColumns=['id','orden','monto','fecha'];

  constructor(private facturaService: FacturaService, private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.buscarTodas();
  }

  buscarTodas(){
    this.facturaService.listar().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
    });
  }

  buscarPorFecha(){
    this.facturaService.listarFacturasPorFecha(this.fecha.toISOString().slice(0,10)).subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
    });
  }
}
