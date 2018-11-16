package clases;

import java.time.LocalDate;


import java.util.ArrayList;
import java.util.List;

import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import strategy.EstrategiaAbstracta;

public class CantYProdYFalta extends Simple {

	private Producto producto;
	private int cantidad;
	private EstrategiaAbstracta strategy;
	
	public CantYProdYFalta(int numeroReclamo,LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr, Producto prod, int cant) {
		super(numeroReclamo, fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr);
		this.producto = prod;
		this.cantidad = cant;
		// TODO Auto-generated constructor stub
	}
	
	public CantYProdYFalta(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr, Producto prod, int cant) {
		super(fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr);
		this.producto = prod;
		this.cantidad = cant;
	}

	

	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}

	public void setEstrategia(EstrategiaAbstracta strategy) {
		this.strategy = strategy;
	}

	

	public Producto getProducto() {
		return producto;
	}

	
	
	public int getCantidad() {
		return cantidad;
	}

	@Override
	protected void guardate() throws ConexionException, AccesoException {
		ReclamoDAO.getInstancia().grabarReclamo(this);
		
	}

}