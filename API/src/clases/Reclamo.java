package clases;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.accessibility.AccessibleRelationSet;

import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
//
import view.ReclamoView;

public abstract class Reclamo{
	
	protected int numeroReclamo ; 
	protected LocalDate fecha;
	protected String descripcion;
	protected Enum <Estados> estado;
	protected Enum <TipoReclamo> tipo;
	protected int clienteDniCuit;
	protected String empleadoNombreUsr;
	protected String idCompuesto;

	protected List <ActualizacionEstado> historial;
	
	public Reclamo(int numeroReclamo, LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, Enum<Estados> estado, int clienteDniCuit, String empleadoNombreUsr, String idCompuesto) {
		super();
		this.fecha = fecha;
		this.numeroReclamo = numeroReclamo;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.estado = estado;
		this.clienteDniCuit = clienteDniCuit;
		this.empleadoNombreUsr = empleadoNombreUsr;
		this.idCompuesto = idCompuesto;
		this.historial = new ArrayList<ActualizacionEstado>();
	}
	
	public Reclamo(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr) {
		super();
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.estado = Estados.Registrado;
		this.clienteDniCuit = clienteDniCuit;
		this.empleadoNombreUsr = empleadoNombreUsr;
		this.historial = new ArrayList<ActualizacionEstado>();
	} 
	
	
	
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
	
	public int getClienteDniCuit() {
		return clienteDniCuit;
	}
	
	public String getIdCompuesto() {
		return idCompuesto;
	}

	public void setIdCompuesto(String idCompuesto) {
		this.idCompuesto = idCompuesto;
	}
	
	// ACA TERMIAN LOS SETTERS / GETTERS
	
	

	public void setClienteDniCuit(int clienteDniCuit) {
		this.clienteDniCuit = clienteDniCuit;
	}

	public void setHistorial(List<ActualizacionEstado> historial) {
		this.historial = historial;
	}

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
	
	protected abstract void guardate() throws ConexionException, AccesoException, SQLException;
	
	public void guardarActuralizacionEstado() throws ConexionException, AccesoException{
		ActualizacionEstado actEst = new ActualizacionEstado(this.getNumeroReclamo(), LocalDate.now(),  this.getDescripcion(),  this.getEstado(), this.getEmpleadoNombreUsr());
		//this.historial.add(actEst);
		actEst.guardate();
	}
	
	public ReclamoView toView() {
		ReclamoView reclamoV = new ReclamoView(this.numeroReclamo, this.fecha, this.descripcion, this.estado.toString(), this.tipo.toString(), String.valueOf(this.clienteDniCuit), this.empleadoNombreUsr, this.idCompuesto);
		return reclamoV;
	}
}

