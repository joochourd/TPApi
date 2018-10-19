package clases;

import java.time.LocalDate;

public abstract class Simple extends Reclamo {

	public Simple(int numeroReclamo, LocalDate fecha, String descripcion, Enum<Tipo> tipo) {
		super(numeroReclamo, fecha, descripcion, tipo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}
	
	public void cambiarEstado(){}

}
