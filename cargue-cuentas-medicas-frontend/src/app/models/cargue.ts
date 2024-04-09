import { EjecucionTarea } from "./ejecucion-tarea";
import { Generic } from "./generic";
import { TipoServicio } from "./tipo-servicio";

export class Cargue implements Generic {
    id: number;
    mes: string;
    anio: string;
    tipoServicio: string;
    nombreArchivo: string;
    ejecucionTarea: EjecucionTarea;
}
