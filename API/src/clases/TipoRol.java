package clases;

public enum TipoRol {
	/**administra todos los reclamos referidos a facturaci�n
	 * id 1*/
	responsableFacturacion, 
	/**administra todos los reclamos referidos a distribuci�n, faltante  y entrega de productos
	 * id 2*/
	responsableDistribucion, 
	/**administra los reclamos de zona
	 * id 3*/
	responsableZonas,
	/**son los encargados de ingresar los reclamos y resolver consultas por tel�fono
	 * id 4*/
	callCenter,
	/**administra la configuraci�n de la aplicaci�n
	 * id 5*/
	administrador,
	/**usuario que solo puede realizar consultas sobre el sistema (reportes, estado de reclamos, etc.)
	 * id 6*/
	consulta;
	
	public int  getIDRol() {
		int count = 1;
		for(TipoRol rol1 :  TipoRol.values()) {
			if (this.equals(rol1)) return count;
			count++;
		}
		return 0;
		
	}

}
