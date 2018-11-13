package clases;

import java.time.LocalDate;

import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class Facturacion extends Simple {
	
	private LocalDate fechaFacturacion;
	private int nroFactura;


	public Facturacion(int numeroReclamo,LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr, LocalDate fechaFacturacion, int nroFactura) {
		super(numeroReclamo, fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr);
		this.fechaFacturacion = fechaFacturacion;
		this.nroFactura = nroFactura;
		// TODO Auto-generated constructor stub
	}
	
	public Facturacion(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr, LocalDate fechaFacturacion, int nroFactura) {
		super(fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr);
		this.fechaFacturacion = fechaFacturacion;
		this.nroFactura = nroFactura;
	}


	@Override
	public void accion() {
		// TODO Auto-generated method stub

	}



	public LocalDate getFechaFacturacion() {
		return fechaFacturacion;
	}


	public void setFechaFacturacion(LocalDate fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}


	public int getNroFactura() {
		return nroFactura;
	}


	public void setNroFactura(int nroFactura) {
		this.nroFactura = nroFactura;
	}



	@Override
	protected void guardate() throws ConexionException, AccesoException {
		ReclamoDAO.getInstancia().grabarReclamo(this);
		
	}
	
	

}
