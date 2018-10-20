package clases;

import java.util.List;

public class Rol {

	private Enum<TipoRol> descripcion;
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
}
