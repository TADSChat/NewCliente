package br.univel.ChatRedes.view;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import java.awt.GridBagConstraints;
import javax.swing.JTable;

public class TabelaUsuarios extends JPanel {

	private static final long serialVersionUID = 1L;

	private static TabelaUsuarios tabelaUsuarios;
	private static JTable table;

	private TabelaUsuarios() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

	public static TabelaUsuarios getTabelaUsuarios() {
		if (tabelaUsuarios == null) {
			tabelaUsuarios = new TabelaUsuarios();
		}
		return tabelaUsuarios;
	}

	/**
	 * @return the table
	 */
	public static synchronized JTable getTable() {
		return table;
	}
}
