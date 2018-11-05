package clases;

import dao.ProductoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class Producto {
	
	private int codigoPublicacion;
	private String titulo;
	private String descripcion;
	private float precio;
	
	
	public Producto(String titulo, String descripcion, float precio){
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	
	public void modificate() throws ConexionException, AccesoException{
		ProductoDAO.getInstancia().modificarProducto(this);
	}
	
	public boolean soyProducto (int codigoPub){
		return this.codigoPublicacion == codigoPub;
	}
	
	public void guardate() throws ConexionException, AccesoException{
		ProductoDAO.getInstancia().grabarProducto(this);
	}

	public int getCodigoPublicacion() {
		return codigoPublicacion;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
	
}
