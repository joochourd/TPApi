package clases;

import java.util.List;

public class Rol {

	private String descripcion;
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
	}
	public void agregarUsr(Empleado usuario){
		usr.add(usuario);
	}
}
