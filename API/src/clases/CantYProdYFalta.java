package clases;

import java.time.LocalDate;


import java.util.ArrayList;
import java.util.List;

import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import strategy.EstrategiaAbstracta;

public class CantYProdYFalta extends Simple {

	private List<Producto> productos;
	private List<Integer> cantidades;
	private EstrategiaAbstracta strategy;
	
	public CantYProdYFalta(int numeroReclamo,LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr) {
		super(numeroReclamo, fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr);
		this.productos = new ArrayList<Producto>();
		this.cantidades = new ArrayList<Integer>();
		// TODO Auto-generated constructor stub
	}
	
	public CantYProdYFalta(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr) {
		super(fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr);
		this.productos = new ArrayList<Producto>();
		this.cantidades = new ArrayList<Integer>();
	}

	

	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}

	public void setEstrategia(EstrategiaAbstracta strategy) {
		this.strategy = strategy;
	}



	@Override
	protected void guardate() throws ConexionException, AccesoException {
		ReclamoDAO.getInstancia().grabarReclamo(this);
		
	}

}