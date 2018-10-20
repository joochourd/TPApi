package strategy;

import java.time.LocalDate;

import clases.CantYProdYFalta;
import clases.TipoReclamo;

public class EstrategiaCantidad extends CantYProdYFalta implements EstrategiaAbstracta {

	public EstrategiaCantidad(int numeroReclamo, LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo) {
		super(numeroReclamo, fecha, descripcion, tipo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}

}
