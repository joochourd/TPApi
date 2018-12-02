package clases;

import java.util.Date;

import view.EmpleadoView;

public class Empleado {
	private String nombre;
	private Date fechaNac;
	private String password;
	private String nomUsr;
	private int nroLU;
	private Rol rolOriginal;
	private Rol rolTemporal;
	
	
	
	public Empleado(String nombre, Date fechaNac, String password, String nomUsr, int nroLU, Rol rolOriginal,
			Rol rolTemporal) {
		super();
		this.nombre = nombre;
		this.fechaNac = fechaNac;
		this.password = password;
		this.nomUsr = nomUsr;
		this.nroLU = nroLU;
		this.rolOriginal = rolOriginal;
		this.rolTemporal = rolTemporal;
	}

	public void setRol (Rol rol){}
	
	public void backToRol (){}
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaNac() {
		return fechaNac;
	}


	public String getPassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getNomUsr() {
		return nomUsr;
	}

	public void setNroLU(int nroLU) {
		this.nroLU = nroLU;
	}
	
	public int getNroLU (){
		return  this.nroLU;
	}

	public Rol getRolTemporal() {
		return rolTemporal;
	}
	
	public int getRolTemporalId() {
		return rolTemporal.getId();
	}

	public void setRolTemporal(Rol rolTemporal) {
		this.rolTemporal = rolTemporal;
	}
	
	public void setRolOriginal(Rol rolOriginal){
		this.rolOriginal = rolOriginal;
	}

	public Rol getRolOriginal() {
		return rolOriginal;
	}
	
	public EmpleadoView toView(){
		return new EmpleadoView(this.nombre, this.fechaNac, this.password, this.nomUsr, this.nroLU, this.rolOriginal.toView(), this.rolTemporal.toView());
	}
	
}
