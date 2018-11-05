package gui;

import java.awt.EventQueue;
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

public class AdministrarReclamosGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Integer ordenRol = null;
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 434, 261);
		contentPane.add(layeredPane);
		
		
		if (ordenRol == 1) {
			Facturacion facturacion;
			List<Reclamo> reclamos = ReclamoDAO.getInstancia().obtenerReclamosPorTipo(TipoReclamo.Facturacion);
			String[] datos = new String[reclamos.size()];
			//auxR.obtenerReclamosPorTipo(TipoReclamo.Facturacion);
			for (int i = 0; i < reclamos.size(); i++) {
				datos[i] = reclamos.get(i).toString()//Facturacion f = (Facturacion) reclamos.get(i);
			}
			JList listReclamos = new JList(datos);
			//listReclamos.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			listReclamos.setBounds(347, 88, -120, -37);
			layeredPane.add(listReclamos);

			
			
		}
	}
}
