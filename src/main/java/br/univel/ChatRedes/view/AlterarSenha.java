package br.univel.ChatRedes.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import common.Criptografia;

public class AlterarSenha extends JFrame {

	private static final long serialVersionUID = 100385206680100633L;

	private static AlterarSenha alterarSenha;
	private JPanel contentPane;
	private JPasswordField psSenhaAtual;
	private JPasswordField psConfirma;
	private JPasswordField psNovaSenha;
	private Dimension dimensaoTela = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Create the frame.
	 */
	private AlterarSenha() {
		setResizable(false);
		setTitle("Editar Dados");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 180);
		setVisible(true);
		setLocation((dimensaoTela.width - this.getSize().width) / 2, (dimensaoTela.height - this.getSize().height) / 2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JLabel lbSenhaAtual = new JLabel("Senha Atual:");
		GridBagConstraints gbc_lbSenhaAtual = new GridBagConstraints();
		gbc_lbSenhaAtual.gridwidth = 2;
		gbc_lbSenhaAtual.insets = new Insets(5, 0, 5, 5);
		gbc_lbSenhaAtual.anchor = GridBagConstraints.EAST;
		gbc_lbSenhaAtual.gridx = 0;
		gbc_lbSenhaAtual.gridy = 0;
		contentPane.add(lbSenhaAtual, gbc_lbSenhaAtual);

		psSenhaAtual = new JPasswordField();
		GridBagConstraints gbc_psSenhaAtual = new GridBagConstraints();
		gbc_psSenhaAtual.insets = new Insets(5, 0, 5, 0);
		gbc_psSenhaAtual.fill = GridBagConstraints.HORIZONTAL;
		gbc_psSenhaAtual.gridx = 2;
		gbc_psSenhaAtual.gridy = 0;
		contentPane.add(psSenhaAtual, gbc_psSenhaAtual);
		psSenhaAtual.setColumns(10);

		JLabel lbNovaSenha = new JLabel("Nova Senha:");
		GridBagConstraints gbc_lbNovaSenha = new GridBagConstraints();
		gbc_lbNovaSenha.gridwidth = 2;
		gbc_lbNovaSenha.anchor = GridBagConstraints.EAST;
		gbc_lbNovaSenha.insets = new Insets(5, 0, 5, 5);
		gbc_lbNovaSenha.gridx = 0;
		gbc_lbNovaSenha.gridy = 1;
		contentPane.add(lbNovaSenha, gbc_lbNovaSenha);

		psNovaSenha = new JPasswordField();
		psNovaSenha.setColumns(10);
		GridBagConstraints gbc_psNovaSenha = new GridBagConstraints();
		gbc_psNovaSenha.insets = new Insets(5, 0, 5, 0);
		gbc_psNovaSenha.fill = GridBagConstraints.HORIZONTAL;
		gbc_psNovaSenha.gridx = 2;
		gbc_psNovaSenha.gridy = 1;
		contentPane.add(psNovaSenha, gbc_psNovaSenha);

		JLabel lbConfirma = new JLabel("Confirmar Senha:");
		GridBagConstraints gbc_lbConfirma = new GridBagConstraints();
		gbc_lbConfirma.gridwidth = 2;
		gbc_lbConfirma.insets = new Insets(5, 0, 5, 5);
		gbc_lbConfirma.anchor = GridBagConstraints.EAST;
		gbc_lbConfirma.gridx = 0;
		gbc_lbConfirma.gridy = 2;
		contentPane.add(lbConfirma, gbc_lbConfirma);

		psConfirma = new JPasswordField();
		GridBagConstraints gbc_psConfirma = new GridBagConstraints();
		gbc_psConfirma.insets = new Insets(5, 0, 5, 0);
		gbc_psConfirma.fill = GridBagConstraints.HORIZONTAL;
		gbc_psConfirma.gridx = 2;
		gbc_psConfirma.gridy = 2;
		contentPane.add(psConfirma, gbc_psConfirma);
		psConfirma.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(salvarSenha());
		GridBagConstraints gbc_btnSalvar = new GridBagConstraints();
		gbc_btnSalvar.gridwidth = 3;
		gbc_btnSalvar.insets = new Insets(5, 0, 0, 0);
		gbc_btnSalvar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSalvar.gridx = 0;
		gbc_btnSalvar.gridy = 3;
		contentPane.add(btnSalvar, gbc_btnSalvar);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	private ActionListener salvarSenha() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String senhaAtual = Criptografia.criptografar(String.valueOf(psSenhaAtual.getPassword()));
				if (!senhaAtual.equals(Principal.getUsuario().getSenha())) {
					JOptionPane.showMessageDialog(null, "Senha atual incorreta, favor verificar!");
					psSenhaAtual.setText("");
					psNovaSenha.setText("");
					psConfirma.setText("");
					return;
				}
				
				if (!Arrays.equals(psNovaSenha.getPassword(), psConfirma.getPassword())){
					JOptionPane.showMessageDialog(null, "Nova senha nao confere com a confirmação, favor verificar!");
					psSenhaAtual.setText("");
					psNovaSenha.setText("");
					psConfirma.setText("");
					return;
				}
				
				String novaSenha = Criptografia.criptografar(String.valueOf(psNovaSenha.getPassword()));
				Principal.getUsuario().setSenha(novaSenha);
				try {
					if (!Login.getConexaoCliente().alterarSenha(Principal.getUsuario())){
						JOptionPane.showMessageDialog(null, "Erro ao atualizar senha, verifique sua conexão e tente novamente!");
						return;
					}
				} catch (RemoteException e) {
					JOptionPane.showMessageDialog(null, "Erro ao atualizar senha, verifique sua conexão e tente novamente!");
					return;
				}

				JOptionPane.showMessageDialog(null, "Senha atualizada com sucesso!");
				alterarSenha = null;
				dispose();
			}
		};
	}

	/**
	 * @return the alterarSenha
	 */
	public synchronized static AlterarSenha getAlterarSenha() {
		if (alterarSenha == null) {
			alterarSenha = new AlterarSenha();
		}
		return alterarSenha;
	}

}
