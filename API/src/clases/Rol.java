package clases;

import java.util.List;

import view.ReclamoView;
import view.RolView;

public class Rol {

	private int id;
	private String descripcion;
	private Enum<TipoReclamo> tipoReclamo;
	private List<Empleado> usr;

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
		case "administrador":
		case "consulta":
			this.tipoReclamo = null; //cambiar solo consulta, los otros tienen que seguir siendo null
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

	
	public void eliminarUsr(int nroLu){		
		for(Empleado empleado : usr) {
			if (empleado.getNroLU() == nroLu)  usr.remove(empleado);
		}
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
	
	public RolView toView() {
		RolView rolV = new RolView(this.id, this.descripcion, this.tipoReclamo, this.usr);
		return rolV;
	}
}
