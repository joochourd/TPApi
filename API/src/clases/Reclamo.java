package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import observador.ObservableReclamo;

public abstract class Reclamo extends ObservableReclamo {
	
	public Reclamo(int numeroReclamo, LocalDate fecha, String descripcion, Enum<Tipos> tipo) {
		super();
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.tipo = Tipos.Cantidad;
		this.estado = Estados.Registrado;
		this.historial = new ArrayList<ActualizacionEstado>();
	}
	int numeroReclamo ; 
	LocalDate fecha;
	String descripcion;
	Enum <Estados> estado;
	Enum <Tipos> tipo;
	

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
		this.updateObserver(estado);
	}
	public Enum<Tipos> getTipo() {
		return tipo;
	}
	public void setTipo(Enum<Tipos> tipo) {
		this.tipo = tipo;
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

}

