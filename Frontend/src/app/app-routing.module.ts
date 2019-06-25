import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OrdenComponent } from './pages/orden/orden.component';
import { OrdenListarComponent } from './pages/orden/orden-listar/orden-listar.component';
import { OrdenModificarComponent } from './pages/orden/orden-listar/orden-modificar/orden-modificar.component';

const routes: Routes = [
  {path:'ordenes', component:OrdenComponent, children:[
    {path:'listar', component:OrdenListarComponent, children:[
      {path:'modificar/:id', component:OrdenModificarComponent}
    ]},
  ]},
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
