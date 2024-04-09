import { Component } from '@angular/core';
import { CommonListarComponent } from '../common-listar.component';
import { TipoServicio } from '../../models/tipo-servicio';
import { TipoServicioService } from '../../services/tipo-servicio.service';
import { BASE_ENDPOINT } from '../../config/app';

@Component({
  selector: 'app-tipo-servicio',
  templateUrl: './tipo-servicio.component.html',
  styleUrl: './tipo-servicio.component.css'
})
export class TipoServicioComponent extends CommonListarComponent<TipoServicio,TipoServicioService>{

  baseEndPoint = BASE_ENDPOINT + '/paginable';
  sinImagen = '/assets/images/nu.png';

  constructor(service:TipoServicioService){
    super(service);
    this.titulo = 'Listado de Tipos de Servicio';
    this.nombreModel = TipoServicio.name;
  }
}
