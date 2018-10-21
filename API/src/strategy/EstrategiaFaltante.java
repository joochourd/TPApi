package strategy;

import java.time.LocalDate;

import clases.CantYProdYFalta;
import clases.TipoReclamo;

public class EstrategiaFaltante extends CantYProdYFalta implements EstrategiaAbstracta {

	

	public EstrategiaFaltante(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit,
			String empleadoNombreUsr) {
		super(fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}

}
