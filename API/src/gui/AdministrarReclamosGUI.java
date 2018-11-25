package gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Facturacion;
import clases.Reclamo;
import clases.TipoReclamo;
import dao.ReclamoDAO;
import excepciones.AccesoException;
import excepciones.ConexionException;
import view.ReclamoView;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * NO USAR!!NO USAR!!NO USAR!!NO USAR!!
 * 
 * LAS PUSE POR SEPARADO
 *
 *PARA USAR AL TABLERO COMO CONTROLADOR
 */
public class AdministrarReclamosGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Integer ordenRol = 1;
					AdministrarReclamosGUI frame = new AdministrarReclamosGUI(ordenRol);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param ordenRol 
	 */
	public AdministrarReclamosGUI(Integer ordenRol) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 393, 288);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelFacturacion = new JPanel();
		panelFacturacion.setBounds(10, 11, 339, 44);
		contentPane.add(panelFacturacion);
		panelFacturacion.setLayout(null);
		
		JLabel lblSeleccioneReclamo = new JLabel("Seleccione reclamo:");
		lblSeleccioneReclamo.setBounds(0, 11, 116, 22);
		panelFacturacion.add(lblSeleccioneReclamo);
		

		
		JPanel panelCantidadProductoFalta = new JPanel();
		panelCantidadProductoFalta.setBounds(10, 66, 339, 44);
		contentPane.add(panelCantidadProductoFalta);
		panelCantidadProductoFalta.setLayout(null);
		
		JLabel lblSeleccioneReclamo_1 = new JLabel("Seleccione reclamo:");
		lblSeleccioneReclamo_1.setBounds(0, 10, 128, 18);
		panelCantidadProductoFalta.add(lblSeleccioneReclamo_1);
	
		
		JPanel panelZona = new JPanel();
		panelZona.setBounds(10, 121, 339, 44);
		contentPane.add(panelZona);
		panelZona.setLayout(null);
		
		JLabel lblSeleccioneReclamo_2 = new JLabel("Seleccione reclamo:");
		lblSeleccioneReclamo_2.setBounds(0, 11, 131, 25);
		panelZona.add(lblSeleccioneReclamo_2);
		

		
		
		
		List<ReclamoView> reclamosV = new ArrayList<>();
		String[] datos = new String[reclamosV.size()];
		
		if (ordenRol == 1) {
			List<Reclamo> reclamos = null;
			panelCantidadProductoFalta.setVisible(false);
			panelFacturacion.setVisible(true);
			panelZona.setVisible(false);
			try {
			//Consigo todos los reclamos de facturacion
				reclamos = ReclamoDAO.getInstancia().obtenerReclamosPorTipo(TipoReclamo.facturacion);
			//Paso los reclamos a ReclamosView
				
				for (int i = 0; i < reclamos.size(); i++) {
					reclamosV.add(reclamos.get(i).reclamoToReclamoView());
				}
			//Los cargo en un array para mostrarlos en la lista
				
				for (int i = 0; i < reclamosV.size(); i++) {
					datos[i] = reclamosV.get(i).toString();
				}
			//Cargo la lista con los datos
			//Agrego el panel de facturacion al layeredPane ----------> Checkear si esta parte esta bien cuando lo probemos
				contentPane.add(panelFacturacion);
			} catch (ConexionException | AccesoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		//Creo la lista con los datos cargados
		JList listFacturacion = new JList(datos);
		listFacturacion.setBounds(154, 14, 175, 19);
		panelFacturacion.add(listFacturacion);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (panelFacturacion.isVisible()) {
					AdministrarFacturacionGUI adminFact = new AdministrarFacturacionGUI();
					adminFact.setVisible(true);
				}
				if (panelCantidadProductoFalta.isVisible()) {
					
				}
				if (panelZona.isVisible()) {
					
				}
			}
		});
		btnAceptar.setBounds(71, 205, 91, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelar.setBounds(228, 205, 91, 23);
		contentPane.add(btnCancelar);
		
		
		if (ordenRol == 2) {
			List<Reclamo> reclamosC = null;
			List<Reclamo> reclamosP = null;
			List<Reclamo> reclamosF = null;
			List<Reclamo> reclamos = null;
			panelCantidadProductoFalta.setVisible(true);
			panelFacturacion.setVisible(false);
			panelZona.setVisible(false);
			try {
			//Consigo todos los reclamos de Cantidad/Producto/Falta
				reclamosC = ReclamoDAO.getInstancia().obtenerReclamosPorTipo(TipoReclamo.cantidad);
				reclamosP = ReclamoDAO.getInstancia().obtenerReclamosPorTipo(TipoReclamo.producto);
				reclamosF = ReclamoDAO.getInstancia().obtenerReclamosPorTipo(TipoReclamo.falta);
				reclamos.addAll(reclamosC);
				reclamos.addAll(reclamosP);
				reclamos.addAll(reclamosF);
			//Paso los reclamos a ReclamosView
				//List<ReclamoView> reclamosV = new ArrayList<>();
				for (int i = 0; i < reclamos.size(); i++) {
					reclamosV.add(reclamos.get(i).reclamoToReclamoView());
				}
			//Los cargo en un array para mostrarlos en la lista
				//String[] datos = new String[reclamosV.size()];
				for (int i = 0; i < reclamosV.size(); i++) {
					datos[i] = reclamosV.get(i).toString();
				}
			
			//Agrego el panel de Cantidad/Producto/Falta al layeredPane ----------> Checkear si esta parte esta bien cuando lo probemos
				contentPane.add(panelCantidadProductoFalta);
				
			} catch (ConexionException | AccesoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		//Cargo la lista con los datos
		JList listCantProdFalt = new JList(datos);
		listCantProdFalt.setBounds(155, 11, 174, 22);
		panelCantidadProductoFalta.add(listCantProdFalt);
		
		if (ordenRol == 3) {
			List<Reclamo> reclamos = null;
			panelCantidadProductoFalta.setVisible(false);
			panelFacturacion.setVisible(false);
			panelZona.setVisible(true);
			try {
				JLabel lblSeleccioneReclamoDe = new JLabel("Seleccione reclamo de Zona: ");
				lblSeleccioneReclamoDe.setBounds(10, 53, 171, 21);
				panelZona.add(lblSeleccioneReclamoDe);
			//Consigo todos los reclamos de zona
				reclamos = ReclamoDAO.getInstancia().obtenerReclamosPorTipo(TipoReclamo.Zona);
			//Paso los reclamos a ReclamosView
				//List<ReclamoView> reclamosV = new ArrayList<>();
				for (int i = 0; i < reclamos.size(); i++) {
					reclamosV.add(reclamos.get(i).reclamoToReclamoView());
				}
			//Los cargo en un array para mostrarlos en la lista
				//String[] datos = new String[reclamosV.size()];
				for (int i = 0; i < reclamosV.size(); i++) {
					datos[i] = reclamosV.get(i).toString();
				}
			//Cargo la lista con los datos
				JList list = new JList(datos);
				list.setBounds(317, 74, -104, -28);
				panelZona.add(list);
			//Agrego el panel de Zona al layeredPane ----------> Checkear si esta parte esta bien cuando lo probemos
				contentPane.add(panelZona);
			} catch (ConexionException | AccesoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		JList listZona = new JList(datos);
		listZona.setBounds(156, 11, 173, 22);
		panelZona.add(listZona);
		
		if (ordenRol == 4) {
			List<Reclamo> reclamos = null;
			try {
				JLabel lblSeleccioneReclamoDe = new JLabel("Seleccione reclamo de Zona: ");
				lblSeleccioneReclamoDe.setBounds(10, 53, 171, 21);
				panelZona.add(lblSeleccioneReclamoDe);
			//Consigo todos los reclamos de zona
				reclamos = ReclamoDAO.getInstancia().obtenerReclamosPorTipo(TipoReclamo.Zona);
			//Paso los reclamos a ReclamosView
				//List<ReclamoView> reclamosV = new ArrayList<>();
				for (int i = 0; i < reclamos.size(); i++) {
					reclamosV.add(reclamos.get(i).reclamoToReclamoView());
				}
			//Los cargo en un array para mostrarlos en la lista
				//String[] datos = new String[reclamosV.size()];
				for (int i = 0; i < reclamosV.size(); i++) {
					datos[i] = reclamosV.get(i).toString();
				}
			//Cargo la lista con los datos
				JList list = new JList(datos);
				list.setBounds(317, 74, -104, -28);
				panelZona.add(list);
			//Agrego el panel de Zona al layeredPane ----------> Checkear si esta parte esta bien cuando lo probemos
				contentPane.add(panelZona);
			} catch (ConexionException | AccesoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
