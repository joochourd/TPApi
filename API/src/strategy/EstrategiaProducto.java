package strategy;

import java.time.LocalDate;

import clases.*;

public class EstrategiaProducto extends CantYProdYFalta implements EstrategiaAbstracta {



	public EstrategiaProducto(int numeroReclamo,LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, Enum<Estados> estado, int clienteDniCuit, String empleadoNombreUsr, Producto producto, int cantidad) {
		super(numeroReclamo, fecha, descripcion, tipo, estado, clienteDniCuit, empleadoNombreUsr, producto, cantidad, empleadoNombreUsr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}

}
