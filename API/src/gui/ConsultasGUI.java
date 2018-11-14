package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Sistema;
import excepciones.AccesoException;
import excepciones.ConexionException;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.concurrent.SynchronousQueue;
import java.awt.event.ActionEvent;

public class ConsultasGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtIdReclamo;
	private JRadioButton rdbtnClientesReclamos, rdbtnReclamosTratadosPor, rdbtnTiempoPromedioDe;
	private ButtonGroup btg;
	private JComboBox comboBoxResponsable;
	private JButton btnConsultar, btnCancelar, btnGenerarReporte;
	private JLabel lblReportes, lblConsultarEstadoReclamo, lblIngreseIdReclamo;
	private JSeparator separator;

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
		
		rdbtnClientesReclamos = new JRadioButton("Cliente con mas reclamos por mes");
		rdbtnClientesReclamos.setBounds(26, 55, 241, 25);
		contentPane.add(rdbtnClientesReclamos);
		
		rdbtnReclamosTratadosPor = new JRadioButton("Reclamos tratados por mes");
		rdbtnReclamosTratadosPor.setBounds(26, 109, 196, 25);
		contentPane.add(rdbtnReclamosTratadosPor);
		
		rdbtnTiempoPromedioDe = new JRadioButton("Tiempo promedio de respuesta de reclamos por responsable");
		rdbtnTiempoPromedioDe.setBounds(26, 162, 377, 25);
		contentPane.add(rdbtnTiempoPromedioDe);
		
		btg = new ButtonGroup();
		btg.add(rdbtnClientesReclamos);
		btg.add(rdbtnReclamosTratadosPor);
		btg.add(rdbtnTiempoPromedioDe);
		
		RadioListener mr = new RadioListener();
		rdbtnClientesReclamos.addActionListener(mr);
		rdbtnReclamosTratadosPor.addActionListener(mr);
		rdbtnTiempoPromedioDe.addActionListener(mr);
		
		
		
		comboBoxResponsable = new JComboBox();
		comboBoxResponsable.setBounds(26, 213, 210, 22);
		comboBoxResponsable.setVisible(false);
		contentPane.add(comboBoxResponsable);
		
		separator = new JSeparator();
		separator.setBounds(43, 275, 480, 25);
		contentPane.add(separator);
		
		lblReportes = new JLabel("Reportes...");
		lblReportes.setBounds(26, 13, 68, 16);
		contentPane.add(lblReportes);
		
		lblConsultarEstadoReclamo = new JLabel("Consultar estado reclamo...");
		lblConsultarEstadoReclamo.setBounds(12, 303, 173, 16);
		contentPane.add(lblConsultarEstadoReclamo);
		
		lblIngreseIdReclamo = new JLabel("Ingrese ID reclamo...");
		lblIngreseIdReclamo.setBounds(26, 352, 125, 16);
		contentPane.add(lblIngreseIdReclamo);
		
		txtIdReclamo = new JTextField();
		txtIdReclamo.setBounds(236, 349, 116, 22);
		contentPane.add(txtIdReclamo);
		txtIdReclamo.setColumns(10);
		
		ManejoBoton mb = new ManejoBoton(this);
		
		btnGenerarReporte = new JButton("Generar reporte");
		btnGenerarReporte.setBounds(412, 92, 125, 25);
		btnGenerarReporte.addActionListener(mb);
		contentPane.add(btnGenerarReporte);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(426, 348, 97, 25);
		btnConsultar.addActionListener(mb);
		contentPane.add(btnConsultar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(mb);
		btnCancelar.setBounds(192, 442, 186, 22);
		contentPane.add(btnCancelar);
	}
	
	class ManejoBoton implements ActionListener {

		ConsultasGUI ventana;

		public ManejoBoton(ConsultasGUI ventana)
		{
			this.ventana = ventana;
		}
		public void actionPerformed(ActionEvent btn) {
			if(btn.getSource().equals(btnGenerarReporte)){
				
			}
			if(btn.getSource().equals(btnConsultar)){
				try {
					System.out.println(Sistema.getInstance().getTablero().realizarConsultaReclamo(Integer.parseInt(ventana.txtIdReclamo.getText())));
				} catch (NumberFormatException | ConexionException | AccesoException e) {
					System.out.println("No se pudo obtener el reclamo");
					e.printStackTrace();
				}
			}
			if(btn.getSource().equals(btnCancelar)){
				System.exit(0);
			}

		}
	}
	
	class RadioListener implements ActionListener{
		 public void actionPerformed(ActionEvent e) {
			 if(e.getSource().equals(rdbtnClientesReclamos)){
				 comboBoxResponsable.setVisible(false);
			 }
			 if(e.getSource().equals(rdbtnReclamosTratadosPor)){
				 comboBoxResponsable.setVisible(false);
			 }
			 if(e.getSource().equals(rdbtnTiempoPromedioDe)){
				 comboBoxResponsable.setVisible(true);
			 }
		 }
	}
	
}
