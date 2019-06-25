import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { OrdenComponent } from './pages/orden/orden.component';
import { MaterialModule } from './material/material.module';
import { HttpClientModule } from '@angular/common/http';

import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { OrdenListarComponent } from './pages/orden/orden-listar/orden-listar.component';
import { OrdenModificarComponent } from './pages/orden/orden-listar/orden-modificar/orden-modificar.component';
import { FacturaComponent } from './pages/factura/factura.component';
import { MeseroComponent } from './pages/mesero/mesero.component';
import { ComidaComponent } from './pages/comida/comida.component';

@NgModule({
  declarations: [
    AppComponent,
    OrdenComponent,
    OrdenModificarComponent,
    OrdenListarComponent,
    FacturaComponent,
    MeseroComponent,
    ComidaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
