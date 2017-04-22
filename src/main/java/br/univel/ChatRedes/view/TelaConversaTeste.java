package br.univel.ChatRedes.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import common.EntidadeUsuario;

public class TelaConversaTeste extends JFrame {

	private static final long serialVersionUID = 4790680784873764159L;
	private JPanel contentPane;
	private JTextArea textAreaDigitar;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	EntidadeUsuario user;

	public TelaConversaTeste(EntidadeUsuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 471, 0 };
		gbl_contentPane.rowHeights = new int[] { 329, 28, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		GridBagConstraints gbc_txAreaConversa = new GridBagConstraints();
		gbc_txAreaConversa.fill = GridBagConstraints.BOTH;
		gbc_txAreaConversa.insets = new Insets(5, 0, 5, 0);
		gbc_txAreaConversa.gridwidth = 2;
		gbc_txAreaConversa.gridx = 0;
		gbc_txAreaConversa.gridy = 0;

		JPanel panelConversa = new JPanel();
		GridBagConstraints gbc_panelConversa = new GridBagConstraints();
		gbc_panelConversa.insets = new Insets(5, 0, 0, 5);
		gbc_panelConversa.fill = GridBagConstraints.BOTH;
		gbc_panelConversa.gridx = 0;
		gbc_panelConversa.gridy = 0;
		contentPane.add(panelConversa, gbc_panelConversa);
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

		JTextArea textAreaConversa = new JTextArea();
		scrollPaneConversa.setViewportView(textAreaConversa);

		JPanel panelDigitar = new JPanel();
		GridBagConstraints gbc_panelDigitar = new GridBagConstraints();
		gbc_panelDigitar.insets = new Insets(5, 0, 0, 5);
		gbc_panelDigitar.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelDigitar.gridx = 0;
		gbc_panelDigitar.gridy = 1;
		contentPane.add(panelDigitar, gbc_panelDigitar);
		GridBagLayout gbl_panelDigitar = new GridBagLayout();
		gbl_panelDigitar.columnWidths = new int[] { 152, 79, 0 };
		gbl_panelDigitar.rowHeights = new int[] { 0, 25, 0 };
		gbl_panelDigitar.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelDigitar.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panelDigitar.setLayout(gbl_panelDigitar);

		JScrollPane scrollPaneDigitar = new JScrollPane();
		GridBagConstraints gbc_scrollPaneDigitar = new GridBagConstraints();
		gbc_scrollPaneDigitar.gridheight = 2;
		gbc_scrollPaneDigitar.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneDigitar.gridx = 0;
		gbc_scrollPaneDigitar.gridy = 0;
		panelDigitar.add(scrollPaneDigitar, gbc_scrollPaneDigitar);

		textAreaDigitar = new JTextArea();
		textAreaDigitar.setBorder(new EmptyBorder(2, 2, 2, 2));
		scrollPaneDigitar.setViewportView(textAreaDigitar);

		JButton btnEnviar = new JButton("Enviar");
		GridBagConstraints gbc_btnEnviar = new GridBagConstraints();
		gbc_btnEnviar.fill = GridBagConstraints.BOTH;
		gbc_btnEnviar.gridx = 1;
		gbc_btnEnviar.gridy = 0;
		panelDigitar.add(btnEnviar, gbc_btnEnviar);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enviarMensagem();
			}
		});

		JButton btnArquivo = new JButton("Arquivo");
		GridBagConstraints gbc_btnArquivo = new GridBagConstraints();
		gbc_btnArquivo.fill = GridBagConstraints.BOTH;
		gbc_btnArquivo.gridx = 1;
		gbc_btnArquivo.gridy = 1;
		panelDigitar.add(btnArquivo, gbc_btnArquivo);
		btnArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				procurarArquivo();
			}
		});

	}

	public void enviarMensagem() {

		Date data = new Date();
		String mensagem = textAreaDigitar.getText();
		Principal.enviaMsg(user, mensagem);
		textAreaDigitar.append(sdf.format(data) + " - " + Principal.getUsuario().getNome() + ": " + mensagem + "\n");

	}

	public void procurarArquivo() {
		JFileChooser arquivo = new JFileChooser();
		arquivo.setAcceptAllFileFilterUsed(false);
		arquivo.showSaveDialog(null);
		if (arquivo.getSelectedFile() != null) {

			String nome = arquivo.getSelectedFile().getName();
			String extensao = arquivo.getSelectedFile().getName().substring(
					arquivo.getSelectedFile().getName().lastIndexOf(".") + 1,
					arquivo.getSelectedFile().getName().length());
			Path path = Paths.get(arquivo.getSelectedFile().toString());

			Principal.enviaArq(user, arquivo.getSelectedFile());
		}
	}

	public EntidadeUsuario getUser() {
		return user;
	}

	public void setUser(EntidadeUsuario user) {
		this.user = user;
	}

}
