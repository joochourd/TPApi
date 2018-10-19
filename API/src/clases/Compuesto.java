package clases;

import java.time.LocalDate;

public class Compuesto extends Reclamo {

	public Compuesto(int numeroReclamo, LocalDate fecha, String descripcion, Enum<Tipo> tipo) {
		super(numeroReclamo, fecha, descripcion, tipo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}
	
	public void addReclamo(int numeroReclamo) {
		Simple simple = Sistema.getInstance().alta;
	}

}
