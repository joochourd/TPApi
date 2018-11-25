package view;

import java.util.List;

import clases.Empleado;
import clases.TipoReclamo;

public class RolView {
	
	private int id;
	private String descripcion;
	private Enum<TipoReclamo> tipoReclamo;
	private List<Empleado> usr;
	
	
	public RolView(int id, String descripcion, Enum<TipoReclamo> tipoReclamo, List<Empleado> usr) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.tipoReclamo = tipoReclamo;
		this.usr = usr;
	}
	
	


	public int getId() {
		return id;
	}




	public String getDescripcion() {
		return descripcion;
	}




	public Enum<TipoReclamo> getTipoReclamo() {
		return tipoReclamo;
	}




	public List<Empleado> getUsr() {
		return usr;
	}




	@Override
	public String toString() {
		return "id: " + id + ", descripcion: " + descripcion;
	}
	

	
	
}
