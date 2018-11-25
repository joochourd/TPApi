package clases;

import java.time.LocalDate;

public abstract class Simple extends Reclamo {
	
	public Simple(int numeroReclamo,LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, Enum<Estados> estado, int clienteDniCuit, String empleadoNombreUsr, String idCompuesto) {
		super(numeroReclamo, fecha, descripcion, tipo, estado, clienteDniCuit, empleadoNombreUsr, idCompuesto);
		// TODO Auto-generated constructor stub
	}
	
	public Simple(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr) {
		super(fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr);
	}

	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}
	

	public void cambiarEstado(){}

}
