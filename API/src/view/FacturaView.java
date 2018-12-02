package view;

import java.util.Date;

public class FacturaView {
	
	private int nro;
	private String tipo;
	private Date fecha;
	private float total;
	
	
	public FacturaView(int nro, String tipo, Date fecha, float total) {
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
	@Override
	public String toString() {
		return "nro: " + nro + ", tipo: " + tipo + ", fecha: " + fecha + ", total: " + total;
	}
	

}
