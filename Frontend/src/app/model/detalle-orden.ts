import { Orden } from './orden';
import { Comida } from './comida';

export class DetalleOrden {
    id: number;
	orden: Orden;
	comida: Comida;
	cantidad: number;
}
