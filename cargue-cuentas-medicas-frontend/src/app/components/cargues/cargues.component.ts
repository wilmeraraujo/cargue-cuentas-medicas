import { Component } from '@angular/core';
import { Cargue } from '../../models/cargue';
import { CargueService } from '../../services/cargue.service';
import { CommonListarComponent } from '../common-listar.component';
import { BASE_ENDPOINT } from '../../config/app';

@Component({
  selector: 'app-cargues',
  templateUrl: './cargues.component.html',
  styleUrl: './cargues.component.css'
})
export class CarguesComponent extends CommonListarComponent<Cargue,CargueService>{

  baseEndPoint = BASE_ENDPOINT + '/paginable';
  sinImagen = '/assets/images/nu.png';

  constructor(service:CargueService){
    super(service);
    this.titulo = 'Listado de Cargues';
    this.nombreModel = Cargue.name;
  }
}
