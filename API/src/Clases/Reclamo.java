package Clases;

import java.time.LocalDate;

public abstract class Reclamo {
	
	public static int numeroReclamoEstatico;
	public Reclamo(int numeroReclamo, LocalDate fecha, String descripcion, Enum<Tipos> tipo) {
		super();
		this.numeroReclamo = ++numeroReclamoEstatico;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.tipo = Tipos.Cantidad;
		this.estado = Estados.Registrado;
	}
	int numeroReclamo ;
	LocalDate fecha;
	String descripcion;
	Enum <Estados> estado;
	Enum <Tipos> tipo;
	
	
	//ACA COMIENZAN LOS SETTERS GETTERS
	
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

}

