package clases;

import java.util.List;

import observador.ObservableTablero;


public class Tablero  extends ObservableTablero {
	private List <Reclamo> reclamos;

/*Agregar restrincciones de roles*/
	public void crearReclamoZona(){}
	public void crearReclamoFacturacion(){}
	public void crearReclamoCantYProdYFalta(){}
	public void crearReclamoCompuesto(){}
	public void tratarReclamoZona(){}
	public void tratarReclamoFacturacion(){}
	public void tratarCantYPordYFalta(){}
	public void tratarReclamoCompuesto(){}
	public void getReclamos(Rol rol, Tipo tipoReclamo){//ver como traer todos los reclamos
		
	}
	
}
