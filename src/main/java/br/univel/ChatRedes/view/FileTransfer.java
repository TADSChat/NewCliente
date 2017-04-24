package br.univel.ChatRedes.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.EntidadeUsuario;

public class FileTransfer extends JFrame {

	private static final long serialVersionUID = -7747678608703047693L;

	private JPanel contentPane;

	Dimension dimensaoTela = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Create the frame.
	 */
	public FileTransfer(EntidadeUsuario remetente, File arquivo, byte[] bytes) {
		setResizable(false);

		setVisible(true);
		setTitle("Arquivo");
		setSize(453, 230);
		setLocation((dimensaoTela.width - this.getSize().width) / 2, (dimensaoTela.height - this.getSize().height) / 2);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setSize(400, 200);
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 130, 130, 221, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lblDesejaReceberEste = new JLabel("Remetente:");
		lblDesejaReceberEste.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
		GridBagConstraints gbc_lblDesejaReceberEste = new GridBagConstraints();
		gbc_lblDesejaReceberEste.anchor = GridBagConstraints.EAST;
		gbc_lblDesejaReceberEste.insets = new Insets(5, 0, 0, 5);
		gbc_lblDesejaReceberEste.gridx = 0;
		gbc_lblDesejaReceberEste.gridy = 0;
		contentPane.add(lblDesejaReceberEste, gbc_lblDesejaReceberEste);

		JLabel lblRemetente = new JLabel(remetente.getNome());
		lblRemetente.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		GridBagConstraints gbc_lblRemetente = new GridBagConstraints();
		gbc_lblRemetente.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRemetente.anchor = GridBagConstraints.NORTH;
		gbc_lblRemetente.gridwidth = 2;
		gbc_lblRemetente.insets = new Insets(5, 0, 0, 5);
		gbc_lblRemetente.gridx = 1;
		gbc_lblRemetente.gridy = 0;
		contentPane.add(lblRemetente, gbc_lblRemetente);

		JLabel lblNomeDoArquivo = new JLabel("Arquivo:");
		lblNomeDoArquivo.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 15));
		GridBagConstraints gbc_lblNomeDoArquivo = new GridBagConstraints();
		gbc_lblNomeDoArquivo.insets = new Insets(5, 0, 0, 5);
		gbc_lblNomeDoArquivo.anchor = GridBagConstraints.EAST;
		gbc_lblNomeDoArquivo.gridx = 0;
		gbc_lblNomeDoArquivo.gridy = 1;
		contentPane.add(lblNomeDoArquivo, gbc_lblNomeDoArquivo);

		JLabel lblArqNome = new JLabel(arquivo.getName());
		lblArqNome.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		GridBagConstraints gbc_lblArqNome = new GridBagConstraints();
		gbc_lblArqNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblArqNome.anchor = GridBagConstraints.NORTH;
		gbc_lblArqNome.gridwidth = 2;
		gbc_lblArqNome.insets = new Insets(5, 0, 0, 5);
		gbc_lblArqNome.gridx = 1;
		gbc_lblArqNome.gridy = 1;
		contentPane.add(lblArqNome, gbc_lblArqNome);

		JButton btnAceitar = new JButton("Aceitar");
		btnAceitar.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		btnAceitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path = "";

				JFileChooser diretorio = new JFileChooser();
				diretorio.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				diretorio.setAcceptAllFileFilterUsed(false);
				diretorio.showSaveDialog(null);
				if (diretorio.getSelectedFile() != null) {
					path = diretorio.getSelectedFile().toString();
				} else {
					JOptionPane.showMessageDialog(null,
							String.format("Download do arquivo [%s] foi cancelado!", arquivo.getName()));
					return;
				}

				File novoArquivo = new File(path + File.separator + arquivo.getName());
				try {
					Files.write(Paths.get(novoArquivo.getPath()), bytes, StandardOpenOption.CREATE);
				} catch (IOException exception) {
					JOptionPane.showMessageDialog(null, String.format("Erro ao criar o arquivo [%s]! \n%s",
							arquivo.getName(), exception.getCause().toString()));
					return;
				}

				JOptionPane.showMessageDialog(null,
						String.format("Arquivo [%s] baixado com sucesso!", arquivo.getName()));
				dispose();

			}
		});
		GridBagConstraints gbc_btnAceitar = new GridBagConstraints();
		gbc_btnAceitar.gridwidth = 3;
		gbc_btnAceitar.anchor = GridBagConstraints.NORTH;
		gbc_btnAceitar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAceitar.insets = new Insets(10, 30, 10, 30);
		gbc_btnAceitar.gridx = 0;
		gbc_btnAceitar.gridy = 2;
		contentPane.add(btnAceitar, gbc_btnAceitar);

		JButton btnRecusar = new JButton("Recusar");
		btnRecusar.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 16));
		btnRecusar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GridBagConstraints gbc_btnRecusar = new GridBagConstraints();
		gbc_btnRecusar.insets = new Insets(3, 30, 10, 30);
		gbc_btnRecusar.gridwidth = 3;
		gbc_btnRecusar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRecusar.anchor = GridBagConstraints.NORTH;
		gbc_btnRecusar.gridx = 0;
		gbc_btnRecusar.gridy = 3;
		contentPane.add(btnRecusar, gbc_btnRecusar);
	}
}
