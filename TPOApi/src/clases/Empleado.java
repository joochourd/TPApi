package clases;

import java.util.Date;

public class Empleado {
	private String nombre;
	private Date fechaNac;
	private String password;
	private String nomUsr;
	private int nroLU;
	private Rol rolOriginal;
	private Rol rolTemporal;
	
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

	public void setRolTemporal(Rol rolTemporal) {
		this.rolTemporal = rolTemporal;
	}
	
	public void setRolOriginal(Rol rolOriginal){
		this.rolOriginal = rolOriginal;
	}

	public Rol getRolOriginal() {
		return rolOriginal;
	}

	
}