package strategy;

import java.time.LocalDate;

import clases.CantYProdYFalta;
import clases.TipoReclamo;
import clases.*;

public class EstrategiaProducto extends CantYProdYFalta implements EstrategiaAbstracta {



	public EstrategiaProducto(int numeroReclamo, LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit,
			String empleadoNombreUsr, Producto producto) {
		super(numeroReclamo, fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr, producto, clienteDniCuit);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}

}
