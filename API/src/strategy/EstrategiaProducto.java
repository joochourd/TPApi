package strategy;

import java.time.LocalDate;

import clases.CantYProdYFalta;
import clases.Tipo;

public class EstrategiaProducto extends CantYProdYFalta implements EstrategiaAbstracta {

	public EstrategiaProducto(int numeroReclamo, LocalDate fecha, String descripcion, Enum<Tipo> tipo) {
		super(numeroReclamo, fecha, descripcion, tipo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}

}
