import { Injectable } from '@angular/core';
import { BASE_ENDPOINT } from '../config/app';
import {  HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Mes } from '../models/mes';

@Injectable({
  providedIn: 'root'
})
export class MesService {

  private baseEndPoint: string = BASE_ENDPOINT + '/meses';

  private cabeceras: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient) { }

  public listar(): Observable<Mes[]> {
    return this.http.get<Mes[]>(this.baseEndPoint);
  }
 
}
