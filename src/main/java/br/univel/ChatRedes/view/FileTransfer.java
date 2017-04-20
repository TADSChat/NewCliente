package br.univel.ChatRedes.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.Arquivo;
import common.EntidadeUsuario;

public class FileTransfer extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public FileTransfer(EntidadeUsuario remetente, Arquivo arquivo) {
		setVisible(true);
		setTitle("File Transfer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 250, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblDesejaReceberEste = new JLabel("Remetente:");
		lblDesejaReceberEste.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblDesejaReceberEste = new GridBagConstraints();
		gbc_lblDesejaReceberEste.anchor = GridBagConstraints.WEST;
		gbc_lblDesejaReceberEste.insets = new Insets(0, 0, 5, 5);
		gbc_lblDesejaReceberEste.gridx = 0;
		gbc_lblDesejaReceberEste.gridy = 0;
		contentPane.add(lblDesejaReceberEste, gbc_lblDesejaReceberEste);
		
		JLabel lblRemetente = new JLabel(remetente.getNome());
		GridBagConstraints gbc_lblRemetente = new GridBagConstraints();
		gbc_lblRemetente.gridwidth = 2;
		gbc_lblRemetente.gridheight = 2;
		gbc_lblRemetente.insets = new Insets(0, 0, 5, 0);
		gbc_lblRemetente.gridx = 0;
		gbc_lblRemetente.gridy = 1;
		contentPane.add(lblRemetente, gbc_lblRemetente);
		
		JLabel lblNomeDoArquivo = new JLabel("Nome do Arquivo:");
		lblNomeDoArquivo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNomeDoArquivo = new GridBagConstraints();
		gbc_lblNomeDoArquivo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeDoArquivo.anchor = GridBagConstraints.WEST;
		gbc_lblNomeDoArquivo.gridx = 0;
		gbc_lblNomeDoArquivo.gridy = 3;
		contentPane.add(lblNomeDoArquivo, gbc_lblNomeDoArquivo);
		
		JLabel lblArqNome = new JLabel(arquivo.getNome());
		GridBagConstraints gbc_lblArqNome = new GridBagConstraints();
		gbc_lblArqNome.gridwidth = 2;
		gbc_lblArqNome.gridheight = 2;
		gbc_lblArqNome.insets = new Insets(0, 0, 5, 0);
		gbc_lblArqNome.gridx = 0;
		gbc_lblArqNome.gridy = 4;
		contentPane.add(lblArqNome, gbc_lblArqNome);
		
		JButton btnAceitar = new JButton("Aceitar");
		btnAceitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String path = "." + File.separatorChar + "shared" + File.separatorChar + arquivo.getNome();
					Files.write(Paths.get(path), arquivo.getDados(), StandardOpenOption.CREATE);
					
					JOptionPane.showMessageDialog(null, "Arquivo baixado com sucesso!");
					dispose();
				} catch (IOException e2) {
					throw new RuntimeException(e2);
				}
			}
		});
		GridBagConstraints gbc_btnAceitar = new GridBagConstraints();
		gbc_btnAceitar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAceitar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceitar.gridx = 0;
		gbc_btnAceitar.gridy = 7;
		contentPane.add(btnAceitar, gbc_btnAceitar);
		
		JButton btnRecusar = new JButton("Recusar");
		btnRecusar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnRecusar = new GridBagConstraints();
		gbc_btnRecusar.gridx = 1;
		gbc_btnRecusar.gridy = 7;
		contentPane.add(btnRecusar, gbc_btnRecusar);
	}

}
