package br.univel.ChatRedes.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import common.Arquivo;
import common.EntidadeUsuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Conversa extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField field_envia_msg;
	private JTextArea field_area;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	EntidadeUsuario user;

	public EntidadeUsuario getUser() {
		return user;
	}

	public void setUser(EntidadeUsuario user) {
		this.user = user;
	}

	/**
	 * Create the panel.
	 */
	public Conversa(EntidadeUsuario user) {

		setUser(user);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);

		field_area = new JTextArea();
		field_area.setEditable(false);
		scrollPane.setViewportView(field_area);

		field_envia_msg = new JTextField();
		GridBagConstraints gbc_field_envia_msg = new GridBagConstraints();
		gbc_field_envia_msg.insets = new Insets(0, 0, 0, 5);
		gbc_field_envia_msg.fill = GridBagConstraints.HORIZONTAL;
		gbc_field_envia_msg.gridx = 0;
		gbc_field_envia_msg.gridy = 1;
		add(field_envia_msg, gbc_field_envia_msg);
		field_envia_msg.setColumns(10);

		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enviarMensagem();
			}
		});
		GridBagConstraints gbc_btnEnviar = new GridBagConstraints();
		gbc_btnEnviar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEnviar.gridx = 1;
		gbc_btnEnviar.gridy = 1;
		add(btnEnviar, gbc_btnEnviar);

		JButton btnArquivo = new JButton("Arquivo");
		GridBagConstraints gbc_btnArquivo = new GridBagConstraints();
		gbc_btnArquivo.gridx = 2;
		gbc_btnArquivo.gridy = 1;
		add(btnArquivo, gbc_btnArquivo);

	}

	public void enviaArquivo(EntidadeUsuario destinatario, Arquivo arquivo) {
		Principal.enviaArq(arquivo);
	}

	public void mostrar(EntidadeUsuario remetente, String mensagem) {

		Date data = new Date();
		field_area.append(sdf.format(data) + " - " + remetente.getNome() + ": " + mensagem);

	}

	public void enviarMensagem() {

		Date data = new Date();
		String mensagem = field_envia_msg.getText();
		Principal.enviaMsg(mensagem);
		field_area.append(sdf.format(data) + " - " + user.getNome() + ": " + mensagem);

	}

}
