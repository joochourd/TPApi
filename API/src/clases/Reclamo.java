package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.accessibility.AccessibleRelationSet;

import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
//

public abstract class Reclamo{
	
	public Reclamo(int numeroReclamo, LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr) {
		super();
		this.fecha = fecha;
		this.numeroReclamo = numeroReclamo;
		this.descripcion = descripcion;
		this.tipo = TipoReclamo.Cantidad;
		this.estado = Estados.Registrado;
		this.clienteDniCuit = clienteDniCuit;
		this.empleadoNombreUsr = empleadoNombreUsr;
		this.historial = new ArrayList<ActualizacionEstado>();
	}
	
	/*public Reclamo(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr) {
		super();
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.tipo = TipoReclamo.Cantidad;
		this.estado = Estados.Registrado;
		this.clienteDniCuit = clienteDniCuit;
		this.empleadoNombreUsr = empleadoNombreUsr;
		this.historial = new ArrayList<ActualizacionEstado>();
	}*/ //no borrar
	
	protected int numeroReclamo ; 
	protected LocalDate fecha;
	protected String descripcion;
	protected Enum <Estados> estado;
	protected Enum <TipoReclamo> tipo;
	protected int clienteDniCuit;
	protected String empleadoNombreUsr;

	protected List <ActualizacionEstado> historial;
	
	
	//ACA COMIENZAN LOS SETTERS GETTERS
	
	public List<ActualizacionEstado> getHistorial() {
		return historial;
	}
	public int getNumeroReclamo() {
		return numeroReclamo;
	}
	public void setNumeroReclamo(int numeroReclamo) {
		this.numeroReclamo = numeroReclamo;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Enum<Estados> getEstado() {
		return estado;
	}
	public void setEstado(Enum<Estados> estado) {
		this.estado = estado;
	}
	public Enum<TipoReclamo> getTipo() {
		return tipo;
	}
	public void setTipo(Enum<TipoReclamo> tipo) {
		this.tipo = tipo;
	}
	public int numeroReclamo() {
		return this.numeroReclamo;
	}
	
	
	// ACA TERMIAN LOS SETTERS / GETTERS
	
	public String getEmpleadoNombreUsr() {
		return empleadoNombreUsr;
	}
	public void setEmpleadoNombreUsr(String empleadoNombreUsr) {
		this.empleadoNombreUsr = empleadoNombreUsr;
	}
	public abstract void accion();
	
	public boolean estaFinalizado(){
		if (this.estado.equals(Estados.Resuelto) || this.estado.equals(Estados.Cerrado)){
			return true;
		}
		return false;
	}
	public boolean consultar(){
		return false;
		
	}
	public void modificate() throws ConexionException, AccesoException{
		ReclamoDAO.getInstancia().modificarReclamo(this);
	}
	
	protected abstract void guardate() throws ConexionException, AccesoException;
	
	public void guardarActuralizacionEstado(){
		ActualizacionEstado actEst = new ActualizacionEstado(int idReclamo, LocalDate fecha, String descripcion, Enum<Estados> estado, String nombreUsrEmpleado);
		actEst.guardate();
	}
}

