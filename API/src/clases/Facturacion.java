package clases;

import java.sql.SQLException;
import java.time.LocalDate;

import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class Facturacion extends Simple {
	
	private LocalDate fechaFacturacion;
	private Factura factura;


	public Facturacion(int numeroReclamo,LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, Enum<Estados> estado, int clienteDniCuit, String empleadoNombreUsr, LocalDate fechaFacturacion, Factura factura, String idCompuesto) {
		super(numeroReclamo, fecha, descripcion, tipo, estado, clienteDniCuit, empleadoNombreUsr, idCompuesto);
		this.fechaFacturacion = fechaFacturacion;
		this.factura = factura;
		// TODO Auto-generated constructor stub
	}
	
	public Facturacion(LocalDate fecha, String descripcion, Enum<TipoReclamo> tipo, int clienteDniCuit, String empleadoNombreUsr, LocalDate fechaFacturacion, Factura factura) {
		super(fecha, descripcion, tipo, clienteDniCuit, empleadoNombreUsr);
		this.fechaFacturacion = fechaFacturacion;
		this.factura = factura;
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
		return factura.getNro();
	}





	@Override
	protected void guardate() throws ConexionException, AccesoException, SQLException {
		ReclamoDAO.getInstancia().grabarReclamo(this);
		
	}
	
	

}
