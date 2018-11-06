package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultasGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtIdReclamo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultasGUI frame = new ConsultasGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConsultasGUI() {
		setTitle("Consultas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnK = new JRadioButton("Cliente con mas reclamos por mes");
		rdbtnK.setBounds(26, 55, 241, 25);
		contentPane.add(rdbtnK);
		
		JRadioButton rdbtnReclamosTratadosPor = new JRadioButton("Reclamos tratados por mes");
		rdbtnReclamosTratadosPor.setBounds(26, 109, 196, 25);
		contentPane.add(rdbtnReclamosTratadosPor);
		
		JRadioButton rdbtnTiempoPromedioDe = new JRadioButton("Tiempo promedio de respuesta de reclamos por responsable");
		rdbtnTiempoPromedioDe.setBounds(26, 162, 377, 25);
		contentPane.add(rdbtnTiempoPromedioDe);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(26, 213, 210, 22);
		contentPane.add(comboBox);
		
		JButton btnGenerarReporte = new JButton("Generar reporte");
		btnGenerarReporte.setBounds(412, 92, 125, 25);
		contentPane.add(btnGenerarReporte);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(43, 275, 480, 25);
		contentPane.add(separator);
		
		JLabel lblReportes = new JLabel("Reportes...");
		lblReportes.setBounds(26, 13, 68, 16);
		contentPane.add(lblReportes);
		
		JLabel lblConsultarEstadoReclamo = new JLabel("Consultar estado reclamo...");
		lblConsultarEstadoReclamo.setBounds(12, 303, 173, 16);
		contentPane.add(lblConsultarEstadoReclamo);
		
		JLabel lblIngreseIdReclamo = new JLabel("Ingrese ID reclamo...");
		lblIngreseIdReclamo.setBounds(26, 352, 125, 16);
		contentPane.add(lblIngreseIdReclamo);
		
		txtIdReclamo = new JTextField();
		txtIdReclamo.setBounds(236, 349, 116, 22);
		contentPane.add(txtIdReclamo);
		txtIdReclamo.setColumns(10);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(426, 348, 97, 25);
		contentPane.add(btnConsultar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCancelar.setBounds(192, 442, 186, 22);
		contentPane.add(btnCancelar);
	}
}
