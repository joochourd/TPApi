package view;

public class ProductoView {
	private int codigoPublicacion;
	private String titulo;
	private String descripcion;
	private float precio;
	
	public ProductoView(int codigoPublicacion, String titulo, String descripcion, float precio){
		this.codigoPublicacion = codigoPublicacion;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	
	
	
	public int getCodigoPublicacion() {
		return codigoPublicacion;
	}



	public String getTitulo() {
		return titulo;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public float getPrecio() {
		return precio;
	}



	public String toString(){
		return this.codigoPublicacion + " - " + this.titulo + " - " + this.descripcion + "-" + this.precio;
	}
}
