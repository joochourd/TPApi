package clases;

import java.time.LocalDate;

public abstract class Simple extends Reclamo {

	public Simple(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr) {
		super(fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}
	
	public void cambiarEstado(){}

}
