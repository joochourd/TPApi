package clases;

import dao.ClienteDAO;
import dao.EmpleadoDAO;
import dao.ProductoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;

public class Sistema {

	private static Sistema instance;
	private Empleado empleadoActual;
	private Tablero tablero;
	
	

	public static Sistema getInstance () {
		if (instance == null) {
			return new Sistema();
		}
		return instance;
	}



	public void modificarCliente(int dniCuit, String nombre, String domicilio, String telefono, String mail) throws ConexionException, AccesoException{
		Cliente cli = ClienteDAO.getInstancia().buscarCliente(dniCuit);
		cli.setNombre(nombre);
		cli.setDomicilio(domicilio);
		cli.setTelefono(telefono);
		cli.setMail(mail);
		cli.modificate();
	}

	public void altaCliente(int dniCuit, String nombre, String domicilio, String telefono, String mail) throws ConexionException, AccesoException{
		Cliente cliente = buscarCliente(dniCuit);
		if (cliente == null){
			cliente = new Cliente(dniCuit, nombre, domicilio, telefono, mail);
			cliente.guardate();
		}

	}
	public void bajaCliente(int dniCuit, String nombre) throws ConexionException, AccesoException{
		ClienteDAO.getInstancia().borrarCliente(dniCuit, nombre);
	}

	public Cliente buscarCliente (int dniCuit) throws ConexionException, AccesoException{
		return ClienteDAO.getInstancia().buscarCliente(dniCuit);
	}

	public void altaProducto(String titulo, String descripcion, float precio) throws ConexionException, AccesoException{
		Producto prod = new Producto(titulo, descripcion, precio);
		prod.guardate();

	}
	public void modificarProducto(int codigoPublicacion,String titulo, String descripcion, float precio) throws ConexionException, AccesoException{
		Producto prod = ProductoDAO.getInstancia().buscarProducto(titulo, codigoPublicacion);
		prod.setTitulo(titulo);
		prod.setDescripcion(descripcion);
		prod.setPrecio(precio);
		prod.modificate();
	}

	public void bajaProducto(int codigoPublicacion) throws ConexionException, AccesoException{
		ProductoDAO.getInstancia().borrarProducto(codigoPublicacion);
	}
	
	public Producto buscarProducto(String tituloProd, int codigo) throws ConexionException, AccesoException{
		return ProductoDAO.getInstancia().buscarProducto(tituloProd, codigo);
	}


	public String login(String usuario, String password) throws ConexionException, AccesoException{
		Empleado emp = EmpleadoDAO.getInstancia().buscarEmpleado(usuario);
		if(emp != null){
			if(emp.getPassword().equals(password)){
				this.empleadoActual = emp;
				return "Bienvenido al sistema...";
			}
			else{
				return "Contrasenia incorrecta...";
			}
		}
		else{
			return "Usuario incorrecto...";
		}
	}



	public Empleado getEmpleadoActual() {
		return empleadoActual;
	}



	public void setEmpleadoActual(Empleado empleadoActual) {
		this.empleadoActual = empleadoActual;
	}



	public Tablero getTablero() {
		return tablero;
	}



	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

}	
