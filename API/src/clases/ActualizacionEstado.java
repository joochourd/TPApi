package clases;

import java.time.LocalDate;

import dao.ActualizacionEstadoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class ActualizacionEstado {
	private LocalDate fecha;
	private String descripcion;
	private Enum <Estados> estado;
	private String nombreUsrEmpleado;
	
	public ActualizacionEstado(LocalDate fecha, String descripcion, Enum <Estados> estado, String nombreUsrEmpleado) {
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.estado = estado;
		this.nombreUsrEmpleado = nombreUsrEmpleado;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Enum<Estados> getEstado() {
		return estado;
	}

	public String getNombreUsrEmpleado() {
		return nombreUsrEmpleado;
	}
	
	public void guardate() throws ConexionException, AccesoException{
		ActualizacionEstadoDAO.getInstancia().grabarActualizacionEstado(this);
	}
	
}

