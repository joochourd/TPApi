package clases;

import java.time.LocalDate;

public class Facturacion extends Simple {


	public Facturacion(int numeroReclamo, LocalDate fecha, String descripcion, Enum<Tipos> tipo, LocalDate fechaFacturacion, int nroFactura) {
		super(numeroReclamo, fecha, descripcion, tipo);
		this.fechaFacturacion = fecha;
		this.nroFactura = nroFactura;
		// TODO Auto-generated constructor stub
	}


	private LocalDate fechaFacturacion;
	private int nroFactura;
	


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

}
