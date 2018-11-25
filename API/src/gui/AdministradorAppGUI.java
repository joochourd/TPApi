package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.Sistema;
import excepciones.AccesoException;
import excepciones.ConexionException;
import view.EmpleadoView;
import view.RolView;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;

public class AdministradorAppGUI extends JFrame {

	private JPanel contentPane;
	
	private JComboBox comboBoxRol;
	private JComboBox comboBoxEmpleado;
	
	private JLabel lblSeleccioneUnEmpleado;
	private JLabel lblqueDeseaHacer;
	private JLabel lblSeleccioneUnRol;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	private List<EmpleadoView> empleadosV;
	private List<RolView> rolV;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdministradorAppGUI frame = new AdministradorAppGUI();
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
	public AdministradorAppGUI() {
		setTitle("Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblqueDeseaHacer = new JLabel("Cambiar rol temporal empleado");
		lblqueDeseaHacer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblqueDeseaHacer.setBounds(141, 13, 295, 54);
		contentPane.add(lblqueDeseaHacer);
		
		comboBoxEmpleado = new JComboBox();
		comboBoxEmpleado.setBounds(204, 110, 317, 22);
		try {
			empleadosV = Sistema.getInstance().getEmpleados();
			for(EmpleadoView e : empleadosV){
				comboBoxEmpleado.addItem(e);
			}
		} catch (AccesoException | ConexionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		contentPane.add(comboBoxEmpleado);
		
		lblSeleccioneUnEmpleado = new JLabel("Seleccione un empleado");
		lblSeleccioneUnEmpleado.setBounds(36, 113, 156, 16);
		contentPane.add(lblSeleccioneUnEmpleado);
		
		lblSeleccioneUnRol = new JLabel("Seleccione un rol");
		lblSeleccioneUnRol.setBounds(36, 210, 132, 16);
		contentPane.add(lblSeleccioneUnRol);
		
		comboBoxRol = new JComboBox();
		comboBoxRol.setBounds(204, 207, 317, 22);
		try {
			rolV = Sistema.getInstance().getRoles();
			for(RolView r : rolV){
				comboBoxRol.addItem(r);
			}
		} catch (AccesoException | ConexionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.add(comboBoxRol);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(88, 357, 97, 25);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Sistema.getInstance().modificarEmpleado(empleadosV.get(comboBoxEmpleado.getSelectedIndex()), rolV.get(comboBoxRol.getSelectedIndex()));
				} catch (ConexionException | AccesoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(364, 357, 97, 25);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(btnCancelar);
	}
}
