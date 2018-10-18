package Clases;

public class Producto {
	private int codigoPublicacion;
	private String descripcion;
	private String titulo;
	private float precio;
	
	
	public void modificarProducto(String titulo, String descripcion, float precio) {
		//WTF ?!
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int getCodigoPublicacion() {
		return codigoPublicacion;
	}
	public void setCodigoPublicacion(int codigoPublicacion) {
		this.codigoPublicacion = codigoPublicacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
}
