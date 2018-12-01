package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;

public class AdministradorAppGUI extends JFrame {

	private JPanel contentPane;
	
	private JComboBox comboBoxRol;
	
	private JLabel lblSeleccioneUnEmpleado;
	private JLabel lblqueDeseaHacer;
	private JLabel lblSeleccioneUnRol;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	
	private List<EmpleadoView> empleadosV;
	private List<RolView> rolV;

	private JScrollPane scrollPane;
	private DefaultListModel listModel;
	private JList list;	

	
	public AdministradorAppGUI() {
		setTitle("Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 792, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblqueDeseaHacer = new JLabel("Cambiar rol temporal empleado");
		lblqueDeseaHacer.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblqueDeseaHacer.setBounds(277, 13, 244, 54);
		contentPane.add(lblqueDeseaHacer);
		
		listModel = new DefaultListModel();
		try {
			empleadosV = Sistema.getInstance().getEmpleados();
			for(EmpleadoView e : empleadosV){
				listModel.addElement(e);
			}
			list = new JList(listModel);
			list.setBorder(UIManager.getBorder("TextField.border"));
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			list.setBackground(Color.WHITE);
			list.setBounds(53, 174, 647, 102);
			//contentPane.add(list);
			scrollPane = new JScrollPane();
			scrollPane.setBounds(53, 174, 647, 102);
			scrollPane.setViewportView(list);
			contentPane.add(scrollPane);
		} catch (AccesoException | ConexionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		lblSeleccioneUnEmpleado = new JLabel("Seleccione un empleado");
		lblSeleccioneUnEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSeleccioneUnEmpleado.setBounds(31, 123, 185, 22);
		contentPane.add(lblSeleccioneUnEmpleado);
		
		lblSeleccioneUnRol = new JLabel("Seleccione un nuevo rol temporal");
		lblSeleccioneUnRol.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSeleccioneUnRol.setBounds(31, 332, 250, 22);
		contentPane.add(lblSeleccioneUnRol);
		
		comboBoxRol = new JComboBox();
		comboBoxRol.setBounds(334, 333, 366, 22);
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
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAceptar.setBounds(204, 472, 97, 25);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Sistema.getInstance().modificarEmpleado(empleadosV.get(list.getSelectedIndex()), rolV.get(comboBoxRol.getSelectedIndex()));
				} catch (ConexionException | AccesoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancelar.setBounds(467, 472, 97, 25);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		contentPane.add(btnCancelar);
		
		
	}
}
