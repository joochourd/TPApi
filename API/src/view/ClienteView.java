package view;

public class ClienteView {
	
	private String nombre;
	private String domicilio;
	private String telefono;
	private String mail;
	private int dniCuit;
	
	public ClienteView(String nombre, String domicilio, String telefono, String mail, int dniCuit) {
		super();
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.telefono = telefono;
		this.mail = mail;
		this.dniCuit = dniCuit;
	}

	@Override
	public String toString() {
		return "nombre: " + nombre + ", dniCuit: " + dniCuit;
	}


}
