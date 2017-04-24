package br.univel.ChatRedes.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.EntidadeUsuario;
import common.InterfaceServidor;
import common.Status;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private InterfaceServidor conexaoCliente;
	private static EntidadeUsuario usuario;
	private static Principal principal;

	private Principal() {
		this.conexaoCliente = Login.getConexaoCliente();
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("TadsZap");
		setBounds(100, 100, 350, 600);
		setUsuario(Login.getMeuUsuario());

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int resposta = JOptionPane.showConfirmDialog(null,
						String.format("Deseja realmente sair %s?", usuario.getNome()), "Atenção", dialogButton);

				if (resposta == JOptionPane.YES_OPTION) {
					resetarCliente();
				} else {
					setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				}
			}
		});

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnConexo = new JMenu("Conexão");
		menuBar.add(mnConexo);

		JMenuItem mntmAlterarSenha = new JMenuItem("Alterar Senha");
		mntmAlterarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlterarSenha.getAlterarSenha().setVisible(true);
			}
		});
		mnConexo.add(mntmAlterarSenha);

		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetarCliente();
				new Login();
			}
		});
		mnConexo.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 324, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 32, 531, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel painelCabecalho = new JPanel();
		GridBagConstraints gbc_painelCabecalho = new GridBagConstraints();
		gbc_painelCabecalho.insets = new Insets(0, 0, 5, 0);
		gbc_painelCabecalho.fill = GridBagConstraints.VERTICAL;
		gbc_painelCabecalho.gridx = 0;
		gbc_painelCabecalho.gridy = 0;
		contentPane.add(painelCabecalho, gbc_painelCabecalho);
		GridBagLayout gbl_painelCabecalho = new GridBagLayout();
		gbl_painelCabecalho.columnWidths = new int[] { 0, 0 };
		gbl_painelCabecalho.rowHeights = new int[] { 0, 0 };
		gbl_painelCabecalho.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_painelCabecalho.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		painelCabecalho.setLayout(gbl_painelCabecalho);

		JLabel lblNomeLogado = new JLabel(usuario.getNome());
		GridBagConstraints gbc_lblNomeLogado = new GridBagConstraints();
		gbc_lblNomeLogado.insets = new Insets(5, 5, 5, 5);
		gbc_lblNomeLogado.anchor = GridBagConstraints.NORTH;
		gbc_lblNomeLogado.gridx = 0;
		gbc_lblNomeLogado.gridy = 0;
		painelCabecalho.add(lblNomeLogado, gbc_lblNomeLogado);
		lblNomeLogado.setFont(new Font("Tahoma", Font.PLAIN, 21));

		JComboBox<Status> CBStatus = new JComboBox<Status>();
		GridBagConstraints gbc_CBStatus = new GridBagConstraints();
		gbc_CBStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_CBStatus.anchor = GridBagConstraints.NORTH;
		gbc_CBStatus.insets = new Insets(0, 0, 5, 0);
		gbc_CBStatus.gridx = 0;
		gbc_CBStatus.gridy = 1;
		contentPane.add(CBStatus, gbc_CBStatus);
		CBStatus.setModel(new DefaultComboBoxModel<Status>(Status.values()));
		CBStatus.setSelectedItem(usuario.getStatus());

		JPanel panel = TabelaUsuarios.getTabelaUsuarios();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		contentPane.add(panel, gbc_panel);

		CBStatus.removeItem(Status.OFFLINE);
		CBStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					usuario.setStatus(Status.valueOf(CBStatus.getSelectedItem().toString()));
					conexaoCliente.atualizarStatus(usuario);
				} catch (RemoteException e) {
					String mensagem[] = e.getCause().toString().split(":");
					JOptionPane.showMessageDialog(null, "Erro ao atualizar status! \n" + mensagem[1]);
					return;
				}
			}
		});
	}

	public void resetarCliente() {
		try {
			conexaoCliente.desconectarChat(usuario);
			TelaConversa.deleteTela();
			usuario = null;
			conexaoCliente = null;
			principal = null;
			Modelo.deletarModelo();
			dispose();
		} catch (RemoteException e) {
			String mensagem[] = e.getCause().toString().split(":");
			JOptionPane.showMessageDialog(null,
					"Erro ao se desconectar do servidor, a aplicação sera fechada! \n" + mensagem[1]);
			System.exit(0);
		}
	}

	public static EntidadeUsuario getUsuario() {
		return usuario;
	}

	@SuppressWarnings("static-access")
	public void setUsuario(EntidadeUsuario usuario) {
		this.usuario = usuario;
	}

	public static Principal getPrincipal() {
		if (principal == null) {
			principal = new Principal();
		}
		return principal;
	}
}
