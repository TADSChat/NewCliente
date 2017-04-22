package br.univel.ChatRedes.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import common.EntidadeUsuario;
import common.InterfaceServidor;
import common.InterfaceUsuario;
import common.Status;
import javax.swing.JTextArea;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static InterfaceServidor conexaoCliente;
	private static EntidadeUsuario meuUsuario;
	private JPanel contentPane;
	private JTextField field_email;
	private JTextField field_servidor;

	private JNumberField field_porta;
	private JPasswordField field_senha;

	private Registry registry;
	private Dimension dimensaoTela = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Create the frame.
	 */
	public Login() {
		setVisible(true);
		setTitle("TadsZap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 500);
		setLocation((dimensaoTela.width - this.getSize().width) / 2, (dimensaoTela.height - this.getSize().height) / 2);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblNewLabel = new JLabel("TadsZap");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 26));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 3;
		contentPane.add(lblEmail, gbc_lblEmail);

		field_email = new JTextField();
		GridBagConstraints gbc_field_email = new GridBagConstraints();
		gbc_field_email.insets = new Insets(0, 0, 5, 0);
		gbc_field_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_field_email.gridwidth = 4;
		gbc_field_email.gridx = 0;
		gbc_field_email.gridy = 4;
		contentPane.add(field_email, gbc_field_email);
		field_email.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		GridBagConstraints gbc_lblSenha = new GridBagConstraints();
		gbc_lblSenha.anchor = GridBagConstraints.WEST;
		gbc_lblSenha.insets = new Insets(0, 0, 5, 5);
		gbc_lblSenha.gridx = 0;
		gbc_lblSenha.gridy = 5;
		contentPane.add(lblSenha, gbc_lblSenha);

		field_senha = new JPasswordField();
		GridBagConstraints gbc_field_senha = new GridBagConstraints();
		gbc_field_senha.gridwidth = 4;
		gbc_field_senha.insets = new Insets(0, 0, 5, 0);
		gbc_field_senha.fill = GridBagConstraints.HORIZONTAL;
		gbc_field_senha.gridx = 0;
		gbc_field_senha.gridy = 6;
		contentPane.add(field_senha, gbc_field_senha);

		JLabel lblServidor = new JLabel("Servidor:");
		GridBagConstraints gbc_lblServidor = new GridBagConstraints();
		gbc_lblServidor.anchor = GridBagConstraints.WEST;
		gbc_lblServidor.insets = new Insets(0, 0, 5, 5);
		gbc_lblServidor.gridx = 0;
		gbc_lblServidor.gridy = 7;
		contentPane.add(lblServidor, gbc_lblServidor);

		field_servidor = new JTextField();
		field_servidor.setText("192.168.25.13");
		GridBagConstraints gbc_field_servidor = new GridBagConstraints();
		gbc_field_servidor.insets = new Insets(0, 0, 5, 0);
		gbc_field_servidor.gridwidth = 4;
		gbc_field_servidor.fill = GridBagConstraints.HORIZONTAL;
		gbc_field_servidor.gridx = 0;
		gbc_field_servidor.gridy = 8;
		contentPane.add(field_servidor, gbc_field_servidor);
		field_servidor.setColumns(10);

		JLabel lblSenha_1 = new JLabel("Porta:");
		GridBagConstraints gbc_lblSenha_1 = new GridBagConstraints();
		gbc_lblSenha_1.anchor = GridBagConstraints.WEST;
		gbc_lblSenha_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblSenha_1.gridx = 0;
		gbc_lblSenha_1.gridy = 9;
		contentPane.add(lblSenha_1, gbc_lblSenha_1);

		field_porta = new JNumberField();
		field_porta.setText("1818");
		GridBagConstraints gbc_field_porta = new GridBagConstraints();
		gbc_field_porta.insets = new Insets(0, 0, 5, 0);
		gbc_field_porta.fill = GridBagConstraints.HORIZONTAL;
		gbc_field_porta.gridwidth = 4;
		gbc_field_porta.gridx = 0;
		gbc_field_porta.gridy = 10;
		contentPane.add(field_porta, gbc_field_porta);
		field_porta.setColumns(10);

		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				conectar();

			}
		});
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConectar.insets = new Insets(0, 0, 5, 5);
		gbc_btnConectar.gridx = 2;
		gbc_btnConectar.gridy = 11;
		contentPane.add(btnConectar, gbc_btnConectar);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnSair = new GridBagConstraints();
		gbc_btnSair.insets = new Insets(0, 0, 5, 0);
		gbc_btnSair.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSair.gridx = 3;
		gbc_btnSair.gridy = 11;
		contentPane.add(btnSair, gbc_btnSair);
	}

	protected void conectar() {
		EntidadeUsuario user = new EntidadeUsuario();

		String ipServidor = field_servidor.getText();
		Integer portaServidor = field_porta.getNumber();

		user.setEmail(field_email.getText()).setSenha(String.valueOf(field_senha.getPassword()));

		try {
			registry = LocateRegistry.getRegistry(ipServidor, Integer.valueOf(portaServidor));
			conexaoCliente = (InterfaceServidor) registry.lookup(InterfaceServidor.NOME);

			Usuario usuarioExportado = new Usuario();
			usuarioExportado.exportar();
			meuUsuario = conexaoCliente.conectarChat(user, usuarioExportado);

			if (meuUsuario == null) {
				JOptionPane.showMessageDialog(null, "Usuario invalido!");
				return;
			} else {
				System.out.println(meuUsuario);
				new Principal();
				dispose();
			}

		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"                   - ERRO -\n                             "
							+ "- Verifique se o IP e PORTA estão corretos.\n             "
							+ "- Verifique se não há bloqueio de FIREWALL ou ANTIVIRUS.\n" + "\n\n");

		}
	}

	public static InterfaceServidor getConexaoCliente() {
		return conexaoCliente;
	}

	public static void setConexaoCliente(InterfaceServidor conexaoCliente) {
		Login.conexaoCliente = conexaoCliente;
	}

	public static EntidadeUsuario getMeuUsuario() {
		return meuUsuario;
	}

	public static void setMeuUsuario(EntidadeUsuario meuUsuario) {
		Login.meuUsuario = meuUsuario;
	}

}
