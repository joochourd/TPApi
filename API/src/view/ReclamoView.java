package view;

import java.time.LocalDate;

import clases.Estados;
import clases.TipoReclamo;

public class ReclamoView {
	
	protected int numeroReclamo ; 
	protected LocalDate fecha;
	protected String descripcion;
	protected String estado;
	protected String tipo;
	protected String Nombre;
	protected String empleadoNombreUsr;
	protected String idCompuesto;
	
	public ReclamoView(){}

	public ReclamoView(int numeroReclamo, LocalDate fecha, String descripcion, String estado, String tipo,
			String nombre, String empleadoNombreUsr, String idCompuesto) {
		super();
		this.numeroReclamo = numeroReclamo;
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.estado = estado;
		this.tipo = tipo;
		this.Nombre = nombre;
		this.empleadoNombreUsr = empleadoNombreUsr;
		this.idCompuesto = idCompuesto;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getEmpleadoNombreUsr() {
		return empleadoNombreUsr;
	}

	public void setEmpleadoNombreUsr(String empleadoNombreUsr) {
		this.empleadoNombreUsr = empleadoNombreUsr;
	}
	
	public String toString() {
		return "nro: " + numeroReclamo + ", fecha: " + fecha + ", descripcion: " + descripcion
				+ ", estado: " + estado + ", tipo: " + tipo;
	}
}
