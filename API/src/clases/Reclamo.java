package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public abstract class Reclamo{
	
	public Reclamo(int numeroReclamo, LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo) {
		super();
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.tipo = TipoReclamo.Cantidad;
		this.estado = Estados.Registrado;
		this.historial = new ArrayList<ActualizacionEstado>();
	}
	private int numeroReclamo ; 
	private LocalDate fecha;
	private String descripcion;
	private Enum <Estados> estado;
	private Enum <TipoReclamo> tipo;
	

	List <ActualizacionEstado> historial;
	
	
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
	
	private void saveINBD() {
		
	}


}

