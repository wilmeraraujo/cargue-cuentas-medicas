import { Injectable } from '@angular/core';
import { Cargue } from '../models/cargue';
import { CommonService } from './common.service';
import { BASE_ENDPOINT } from '../config/app';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TipoServicio } from '../models/tipo-servicio';

@Injectable({
  providedIn: 'root'
})
export class CargueService extends CommonService<Cargue>{

  protected override  baseEndPoint = BASE_ENDPOINT + '/cargues';

   constructor(http: HttpClient) {
    super(http);
    }

    public crearCargue(cargue: Cargue,file: File): Observable<any>{
      
      const formData = new FormData();
      formData.append('file',file);
      formData.append('mes',cargue.mes);
      formData.append('anio',cargue.anio);
      formData.append('nombreArchivo',cargue.nombreArchivo);
      formData.append("tipoServicio",cargue.tipoServicio);          
      return this.http.post<any>(this.baseEndPoint + '/procesar-cargue',formData);
    }

    //public editarConFoto(cargue: Cargue): Observable<Cargue>{     
      //return this.http.put<Cargue>(`${this.baseEndPoint}/editar-con-foto/${cargue.id}`);
    //}
}
