package clases;

import java.time.LocalDate;

public class Zona extends Simple {

	private String zona; 

	public Zona(int numeroReclamo, LocalDate fecha, String descripcion, Enum<Tipo> tipo, String zona) {
		super(numeroReclamo, fecha, descripcion, tipo);
		this.zona = zona;
		// TODO Auto-generated constructor stub
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}

}
