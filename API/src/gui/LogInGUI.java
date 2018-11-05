package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogInGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textNomUsr;
	private JTextField textContr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInGUI frame = new LogInGUI();
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
	public LogInGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogIn = new JLabel("Log In");
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setBounds(187, 27, 82, 24);
		contentPane.add(lblLogIn);
		
		JLabel lblNombreDe = new JLabel("Nombre de Usuario:");
		lblNombreDe.setBounds(127, 62, 106, 24);
		contentPane.add(lblNombreDe);
		
		JLabel lblContrasenia = new JLabel("Contrase\u00F1a:");
		lblContrasenia.setBounds(127, 97, 70, 24);
		contentPane.add(lblContrasenia);
		
		textNomUsr = new JTextField();
		textNomUsr.setBounds(243, 62, 86, 20);
		contentPane.add(textNomUsr);
		textNomUsr.setColumns(10);
		
		textContr = new JTextField();
		textContr.setBounds(243, 99, 86, 20);
		contentPane.add(textContr);
		textContr.setColumns(10);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesi\u00F3n");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnIniciarSesion.setBounds(127, 132, 95, 31);
		contentPane.add(btnIniciarSesion);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancelar.setBounds(253, 130, 89, 33);
		contentPane.add(btnCancelar);
		
	}
}
