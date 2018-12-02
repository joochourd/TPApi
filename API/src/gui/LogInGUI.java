package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import clases.Sistema;
import excepciones.AccesoException;
import excepciones.ConexionException;
import view.EmpleadoView;
import java.awt.Font;

public class LogInGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomUsr;
	private JPasswordField txtContr;

	public LogInGUI() {
		setTitle("Log In");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblcontraseña = new JLabel("\uD83D\uDD12");
		lblcontraseña.setHorizontalAlignment(SwingConstants.CENTER);
		lblcontraseña.setBounds(209, 97, 34, 24);
		contentPane.add(lblcontraseña);
		
		JLabel lblNombreDe = new JLabel("Nombre de Usuario:");
		lblNombreDe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreDe.setBounds(50, 60, 155, 24);
		contentPane.add(lblNombreDe);
		
		JLabel lblContrasenia = new JLabel("Contrase\u00F1a:");
		lblContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContrasenia.setBounds(50, 97, 118, 24);
		contentPane.add(lblContrasenia);
		
		txtNomUsr = new JTextField();
		txtNomUsr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNomUsr.setBounds(243, 62, 132, 20);
		contentPane.add(txtNomUsr);
		txtNomUsr.setColumns(10);
		
		txtContr = new JPasswordField();
		txtContr.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtContr.setBounds(243, 99, 132, 20);
		contentPane.add(txtContr);
		txtContr.setColumns(10);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesi\u00F3n");
		btnIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					EmpleadoView emp = Sistema.getInstance().login(txtNomUsr.getText(), txtContr.getText());
					if (emp != null) {
						emp.getRolTemporal();
						
						switch (emp.getRolTemporal().getId()) {
						case 1://responsableFacturacion
							AdministrarFacturacionGUI admFact = new AdministrarFacturacionGUI();
							admFact.setVisible(true);
							break;
						case 2://responsableCantProdFalta
							AdministrarCantProdFaltGUI admCPF = new AdministrarCantProdFaltGUI();
							admCPF.setVisible(true);
							break;
						case 3://responsableZonas
							AdministrarZonaGUI admZona = new AdministrarZonaGUI();
							admZona.setVisible(true);
							break;
						case 4://callCenter
							RegistrarReclamos venta = new RegistrarReclamos();
							venta.setVisible(true);
							break;
						case 5://administrador
							AdministradorAppGUI administrador = new AdministradorAppGUI();
							administrador.setVisible(true);
							break;
						case 6://consulta
							ConsultasGUI consultas = new ConsultasGUI();
							consultas.setVisible(true);
							break;

						default:
							break;
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "Usurario incorrecto...", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (ConexionException | AccesoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		btnIniciarSesion.setBounds(73, 184, 132, 24);
		contentPane.add(btnIniciarSesion);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancelar.setBounds(243, 185, 132, 24);
		contentPane.add(btnCancelar);
		
		JLabel lblUsr = new JLabel("\uD83D\uDC71");
		lblUsr.setBounds(220, 60, 20, 24);
		contentPane.add(lblUsr);
		
	}
}
