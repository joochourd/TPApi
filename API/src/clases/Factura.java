package clases;

import java.util.Date;

import dao.FacturaDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import view.FacturaView;

public class Factura {

	private int nro;
	private String tipo;
	private Date fecha;
	private float total;
	
	
	
	public void grabate() throws ConexionException, AccesoException{
		FacturaDAO factura = new FacturaDAO();
		factura.grabarFactura(this);
	}
	
	public Factura(int nro, String tipo, Date fecha, float total) {
		super();
		this.nro = nro;
		this.tipo = tipo;
		this.fecha = fecha;
		this.total = total;
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
	
	public FacturaView toView() {
		return new FacturaView(this.nro, this.tipo, this.fecha, this.total);
	}
	
}
