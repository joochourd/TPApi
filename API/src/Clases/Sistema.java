package Clases;

import java.util.Date;
import java.util.List;

import dao.ClienteDAO;
import dao.ProductoDAO;

public class Sistema {
	
	
	public void modificarCliente(int dniCuitAnterior, String nombreAnterior, int dniCuit, String nombre, String domicilio, String telefono, String mail){
		Cliente cliente = buscarCliente(dniCuitAnterior);
		cliente.modificarCliente(dniCuit, nombre, domicilio, telefono, mail);
		ClienteDAO cDao = new ClienteDAO();
		cDao.modificarCliente(cliente, nombreAnterior, dniCuitAnterior);
	}
	public Cliente altaCliente(int dniCuit, String nombre, String domicilio, String telefono, String mail){
		Cliente cliente = buscarCliente(dniCuit);
		if (cliente == null){ //primero verifica que el cliente no exista
			cliente = new Cliente(dniCuit, nombre, domicilio, telefono, mail);
			cliente.guardate;
		}
		
	}
	public void bajaCliente(int dniCuit, String nombre){
		ClienteDAO cDao= new ClienteDAO();
		cDao.borrarCliente(dniCuit, nombre);
	}
	public Cliente buscarCliente (int dniCuit){ // Ver como devolver un cliente
		ClienteDAO cDao = new ClienteDAO();
		cDao.buscarCliente(dniCuit);
		return  ;
	}
	public Producto altaProducto(String titulo, String descripcion, float precio){
		Producto prod = new Producto(titulo, descripcion, precio);
		prod.guardate();
		
	}
	public void modificarProducto(int codigoPublicacion,String titulo, String descripcion, float precio){
		Producto producto = buscarProducto();
		producto.modificarProducto(titulo, descripcion, precio);
		ProductoDAO pDao = new ProductoDAO();
		pDao.modificarProducto(producto,);
	}
	
	public void bajaProducto(int codigoPublicacion){
		ProductoDAO pDao = new ProductoDAO();
		pDao.borrarProducto(codigoPublicacion);
	}
	public Producto buscarProducto(List<String> tituloProd){}//ver parametros
	
	public Reclamo iniciarReclamo(int dniCuit){}
	
	public List <String> reclamoCantProdFalta(List<int> cantidad, String tituloProd){}	
	
	public Reclamo registrarReclamoZona(String zona){}
	
	public Reclamo registrarReclamoFacturacion(Date fecha, int nroFactura){}	
	
	public void administrarReclamoCantProdFaltante(){}
	
	public void administrarReclamoZona(){}
	
	public void administrarReclamoFacturacion(){}
	
	public void realizarConsulta(){}
	
	public void login(String usuario, String contrase�a){}
	
	public List<Reclamo> getReclamos(Cliente cliente){}
	
	public void registrarReclamoCompuesto(){}
	
}	