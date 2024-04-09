import { Component, OnInit } from '@angular/core';
import { CommonFormComponent } from '../common-form.component';
import { Cargue } from '../../models/cargue';
import { CargueService } from '../../services/cargue.service';
import { TipoServicioService } from '../../services/tipo-servicio.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { TipoServicio } from '../../models/tipo-servicio';
import { AnioService } from '../../services/anio.service';
import { Anio } from '../../models/anio';
import { MesService } from '../../services/mes.service';
import { Mes } from '../../models/mes';

@Component({
  selector: 'app-cargues-form',
  templateUrl: './cargues-form.component.html',
  styleUrl: './cargues-form.component.css'
})
export class CarguesFormComponent extends CommonFormComponent<Cargue, CargueService> implements OnInit {

  private archivoSeleccionado: File;
  tipoServicios: TipoServicio[] = [];
  anios: Anio[] = [];
  meses: Mes[] = [];

  constructor(service: CargueService, 
    private mesService: MesService,
    private tipoServicioService: TipoServicioService,
    private anioService: AnioService,
    router: Router,
    route: ActivatedRoute) {
    super(service, router, route);
    this.titulo = "Crear Cargues";
    this.model = new Cargue();
    this.redirect = '/cargues';
    this.nombreModel = Cargue.name;
  }

  override ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id: number = +params.get('id');
      if(id){
        this.service.ver(id).subscribe(model => {
          this.model = model;
          this.titulo = 'Editar ' + this.nombreModel;         
        });
      }
    });

    this.mesService.listar().subscribe(meses => {
      this.meses = meses;
    })

    this.anioService.listar().subscribe(anios => {
      this.anios = anios;
    })
   
    this.tipoServicioService.listar()
    .subscribe(servicios => {
      this.tipoServicios = servicios;
    });
 
  }

  public seleccionarArchivo(event): void {
    this.archivoSeleccionado = event.target.files[0];
    
    if(this.archivoSeleccionado.type.indexOf('json') < 0){
      this.archivoSeleccionado=null;
      Swal.fire('Error al seleccionar el archivo:', 'El archivo debe ser de tipo json','error');
    }
  }

  public override crear(): void {
    console.log(this.archivoSeleccionado);
    if (!this.archivoSeleccionado) {
      Swal.fire('Error al seleccionar el archivo:', 'Debe seleccionar un archivo valido','error');
    }else{
      
      this.service.crearCargue(this.model,this.archivoSeleccionado).subscribe(cargue => {
        console.log(cargue);
        Swal.fire('Nuevo:',`${this.nombreModel}  creado con exito`,'success');
        this.router.navigate([this.redirect]);
      }, err => {
        if(err.status === 400){
          this.error=err.error;
          console.log(this.error);
        }
      });
    }
  }

  public override editar(): void {    
      this.service.editar(this.model).subscribe(cargue => {
        console.log(cargue);
        Swal.fire('Editado:',`${this.nombreModel} ${cargue.tipoServicio} actualizado con exito`,'success');
        this.router.navigate([this.redirect]);
      }, err => {
        if(err.status === 400){
          this.error=err.error;
          console.log(this.error);
        }
      });    
  }
}
