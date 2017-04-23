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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.univel.ChatRedes.model.Usuario;
import common.EntidadeUsuario;
import common.InterfaceServidor;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;

	private static InterfaceServidor conexaoCliente;
	private static EntidadeUsuario meuUsuario;
	private JPanel painelPrincipal;
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
		setResizable(false);
		setVisible(true);
		setTitle("TadsZap");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(410, 350);
		setLocation((dimensaoTela.width - this.getSize().width) / 2, (dimensaoTela.height - this.getSize().height) / 2);

		painelPrincipal = new JPanel();
		painelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
		painelPrincipal.setSize(400, 320);
		GridBagLayout gbl_painelPrincipal = new GridBagLayout();
		gbl_painelPrincipal.columnWidths = new int[] { 65, 150, 61, 0 };
		gbl_painelPrincipal.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_painelPrincipal.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0 };
		gbl_painelPrincipal.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		painelPrincipal.setLayout(gbl_painelPrincipal);

		JLabel lblNewLabel = new JLabel(new ImageIcon("imagens\\group.png"));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 26));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		painelPrincipal.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblEmail = new JLabel("Email:");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblEmail.insets = new Insets(5, 0, 5, 5);
		gbc_lblEmail.gridx = 0;
		gbc_lblEmail.gridy = 1;
		painelPrincipal.add(lblEmail, gbc_lblEmail);

		field_email = new JTextField();
		GridBagConstraints gbc_field_email = new GridBagConstraints();
		gbc_field_email.anchor = GridBagConstraints.NORTH;
		gbc_field_email.insets = new Insets(5, 0, 5, 0);
		gbc_field_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_field_email.gridwidth = 3;
		gbc_field_email.gridx = 1;
		gbc_field_email.gridy = 1;
		painelPrincipal.add(field_email, gbc_field_email);
		field_email.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		GridBagConstraints gbc_lblSenha = new GridBagConstraints();
		gbc_lblSenha.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblSenha.insets = new Insets(5, 0, 5, 5);
		gbc_lblSenha.gridx = 0;
		gbc_lblSenha.gridy = 2;
		painelPrincipal.add(lblSenha, gbc_lblSenha);

		field_senha = new JPasswordField();
		GridBagConstraints gbc_field_senha = new GridBagConstraints();
		gbc_field_senha.anchor = GridBagConstraints.NORTH;
		gbc_field_senha.gridwidth = 3;
		gbc_field_senha.insets = new Insets(5, 0, 5, 0);
		gbc_field_senha.fill = GridBagConstraints.HORIZONTAL;
		gbc_field_senha.gridx = 1;
		gbc_field_senha.gridy = 2;
		painelPrincipal.add(field_senha, gbc_field_senha);

		JLabel lblServidor = new JLabel("Servidor:");
		GridBagConstraints gbc_lblServidor = new GridBagConstraints();
		gbc_lblServidor.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblServidor.insets = new Insets(5, 0, 5, 5);
		gbc_lblServidor.gridx = 0;
		gbc_lblServidor.gridy = 3;
		painelPrincipal.add(lblServidor, gbc_lblServidor);

		field_servidor = new JTextField();
		field_servidor.setText("192.168.25.10");
		GridBagConstraints gbc_field_servidor = new GridBagConstraints();
		gbc_field_servidor.anchor = GridBagConstraints.NORTH;
		gbc_field_servidor.insets = new Insets(5, 0, 5, 0);
		gbc_field_servidor.fill = GridBagConstraints.HORIZONTAL;
		gbc_field_servidor.gridx = 1;
		gbc_field_servidor.gridy = 3;
		painelPrincipal.add(field_servidor, gbc_field_servidor);
		field_servidor.setColumns(10);

		JLabel lblSenha_1 = new JLabel("Porta:");
		GridBagConstraints gbc_lblSenha_1 = new GridBagConstraints();
		gbc_lblSenha_1.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblSenha_1.insets = new Insets(5, 0, 5, 5);
		gbc_lblSenha_1.gridx = 2;
		gbc_lblSenha_1.gridy = 3;
		painelPrincipal.add(lblSenha_1, gbc_lblSenha_1);

		field_porta = new JNumberField();
		field_porta.setText("1818");
		GridBagConstraints gbc_field_porta = new GridBagConstraints();
		gbc_field_porta.fill = GridBagConstraints.HORIZONTAL;
		gbc_field_porta.anchor = GridBagConstraints.NORTH;
		gbc_field_porta.insets = new Insets(5, 0, 5, 0);
		gbc_field_porta.gridx = 3;
		gbc_field_porta.gridy = 3;
		painelPrincipal.add(field_porta, gbc_field_porta);
		field_porta.setColumns(10);

		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conectar();
			}
		});
		GridBagConstraints gbc_btnConectar = new GridBagConstraints();
		gbc_btnConectar.anchor = GridBagConstraints.NORTH;
		gbc_btnConectar.gridwidth = 4;
		gbc_btnConectar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConectar.insets = new Insets(5, 0, 5, 0);
		gbc_btnConectar.gridx = 0;
		gbc_btnConectar.gridy = 4;
		painelPrincipal.add(btnConectar, gbc_btnConectar);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		GridBagConstraints gbc_btnSair = new GridBagConstraints();
		gbc_btnSair.anchor = GridBagConstraints.NORTH;
		gbc_btnSair.insets = new Insets(5, 0, 5, 0);
		gbc_btnSair.gridwidth = 4;
		gbc_btnSair.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSair.gridx = 0;
		gbc_btnSair.gridy = 5;
		painelPrincipal.add(btnSair, gbc_btnSair);

		setContentPane(painelPrincipal);
		field_email.grabFocus();
	}

	@SuppressWarnings("static-access")
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
			user.setIpConexao(usuarioExportado.getIpConexao()).setPortaConexao(usuarioExportado.getPortaConexao());
			meuUsuario = conexaoCliente.conectarChat(user, usuarioExportado);

			if (meuUsuario == null) {
				JOptionPane.showMessageDialog(null, "Usuario nao encontrado no servidor. Verifique seu email e senha!");
				return;
			} else {
				Principal.getPrincipal().setVisible(true);
				dispose();
			}

		} catch (Exception e1) {
			String mensagem[] = e1.getCause().toString().split(":");
			JOptionPane.showMessageDialog(null, "Erro ao se conectar ao servidor! \n" + mensagem[1]);
			return;
		}
	}

	public static InterfaceServidor getConexaoCliente() {
		return conexaoCliente;
	}

	public static EntidadeUsuario getMeuUsuario() {
		return meuUsuario;
	}
}
