package clases;

import java.util.List;

public class Rol {

	private int id;
	//private Enum<TipoRol> descripcion;
	String descripcion;
	private Enum<TipoReclamo> tipoReclamo;
	
	
	
	/*public Rol(Enum<TipoRol> rol, Enum<TipoReclamo>tipoReclamo) {
		super();
		this.descripcion = rol;
		this.tipoReclamo = tipoReclamo;
	}*/
	public Rol (int id, String descripcion){
		this.id = id;
		this.descripcion = descripcion;
		switch (descripcion) {
		case "responsableFacturacion":
			this.tipoReclamo = TipoReclamo.facturacion;
			break;
		case "responsableDistribucion":
			this.tipoReclamo = TipoReclamo.cantYProdYFalta;
			break;
		case "responsableZonas":
			this.tipoReclamo = TipoReclamo.zona;
			break;
		case "callCenter":
			this.tipoReclamo = null;
			break;
		case "administrador":
			this.tipoReclamo = null;
			break;
		case "consulta":
			this.tipoReclamo = null; //cambiar
			break;
		default:
			break;
		}
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
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
