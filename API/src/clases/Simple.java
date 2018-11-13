package clases;

import java.time.LocalDate;

public abstract class Simple extends Reclamo {
protected String idCompuesto = null;
	public Simple(int numeroReclamo,LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr) {
		super(numeroReclamo, fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr);
		// TODO Auto-generated constructor stub
	}
	
	public Simple(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr) {
		super(fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr);
	}

	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}
	
	public String getIdCompuesto() {
		return idCompuesto;
	}

	public void setIdCompuesto(String idCompuesto) {
		this.idCompuesto = idCompuesto;
	}

	public void cambiarEstado(){}

}
