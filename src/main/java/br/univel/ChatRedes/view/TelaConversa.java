package br.univel.ChatRedes.view;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JTextArea;

import common.EntidadeUsuario;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TelaConversa extends JFrame {

	private static final long serialVersionUID = 1L;

	private static TelaConversa telaConversa;
	private static JTabbedPane tabbedPane;
	
	public TelaConversa() {
		setVisible(true);
		setSize(600, 400);
		setTitle("CONVERSAS");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 434, 0 };
		gridBagLayout.rowHeights = new int[] { 191, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evento) {
				if (evento.getKeyCode() == KeyEvent.VK_ESCAPE){
					tabbedPane.remove(tabbedPane.getSelectedIndex());
				}
			}
		});

		if (tabbedPane.getTabCount() == 0){
			dispose();
		}
	}
	
	public Conversa getAba(EntidadeUsuario usuario){
		int index = -1;
		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			if (tabbedPane.getTitleAt(i).equals(usuario.getEmail())) {
				tabbedPane.setSelectedIndex(i);
				index = i;
			}
		}		

		if (index < 0){
			tabbedPane.add(usuario.getEmail(), new Conversa(usuario));
			index = tabbedPane.getTabCount() - 1;
		}

		return (Conversa) tabbedPane.getComponentAt(index);
	}

	/**
	 * @return the telaConversa
	 */
	public static TelaConversa getTelaConversa() {
		if (telaConversa == null) {
			telaConversa = new TelaConversa();
		}
		telaConversa.setVisible(true);
		return telaConversa;
	}

	/**
	 * @return the tabbedPane
	 */
	public static JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
}
