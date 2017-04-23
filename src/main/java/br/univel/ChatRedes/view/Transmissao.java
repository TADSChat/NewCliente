package br.univel.ChatRedes.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import common.EntidadeUsuario;
import common.InterfaceServidor;

public class Transmissao extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField field_mensagem;

	/**
	 * Create the frame.
	 * 
	 * @param conexaoCliente
	 */
	public Transmissao(final InterfaceServidor conexaoCliente, final EntidadeUsuario user) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Insira sua Mensagem: ");
		setBounds(100, 100, 450, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		field_mensagem = new JTextField();
		GridBagConstraints gbc_field_mensagem = new GridBagConstraints();
		gbc_field_mensagem.gridwidth = 2;
		gbc_field_mensagem.insets = new Insets(0, 0, 5, 0);
		gbc_field_mensagem.fill = GridBagConstraints.HORIZONTAL;
		gbc_field_mensagem.gridx = 0;
		gbc_field_mensagem.gridy = 0;
		contentPane.add(field_mensagem, gbc_field_mensagem);
		field_mensagem.setColumns(10);

		JButton btnConfirm = new JButton("Enviar");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					conexaoCliente.enviarMensagem(user, field_mensagem.getText());
					dispose();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnConfirm = new GridBagConstraints();
		gbc_btnConfirm.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConfirm.insets = new Insets(0, 0, 0, 5);
		gbc_btnConfirm.gridx = 0;
		gbc_btnConfirm.gridy = 1;
		contentPane.add(btnConfirm, gbc_btnConfirm);

		JButton btnCancel = new JButton("Cancelar");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancel.gridx = 1;
		gbc_btnCancel.gridy = 1;
		contentPane.add(btnCancel, gbc_btnCancel);
	}

}
