package clases;

import java.time.LocalDate;

import dao.ActualizacionEstadoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
//
public class ActualizacionEstado {
	private int idReclamo;
	private LocalDate fecha;
	private String descripcion;
	private Enum <Estados> estado;
	private String nombreUsrEmpleado;
	

	public ActualizacionEstado(int idReclamo, LocalDate fecha, String descripcion, Enum<Estados> estado, String nombreUsrEmpleado) {
		this.idReclamo = idReclamo;
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

