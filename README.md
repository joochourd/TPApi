# TPApi
Project API
Trabajo Práctico Cuatrimestral
Aplicaciones Interactivas

Sistema de Administración de Reclamos

	El equipo de analistas ha finalizado el relevamiento y cotización de un sistema que gestiona los reclamos recibidos.

	Se solicita al equipo de diseñadores que diseñe e implemente el sistema relevado.  A continuación se detalla el relevamiento llevado a cabo por el equipo.

•	El sistema debe permitir la administración de clientes (alta, baja y modificación). De los mismos se debe guardar la siguiente información: nombre, domicilio, teléfono y mail. 
•	El sistema debe permitir la administración de productos (alta, baja y modificación). De los mismos se debe guardar: código publicación, titulo, descripción, precio.
•	El sistema debe registrar los reclamos de los clientes. Cada reclamo debe almacenar: fecha, numero de reclamo, cliente, descripción del reclamo.
•	Existen distintos tipos de reclamos, dependiendo del mismo se solicitara información adicional:
o	Reclamo de cantidades: el cliente solicita se le envíe mayor cantidad de productos. El sistema debe registrar cada producto y su cantidad pedida.
o	Reclamo de producto: el cliente no recibe cierto producto y desea recibirlo. Se debe almacenar el producto y la cantidad pedida.
o	Reclamo de faltantes: el cliente recibió menos cantidad de la solicitada e informada.
o	Reclamo de zona: el cliente ha detectado que un competidor está entregando productos en su zona. Se debe informar la zona afectada.
o	Reclamo de facturación: debe informar la fecha de factura en donde detecto inconsistencias y detallar la misma. Se pueden registrar varias facturas.
•	El sistema debe administrar distintos roles de usuarios (responsable facturación, responsable distribución, responsable zonas entrega, call center, administrador, consulta) 
o	Responsable facturación: administra todos los reclamos referidos a facturación
o	Responsable distribución: administra todos los reclamos referidos a distribución, faltante  y entrega de productos
o	Responsable zona entrega: administra los reclamos de zona
o	Call center: son los encargados de ingresar los reclamos y resolver consultas por teléfono
o	Administrador: administra la configuración de la aplicación
o	Consulta: usuario que solo puede realizar consultas sobre el sistema (reportes, estado de reclamos, etc.)
•	El sistema debe permitir a cada rol manejar un tablero de reclamos. En este tablero el responsable vera todos los reclamos que han ingresado y podrá darles tratamiento. Los reclamos deben contar con un estado que indique el grado de tratamiento (ingresado, en tratamiento, cerrado, solucionado) Se debe registrar la fecha en que se realizaron acciones sobre los reclamos y un detalle de lo realizado.
•	Al cerrar un reclamo el sistema debe registrar la fecha de cierre para estadísticas posteriores.
•	Existen reclamos que deben tratarse en conjunto debido a que una misma situación genera reclamos de distinto tipo. El sistema debe permitir ingresar reclamos compuestos. Por ejemplo: recibir una cantidad menor de productos genera un reclamo de faltante y un reclamo de facturación dado que el producto fue cobrado pero no se recibió.
•	El sistema debe notificar en forma automática a los tableros cada vez que se ingresa un nuevo reclamo.
•	El sistema debe generar reportes de consulta: ranking de clientes con mayor cantidad de reclamos, cantidad de reclamos tratados por mes, ranking de tratamiento de reclamos, tiempo promedio de respuesta de los reclamos por responsable.

Se pide:
-	Documentar el diseño del sistema e implementar utilizando la metodología de UP:
	Ingreso a la aplicación. 
	Administración de roles.
	Registro de reclamos
	Tratamiento de reclamos
	Actualización de tableros en forma automática
	Reportes
-	Las funcionalidades se codificarán accediendo a la información de la BD sin necesidad de mantener información en memoria a menos que lo considere imprescindible
-	Los ABM en el negocio deben validar la información ingresada.
-	Las funcionalidades, ABM y demás procesos de negocios informarán de los casos especiales mediante el uso de excepciones.
-	Las interfaces gráficas deben ser realizadas en java, pudiéndose utilizar algún plugin o framework para eclipse.
