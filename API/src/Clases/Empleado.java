package Clases;

import java.util.Date;

public class Empleado {
	private String nombre;
	private Date fechaNac;
	private String contraseña;
	private String nomUsr;
	private int nroLU;
	private Rol rolOriginal;
	private Rol rolTemporal;
	
	public void setRol (Rol rol){}
	public void backToRol (){}
	public int getNroLU (){
		return  this.nroLU;
	}

}
