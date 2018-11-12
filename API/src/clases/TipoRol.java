package clases;

public enum TipoRol {
	/**administra todos los reclamos referidos a facturación
	 * id 1*/
	responsableFacturacion, 
	/**administra todos los reclamos referidos a distribución, faltante  y entrega de productos
	 * id 2*/
	responsableDistribucion, 
	/**administra los reclamos de zona
	 * id 3*/
	responsableZonas,
	/**son los encargados de ingresar los reclamos y resolver consultas por teléfono
	 * id 4*/
	callCenter,
	/**administra la configuración de la aplicación
	 * id 5*/
	administrador,
	/**usuario que solo puede realizar consultas sobre el sistema (reportes, estado de reclamos, etc.)
	 * id 6*/
	consulta

}
