package view;

import java.util.Date;

import clases.Rol;
import clases.TipoRol;

public class EmpleadoView {
	private String nombre;
	private Date fechaNac;
	private String password;
	private String nomUsr;
	private int nroLU;
	private RolView rolOriginal;
	private RolView rolTemporal;
	//

	public EmpleadoView(String nombre, Date fechaNac, String password, String nomUsr, int nroLU, RolView rolOriginal, RolView rolTemporal){
		this.nombre = nombre;
		this.fechaNac = fechaNac;
		this.nomUsr = nomUsr;
		this.nroLU = nroLU;
		this.rolOriginal = rolOriginal;
		this.rolTemporal = rolTemporal;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaNac() {
		return fechaNac;
	}
	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	
	public String getNomUsr() {
		return nomUsr;
	}
	public void setNomUsr(String nomUsr) {
		this.nomUsr = nomUsr;
	}
	public int getNroLU() {
		return nroLU;
	}
	public void setNroLU(int nroLU) {
		this.nroLU = nroLU;
	}
	public RolView getRolOriginal() {
		return rolOriginal;
	}
	public void setRolOriginal(RolView rolOriginal) {
		this.rolOriginal = rolOriginal;
	}
	public RolView getRolTemporal() {
		return rolTemporal;
	}
	public void setRolTemporal(RolView rolTemporal) {
		this.rolTemporal = rolTemporal;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "nombre: " + nombre + ", nomUsr: " + nomUsr + ", nroLU: " + nroLU + ", rolOriginal: "
				+ rolOriginal.toString() + ", rolTemporal: " + rolTemporal.toString();
	}

	
}
