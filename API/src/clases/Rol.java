package clases;

import java.util.List;

public class Rol {

	private int id;
	private Enum<TipoRol> descripcion;
	private Enum<TipoReclamo> tipoReclamo;
	
	
	
	public Rol(Enum<TipoRol> rol, Enum<TipoReclamo>tipoReclamo) {
		super();
		this.descripcion = rol;
		this.tipoReclamo = tipoReclamo;
	}
	
	public Enum<TipoRol> getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(Enum<TipoRol> descripcion) {
		this.descripcion = descripcion;
	}
	public Enum<TipoReclamo> getTipoReclamo() {
		return tipoReclamo;
	}
	public void setTipoReclamo(Enum<TipoReclamo> tipoReclamo) {
		this.tipoReclamo = tipoReclamo;
	}
	public List<Empleado> getUsr() {
		return usr;
	}
	public void setUsr(List<Empleado> usr) {
		this.usr = usr;
	}
	public List<Enum> getPermisos() {
		return permisos;
	}
	public void setPermisos(List<Enum> permisos) {
		this.permisos = permisos;
	}
	private List<Empleado> usr;
	private List<Enum> permisos;
	
	public void eliminarUsr(int nroLu){
		int i=0;
		for(; i<usr.size(); i++){
			if(nroLu == usr.get(i).getNroLU()){
				break;
			}
		}
		usr.remove(i);
		
		
		/*for(Empleado empleado : usr) {
			if (empleado.getNroLU() == nroLu)  usr.remove(empleado);
		}*/
	}
	public void agregarUsr(Empleado usuario){
		usr.add(usuario);
	}
	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}
