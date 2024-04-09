import { Component, Directive, OnInit } from '@angular/core';
import { Generic } from '../models/generic';
import { CommonService } from '../services/common.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Directive()
export class CommonFormComponent<E extends Generic,S extends CommonService<E>> implements OnInit {

  titulo: string;
  model: E;
  error: any;
  protected redirect: string;
  protected nombreModel: string;

  constructor(protected service: S,
    protected router: Router,
    protected route: ActivatedRoute){}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id: number = +params.get('id');
      if(id){
        this.service.ver(id).subscribe(model => {
          this.model = model;
          this.titulo = 'Editar ' + this.nombreModel;
        });
      }
    })
  }

  public crear(): void{
    this.service.crear(this.model).subscribe(model => {
      console.log(model);
      Swal.fire('Nuevo:',`${this.nombreModel} ${model.id} creado con exito`,'success');
      this.router.navigate([this.redirect]);
    }, err => {
      if(err.status === 400){
        this.error=err.error;
        console.log(this.error);
      }
    });
  }

  public editar(): void{
    this.service.editar(this.model).subscribe(model => {
      console.log(model);
      Swal.fire('Modificado:',`${this.nombreModel} ${model.id} editado con exito`,'success');
      this.router.navigate([this.redirect]);
    }, err => {
      if(err.status === 400){
        this.error=err.error;
        console.log(this.error);
      }
    });
  }


}
