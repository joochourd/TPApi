package strategy;

import java.time.LocalDate;

import clases.Simple;
import clases.Tipos;

public class EstrategiaProducto extends Simple implements EstrategiaAbstracta {

	public EstrategiaProducto(int numeroReclamo, LocalDate fecha, String descripcion, Enum<Tipos> tipo) {
		super(numeroReclamo, fecha, descripcion, tipo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}

}
