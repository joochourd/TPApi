package clases;

import java.util.ArrayList;
import java.util.List;

import dao.ClienteDAO;
import dao.EmpleadoDAO;
import dao.ProductoDAO;
import dao.RolDao;
import excepciones.AccesoException;
import excepciones.ConexionException;
import view.EmpleadoView;
import view.ProductoView;
import view.RolView;

public class Sistema {

	private static Sistema instance;
	private Empleado empleadoActual;
	private Tablero tablero;



	public static Sistema getInstance () {
		if (instance == null) {
			instance = new Sistema();
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
	
	public void modificarEmpleado(EmpleadoView empleadoV, RolView rolV) throws ConexionException, AccesoException{
		Empleado e = new Empleado(empleadoV.getNombre(), empleadoV.getFechaNac(), empleadoV.getPassword(), empleadoV.getNomUsr(), empleadoV.getNroLU(), empleadoV.getRolOriginal(), empleadoV.getRolTemporal());
		e.setRolTemporal(new Rol(rolV.getId(), rolV.getDescripcion()));
		EmpleadoDAO.getInstancia().modificarEmpleado(e);
	}

	public List<ProductoView> getProductos() throws ConexionException, AccesoException{
		List<Producto> productos = ProductoDAO.getInstancia().getProductos();
		List<ProductoView> productosView = new ArrayList<>();
		for(int i = 0; i<productos.size(); i++){
			productosView.add(productos.get(i).toView());
		}
		return productosView;
	}
	
	public List<RolView> getRoles() throws AccesoException, ConexionException{
		List<Rol> roles = RolDao.getInstancia().obtenerTodosLosRoles();
		List<RolView> rolesView = new ArrayList<>();
		for(Rol r : roles){
			rolesView.add(r.toView());
		}
		return rolesView;
	}

	public List <EmpleadoView> getEmpleados() throws AccesoException, ConexionException{
		List<Empleado> empleados = EmpleadoDAO.getInstancia().buscarTodosLosEmpleados();
		List<EmpleadoView> empleadosView = new ArrayList<>();
		for(Empleado emp : empleados){
			empleadosView.add(emp.toView());
		}
		return empleadosView;
	}


	public EmpleadoView login(String usuario, String password) throws ConexionException, AccesoException {
		Empleado emp = EmpleadoDAO.getInstancia().buscarEmpleado(usuario, password);
		if (emp != null) {
			this.empleadoActual = emp;
			this.tablero = new Tablero(emp);
			return emp.toView();
		}
		else
			return null;

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
