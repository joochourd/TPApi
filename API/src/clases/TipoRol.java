package clases;

public enum TipoRol {
	/**administra todos los reclamos referidos a facturaci�n*/
	responsableFacturacion,
	/**administra todos los reclamos referidos a distribuci�n, faltante  y entrega de productos*/
	responsableDistribucion, 
	/**administra los reclamos de zona*/
	responsableZonas,
	/**son los encargados de ingresar los reclamos y resolver consultas por tel�fono*/
	callCenter,
	/**administra la configuraci�n de la aplicaci�n*/
	administrador,
	/**usuario que solo puede realizar consultas sobre el sistema (reportes, estado de reclamos, etc.)*/
	consulta

}
