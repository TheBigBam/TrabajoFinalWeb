import { Mesa } from './mesa';
import { DetalleOrden } from './detalle-orden';
import { Mesero } from './mesero';

export class Orden {
    id: number;
	mesa: Mesa;
	mesero: Mesero;
	fecha_generacion: string;
	nro_clientes: number;
	detalle_orden: DetalleOrden[];
}
