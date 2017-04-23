package br.univel.ChatRedes.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import common.EntidadeUsuario;

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
				if (evento.getKeyCode() == KeyEvent.VK_ESCAPE) {
					tabbedPane.remove(tabbedPane.getSelectedIndex());
				}
			}
		});

		if (tabbedPane.getTabCount() == 0) {
			dispose();
		}
	}

	public Conversa abrirAba(EntidadeUsuario usuario) {
		int index = -1;
		for (int i = 0; i < tabbedPane.getTabCount(); i++) {
			if (tabbedPane.getTitleAt(i).equals(usuario.getEmail())) {
				tabbedPane.setSelectedIndex(i);
				index = i;
			}
		}

		if (index < 0) {
			Conversa conversa = new Conversa(usuario);
			tabbedPane.add(usuario.getEmail(), conversa);
			tabbedPane.setSelectedComponent(conversa);
			int i2 = tabbedPane.getSelectedIndex();
			tabbedPane.setTabComponentAt(i2, new JButtonTabbedPane(tabbedPane));
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

	public static void deleteTela() {
		if (telaConversa != null){
			telaConversa.dispose();			
		}
		telaConversa = null;
		tabbedPane = null;
	}
}
