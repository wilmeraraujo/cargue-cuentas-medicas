import { Injectable } from '@angular/core';
import { TipoServicio } from '../models/tipo-servicio';
import { CommonService } from './common.service';
import { BASE_ENDPOINT } from '../config/app';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TipoServicioService extends CommonService<TipoServicio>{

  protected override  baseEndPoint = BASE_ENDPOINT + '/servicios';

  constructor(http: HttpClient){
    super(http);
  }

}
