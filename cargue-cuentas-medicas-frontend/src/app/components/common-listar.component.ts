import { Component, Directive, ViewChild } from '@angular/core';
import { Generic } from '../models/generic';
import { CommonService } from '../services/common.service';
import { MatPaginator, PageEvent } from '@angular/material/paginator';

@Directive()
export abstract class CommonListarComponent<E extends Generic,S extends CommonService<E> > {

  titulo: string;
  lista: E[];
  protected nombreModel: string;

  totalRegistros = 0;
  paginaActual = 0;
  totalPorPagina = 4;
  pageSizeOptions: number[] = [3,5, 10, 25, 100];

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(protected service: S){}

  ngOnInit(): void {
    this.calcularRangos();
  }

  public paginar(event: PageEvent):void{
    this.paginaActual = event.pageIndex;
    this.totalPorPagina = event.pageSize;
    this.calcularRangos();
  }

  private calcularRangos() {
    this.service.listarPaginas(this.paginaActual.toString(), this.totalPorPagina.toString())
      .subscribe(p => {
        this.lista = p.content as E[]
        this.totalRegistros = p.totalElements as number;
        //this.paginator._intl.itemsPerPageLabel= 'Registros po pagina:';
      }
      );
  }
}
