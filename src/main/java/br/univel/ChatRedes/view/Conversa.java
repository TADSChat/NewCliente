package br.univel.ChatRedes.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import common.EntidadeUsuario;
import common.InterfaceUsuario;
import javax.swing.ScrollPaneConstants;

public class Conversa extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea textAreaDigitar;
	private JTextPaneConversa painelConversa;
	private EntidadeUsuario destinatario;
	@SuppressWarnings("unused")
	private Conversa conversa;

	static SimpleDateFormat dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	EntidadeUsuario user;

	@SuppressWarnings("static-access")
	public Conversa(EntidadeUsuario destinatario) {
		this.destinatario = destinatario;

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 471, 0 };
		gbl_contentPane.rowHeights = new int[] { 329, 28, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gbl_contentPane);

		GridBagConstraints gbc_txAreaConversa = new GridBagConstraints();
		gbc_txAreaConversa.fill = GridBagConstraints.BOTH;
		gbc_txAreaConversa.insets = new Insets(5, 0, 5, 0);
		gbc_txAreaConversa.gridwidth = 2;
		gbc_txAreaConversa.gridx = 0;
		gbc_txAreaConversa.gridy = 0;
		
		JPanel panelConversa = new JPanel();
		panelConversa.setBorder(new EmptyBorder(5, 5, 0, 5));
		GridBagConstraints gbc_panelConversa = new GridBagConstraints();
		gbc_panelConversa.insets = new Insets(5, 0, 0, 5);
		gbc_panelConversa.fill = GridBagConstraints.BOTH;
		gbc_panelConversa.gridx = 0;
		gbc_panelConversa.gridy = 0;
		this.add(panelConversa, gbc_panelConversa);
		
		GridBagLayout gbl_panelConversa = new GridBagLayout();
		gbl_panelConversa.columnWidths = new int[] { 282, 0 };
		gbl_panelConversa.rowHeights = new int[] { 24, 0 };
		gbl_panelConversa.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panelConversa.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panelConversa.setLayout(gbl_panelConversa);

		JScrollPane scrollPaneConversa = new JScrollPane();
		GridBagConstraints gbc_scrollPaneConversa = new GridBagConstraints();
		gbc_scrollPaneConversa.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneConversa.gridx = 0;
		gbc_scrollPaneConversa.gridy = 0;
		panelConversa.add(scrollPaneConversa, gbc_scrollPaneConversa);

		painelConversa = new JTextPaneConversa();
		scrollPaneConversa.setViewportView(painelConversa);
		painelConversa.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				textAreaDigitar.grabFocus();
			}

			@Override
			public void focusLost(FocusEvent e) {
				return;
			}
		});

		JPanel painelDigitar = new JPanel();
		painelDigitar.setBorder(new EmptyBorder(5, 5, 5, 5));
		GridBagConstraints gbc_panelDigitar = new GridBagConstraints();
		gbc_panelDigitar.insets = new Insets(5, 0, 0, 5);
		gbc_panelDigitar.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelDigitar.gridx = 0;
		gbc_panelDigitar.gridy = 1;
		this.add(painelDigitar, gbc_panelDigitar);
		GridBagLayout gbl_panelDigitar = new GridBagLayout();
		gbl_panelDigitar.columnWidths = new int[] { 152, 79, 0 };
		gbl_panelDigitar.rowHeights = new int[] { 0, 25, 0 };
		gbl_panelDigitar.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelDigitar.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		painelDigitar.setLayout(gbl_panelDigitar);

		JScrollPane scrollPaneDigitar = new JScrollPane();
		GridBagConstraints gbc_scrollPaneDigitar = new GridBagConstraints();
		gbc_scrollPaneDigitar.gridheight = 2;
		gbc_scrollPaneDigitar.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneDigitar.gridx = 0;
		gbc_scrollPaneDigitar.gridy = 0;
		painelDigitar.add(scrollPaneDigitar, gbc_scrollPaneDigitar);

		textAreaDigitar = new JTextArea();
		textAreaDigitar.setBorder(new EmptyBorder(2, 2, 2, 2));
		textAreaDigitar.grabFocus();
		textAreaDigitar.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evento) {
				if (evento.getKeyCode() == KeyEvent.VK_ENTER) {
					evento.consume();
					enviarMensagem();
					return;
				}
			}
		 });

		scrollPaneDigitar.setViewportView(textAreaDigitar);

		JButton btnEnviar = new JButton("Enviar");
		GridBagConstraints gbc_btnEnviar = new GridBagConstraints();
		gbc_btnEnviar.insets = new Insets(2, 3, 2, 2);
		gbc_btnEnviar.fill = GridBagConstraints.BOTH;
		gbc_btnEnviar.gridx = 1;
		gbc_btnEnviar.gridy = 0;
		painelDigitar.add(btnEnviar, gbc_btnEnviar);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enviarMensagem();
			}
		});

		JButton btnArquivo = new JButton("Arquivo");
		if (destinatario.getId() == 0) {
			btnArquivo.setEnabled(false);
		}
		GridBagConstraints gbc_btnArquivo = new GridBagConstraints();
		gbc_btnArquivo.insets = new Insets(2, 3, 2, 2);
		gbc_btnArquivo.fill = GridBagConstraints.BOTH;
		gbc_btnArquivo.gridx = 1;
		gbc_btnArquivo.gridy = 1;
		painelDigitar.add(btnArquivo, gbc_btnArquivo);
		btnArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				procurarArquivo();
			}
		});

		conversa = this;
	}

	public void mostrarMensagem(String remetente, String mensagem, Color cor) {
		painelConversa.append(cor, getCabecalho(remetente));
		painelConversa.append(Color.BLACK, mensagem + "\n");
	}

	public void enviarMensagem() {
		if (textAreaDigitar.getText().trim().equals("")) {
			return;
		}

		if (destinatario.getId() == 0) {
			try {
				Login.getConexaoCliente().enviarMensagem(Login.getMeuUsuario(), textAreaDigitar.getText());
			} catch (RemoteException e) {
				String mensagem[] = e.getCause().toString().split(":");
				JOptionPane.showMessageDialog(null, "Erro ao enviar mensagem para todos! \n " + mensagem[1]);
				TelaConversa.getTabbedPane().remove(this);
			}
		} else {
			try {
				Login.getConexaoCliente().enviarMensagem(Login.getMeuUsuario(), destinatario,
						textAreaDigitar.getText());
			} catch (RemoteException e) {
				String mensagem[] = e.getCause().toString().split(":");
				JOptionPane.showMessageDialog(null, "Erro ao enviar mensagem para o destinatario! \n " + mensagem[1]);
				TelaConversa.getTabbedPane().remove(this);
			}
		}

		mostrarMensagem(Login.getMeuUsuario().getNome(), textAreaDigitar.getText(), Color.RED);
		textAreaDigitar.setText("");
	}

	private static String getCabecalho(String nome) {
		return (dataFormatada.format(new Date()) + " - " + nome.toUpperCase() + " disse: ");
	}

	public void procurarArquivo() {
		JFileChooser arquivo = new JFileChooser();
		arquivo.setAcceptAllFileFilterUsed(false);
		arquivo.showSaveDialog(null);
		if (arquivo.getSelectedFile() != null) {
			try {
				Path path = Paths.get(arquivo.getSelectedFile().getPath());
				byte[] dados = Files.readAllBytes(path);
				Registry registry = LocateRegistry.getRegistry(destinatario.getIpConexao(),
						destinatario.getPortaConexao());
				InterfaceUsuario conexaoCliente = (InterfaceUsuario) registry.lookup(InterfaceUsuario.NOME);

				conexaoCliente.receberArquivo(Login.getMeuUsuario(), arquivo.getSelectedFile(), dados);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Erro ao enviar arquivo ao cliente!");
			}
		}
	}
}
