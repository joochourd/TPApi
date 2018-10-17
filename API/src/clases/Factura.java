package clases;

import java.util.Date;

import dao.FacturaDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class Factura {

	private int nro;
	private String tipo;
	private Date fecha;
	private float total;
	
	
	public void grabate() throws ConexionException, AccesoException{
		FacturaDAO factura = new FacturaDAO();
		factura.grabarFactura(this);
	}
	
	public int getNro() {
		return nro;
	}
	public String getTipo() {
		return tipo;
	}
	public Date getFecha() {
		return fecha;
	}
	public float getTotal() {
		return total;
	}
	
	
	
}