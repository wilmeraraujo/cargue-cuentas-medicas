import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarguesComponent } from './components/cargues/cargues.component';
import { CarguesFormComponent } from './components/cargues/cargues-form.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'cargues'},
  {path:'cargues', component: CarguesComponent},
  {path:'cargues/form', component: CarguesFormComponent},
  {path:'cargues/form/:id', component: CarguesFormComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
