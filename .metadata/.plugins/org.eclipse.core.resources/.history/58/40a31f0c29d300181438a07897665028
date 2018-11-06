package clases;

import java.time.LocalDate;
<<<<<<< HEAD

public class CantYProdYFalta extends Simple {
	
	
	public CantYProdYFalta(int numeroReclamo, LocalDate fecha, String descripcion, Enum<Tipos> tipo) {
		super(numeroReclamo, fecha, descripcion, tipo);
		// TODO Auto-generated constructor stub
	}


=======
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
