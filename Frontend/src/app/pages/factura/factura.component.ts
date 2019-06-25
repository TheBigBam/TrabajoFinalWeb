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

  dataSource: MatTableDataSource<Factura>
  displayedColumns=['id','orden','monto','fecha'];

  constructor(private facturaService: FacturaService, private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.facturaService.listar().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
    });
  }
}
