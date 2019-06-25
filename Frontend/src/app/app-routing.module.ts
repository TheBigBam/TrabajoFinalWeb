import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OrdenComponent } from './pages/orden/orden.component';
import { OrdenListarComponent } from './pages/orden/orden-listar/orden-listar.component';
import { OrdenModificarComponent } from './pages/orden/orden-listar/orden-modificar/orden-modificar.component';
import { FacturaComponent } from './pages/factura/factura.component';
import { MeseroComponent } from './pages/mesero/mesero.component';
import { ComidaComponent } from './pages/comida/comida.component';

const routes: Routes = [
  {path:'ordenes', component:OrdenComponent, children:[
    {path:'listar', component:OrdenListarComponent, children:[
      {path:'modificar/:id', component:OrdenModificarComponent}
    ]},
  ]},
  {path:'facturas', component:FacturaComponent},
  {path:'meseros', component:MeseroComponent},
  {path:'comidas', component:ComidaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
