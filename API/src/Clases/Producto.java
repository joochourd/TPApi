package Clases;

import dao.ClienteDAO;
import dao.ProductoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class Producto {
	
	private static int codSiguiente = 1;
	private int codigoPublicacion;
	private String titulo;
	private String descripcion;
	private float precio;
	
	
	public Producto(String titulo, String descripcion, float precio){
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.codigoPublicacion = codSiguiente++;
	}
	
	public void modificarProducto (String titulo, String descripcion, float precio){
		if(this.titulo != titulo) this.titulo= titulo;
		if(this.descripcion != descripcion) this.descripcion = descripcion;
		if(this.precio != precio) this.precio = precio;
	}
	
	public boolean soyProducto (int codigoPub){
		return this.codigoPublicacion == codigoPub;
	}
	
	public void guardate() throws ConexionException, AccesoException{
		ProductoDAO pDao = new ProductoDAO();
		pDao.grabarProducto(this);
	}

	public int getCodigoPublicacion() {
		return codigoPublicacion;
	}

	public void setCodigoPublicacion(int codigoPublicacion) {
		this.codigoPublicacion = codigoPublicacion;
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
