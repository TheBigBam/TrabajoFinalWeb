import { Orden } from './orden';

export class Factura {
    id: number;
    orden: Orden;
	monto_total: number;
	fecha_generacion: string;
}
