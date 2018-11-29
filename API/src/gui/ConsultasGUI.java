package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Sistema;
import excepciones.AccesoException;
import excepciones.ConexionException;
import view.ClienteView;
import view.EmpleadoView;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ConsultasGUI extends JFrame {

	private JPanel contentPane;

	private List<EmpleadoView> empleadosV;

	private JTextField txtIdReclamo;
	private JRadioButton rdbtnClientesReclamos, rdbtnReclamosTratadosPor, rdbtnTiempoPromedioDe;
	private ButtonGroup btg;
	private JComboBox comboBoxResponsable;
	private JButton btnConsultar, btnCancelar, btnGenerarReporte;
	private JLabel lblReportes, lblConsultarEstadoReclamo, lblIngreseIdReclamo;
	private JSeparator separator;
	private JTextField txtMes;
	private JTextField txtMes2;

	public ConsultasGUI() {
		setTitle("Consultas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 792, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		rdbtnClientesReclamos = new JRadioButton("Cliente con mas reclamos por mes");
		rdbtnClientesReclamos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnClientesReclamos.setBounds(26, 55, 293, 25);
		contentPane.add(rdbtnClientesReclamos);

		rdbtnReclamosTratadosPor = new JRadioButton("Reclamos tratados por mes");
		rdbtnReclamosTratadosPor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnReclamosTratadosPor.setBounds(26, 110, 271, 24);
		contentPane.add(rdbtnReclamosTratadosPor);

		rdbtnTiempoPromedioDe = new JRadioButton("Tiempo promedio de respuesta de reclamos por responsable");
		rdbtnTiempoPromedioDe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnTiempoPromedioDe.setBounds(26, 162, 497, 25);
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
		comboBoxResponsable.setBounds(26, 213, 455, 22);
		comboBoxResponsable.setVisible(false);
		try {
			empleadosV = Sistema.getInstance().getEmpleados();
			for(EmpleadoView e : empleadosV){
				comboBoxResponsable.addItem(e.getNombre());
			}
		} catch (AccesoException | ConexionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		contentPane.add(comboBoxResponsable);

		separator = new JSeparator();
		separator.setBounds(43, 275, 697, 25);
		contentPane.add(separator);

		lblReportes = new JLabel("Reportes");
		lblReportes.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblReportes.setBounds(26, 13, 102, 22);
		contentPane.add(lblReportes);

		lblConsultarEstadoReclamo = new JLabel("Consultar estado reclamo");
		lblConsultarEstadoReclamo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblConsultarEstadoReclamo.setBounds(12, 303, 229, 25);
		contentPane.add(lblConsultarEstadoReclamo);

		lblIngreseIdReclamo = new JLabel("Ingrese ID reclamo");
		lblIngreseIdReclamo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblIngreseIdReclamo.setBounds(26, 382, 159, 21);
		contentPane.add(lblIngreseIdReclamo);

		txtIdReclamo = new JTextField();
		txtIdReclamo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtIdReclamo.setBounds(220, 378, 176, 25);
		contentPane.add(txtIdReclamo);
		txtIdReclamo.setColumns(10);

		ManejoBoton mb = new ManejoBoton(this);

		btnGenerarReporte = new JButton("Generar reporte");
		btnGenerarReporte.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGenerarReporte.setBounds(553, 89, 159, 29);
		btnGenerarReporte.addActionListener(mb);
		contentPane.add(btnGenerarReporte);

		btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConsultar.setBounds(553, 380, 125, 25);
		btnConsultar.addActionListener(mb);
		contentPane.add(btnConsultar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancelar.addActionListener(mb);
		btnCancelar.setBounds(295, 488, 186, 22);
		contentPane.add(btnCancelar);

		txtMes = new JTextField();
		txtMes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMes.setText("\u00BAn mes");
		txtMes.setBounds(376, 55, 62, 24);
		contentPane.add(txtMes);
		txtMes.setColumns(10);
		txtMes.setVisible(false);

		txtMes2 = new JTextField();
		txtMes2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtMes2.setText("n\u00BA mes");
		txtMes2.setBounds(376, 110, 62, 24);
		contentPane.add(txtMes2);
		txtMes2.setColumns(10);
		txtMes2.setVisible(false);
	}

	class ManejoBoton implements ActionListener {

		ConsultasGUI ventana;

		public ManejoBoton(ConsultasGUI ventana)
		{
			this.ventana = ventana;
		}
		public void actionPerformed(ActionEvent btn) {
			if(btn.getSource().equals(btnGenerarReporte)){
				if(rdbtnReclamosTratadosPor.isSelected()){
					try {
						if(Integer.parseInt(txtMes2.getText())> 0 && Integer.parseInt(txtMes2.getText())<= 12){
							JOptionPane.showMessageDialog(null, "La cantidad de reclamos tratados en el mes " + Integer.parseInt(txtMes2.getText()) +" es: "+ Sistema.getInstance().getTablero().cantidadReclamosTratadosPorMes(Integer.parseInt(txtMes2.getText())), "cantidad de reclamos por mes", JOptionPane.INFORMATION_MESSAGE);
						}
						else{
							JOptionPane.showMessageDialog(null, "El mes ingresado no es un mes valido...", "Mes incorrecto", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (NumberFormatException | ConexionException | AccesoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(rdbtnClientesReclamos.isSelected()){
					try {
						List<ClienteView> clientesV = Sistema.getInstance().getTablero().clientesConMasReclamosPorMes(Integer.parseInt(txtMes.getText()));
						if(clientesV.isEmpty()){
							JOptionPane.showMessageDialog(null, "No existen reclamos registrados en el mes " + Integer.parseInt(txtMes.getText()), "Promedio", JOptionPane.INFORMATION_MESSAGE);
						}
						else{
							DefaultListModel listModel = new DefaultListModel();
							for(ClienteView c : clientesV){
								listModel.addElement(c);
							}
							JOptionPane.showMessageDialog(null, new JList(listModel), "top 5 clientes con mas reclamos por mes", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (NumberFormatException | ConexionException | AccesoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(rdbtnTiempoPromedioDe.isSelected()){
					try {
						float result = Sistema.getInstance().getTablero().tiempoPromedioRespuestaReclamosPorResponsable(comboBoxResponsable.getSelectedItem().toString());
						JOptionPane.showMessageDialog(null, result + " dias", "Promedio", JOptionPane.INFORMATION_MESSAGE);
					} catch (ConexionException | AccesoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
			if(btn.getSource().equals(btnConsultar)){
				try {
					String result = Sistema.getInstance().getTablero().realizarConsultaReclamo(Integer.parseInt(ventana.txtIdReclamo.getText()));
					JOptionPane.showMessageDialog(null, result, "Estado de reclamo", JOptionPane.INFORMATION_MESSAGE);
				} catch (NumberFormatException | ConexionException | AccesoException e) {
					System.out.println(e);
					//e.printStackTrace();
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
				txtMes2.setVisible(false);
				txtMes.setVisible(true);
			}
			if(e.getSource().equals(rdbtnReclamosTratadosPor)){
				comboBoxResponsable.setVisible(false);
				txtMes.setVisible(false);
				txtMes2.setVisible(true);
			}
			if(e.getSource().equals(rdbtnTiempoPromedioDe)){
				comboBoxResponsable.setVisible(true);
				txtMes.setVisible(false);
				txtMes2.setVisible(false);
			}
		}
	}
}
