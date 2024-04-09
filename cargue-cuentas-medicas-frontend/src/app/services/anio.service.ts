import { Injectable } from '@angular/core';
import { CommonService } from './common.service';
import { BASE_ENDPOINT } from '../config/app';
import { HttpClient } from '@angular/common/http';
import { Anio } from '../models/anio';

@Injectable({
  providedIn: 'root'
})
export class AnioService extends CommonService<Anio>{
  protected override  baseEndPoint = BASE_ENDPOINT + '/anios';

  constructor(http: HttpClient){
    super(http);
  }
}
