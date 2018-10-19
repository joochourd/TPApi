package clases;

import java.time.LocalDate;


import java.util.ArrayList;
import java.util.List;

import strategy.EstrategiaAbstracta;

public class CantYProdYFalta extends Simple {

	private List<Producto> productos;
	private List<Integer> cantidades;
	private EstrategiaAbstracta strategy;
	
	public CantYProdYFalta(int numeroReclamo, LocalDate fecha, String descripcion, Enum<Tipo> tipo) {
		super(numeroReclamo, fecha, descripcion, tipo);
		this.productos = new ArrayList<Producto>();
		this.cantidades = new ArrayList<Integer>();
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}

	public void setEstrategia(EstrategiaAbstracta strategy) {
		this.strategy = strategy;
	}

}