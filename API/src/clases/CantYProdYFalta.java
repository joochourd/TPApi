package clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CantYProdYFalta extends Simple {

	private List<Producto> productos;
	private List<Integer> cantidades;
	private AbstractStrategy strategy;
	
	public CantYProdYFalta(int numeroReclamo, LocalDate fecha, String descripcion, Enum<Tipos> tipo) {
		super(numeroReclamo, fecha, descripcion, tipo);
		this.productos = new ArrayList<Producto>();
		this.cantidades = new ArrayList<Integer>();
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}
	public void setEstrategia(AbstractStrategy strategy) {
		this.strategy = strategy;
	}

}
