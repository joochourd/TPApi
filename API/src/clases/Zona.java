package clases;

import java.time.LocalDate;

import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class Zona extends Simple {

	private String zona; 

	public Zona(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr, String zona) {
		super(fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr);
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

	@Override
	protected void guardate() throws ConexionException, AccesoException {
		ReclamoDAO.getInstancia().grabarReclamo(this);		
	}

}
