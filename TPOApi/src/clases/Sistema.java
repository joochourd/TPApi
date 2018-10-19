package clases;

import java.util.Date;
import java.util.List;

import dao.ClienteDAO;
import dao.ProductoDAO;

public class Sistema {
	
	
	public void modificarCliente(int dniCuitAnterior, String nombreAnterior, int dniCuit, String nombre, String domicilio, String telefono, String mail){
		List<Cliente> cliente = buscarCliente(dniCuitAnterior);
		int i = 0;
		while (!cliente.isEmpty()){
			cliente.get(i).modificarCliente(dniCuit, nombre, domicilio, telefono, mail);
			ClienteDAO cDao = new ClienteDAO();
			cDao.modificarCliente(cliente.get(i), nombreAnterior, dniCuitAnterior);
		}
	}
	public Cliente altaCliente(int dniCuit, String nombre, String domicilio, String telefono, String mail){
		List<Cliente> cliente = buscarCliente(dniCuit);
		if (cliente.isEmpty()){ //primero verifica que el cliente no exista
			Cliente clienteNuevo = new Cliente(dniCuit, nombre, domicilio, telefono, mail);
			clienteNuevo.guardate();
		}
		
	}
	public void bajaCliente(int dniCuit, String nombre){
		ClienteDAO cDao= new ClienteDAO();
		cDao.borrarCliente(dniCuit, nombre);
	}
	public List<Cliente> buscarCliente (int dniCuit){
		ClienteDAO cDao = new ClienteDAO();
		return cDao.buscarCliente(dniCuit);
	}
	public Producto altaProducto(int codigoPublicacion, String titulo, String descripcion, float precio){
		List<Producto> producto = buscarProducto(titulo, codigoPublicacion);
		if (producto.isEmpty()){
			Producto productoNuevo = new Producto(codigoPublicacion, titulo, descripcion, precio);
			productoNuevo.guardate();
		}		
	}
	public void modificarProducto(int codigoPublicacion,String titulo, String descripcion, float precio){
		List<Producto> producto = buscarProducto(titulo, codigoPublicacion);
		int i = 0;
		while (!producto.isEmpty()){
			producto.get(i).modificarProducto(titulo, descripcion, precio);
			ProductoDAO pDao = new ProductoDAO();
			pDao.modificarProducto(producto.get(i));
		}
	}
	
	public void bajaProducto(int codigoPublicacion){
		ProductoDAO pDao = new ProductoDAO();
		pDao.borrarProducto(codigoPublicacion);
	}
	public List<Producto> buscarProducto(String tituloProd, int codigo){// Ver como devolver un producto
		ProductoDAO pDao = new ProductoDAO();
		return pDao.buscarProducto(tituloProd, codigo);
	}
	
	
	
	public List <String> reclamoCantProdFalta(List<int> cantidad, String tituloProd){}	
	
	public Reclamo registrarReclamoZona(String zona, int dniCuit){}
	
	public Reclamo registrarReclamoFacturacion(Date fecha, int nroFactura, int dniCuit){}	
	
	public void registrarReclamoCompuesto(int dniCuit){}
	// ver si dni es string o int
	
	public void administrarReclamoCantProdFaltante(){}
	
	public void administrarReclamoZona(){}
	
	public void administrarReclamoFacturacion(){}
	
	public void realizarConsulta(){}
	
	public void login(String usuario, String contraseña){}
	
	public List<Reclamo> getReclamos(Cliente cliente){
		cliente.getReclamos();
	}
	
}	
