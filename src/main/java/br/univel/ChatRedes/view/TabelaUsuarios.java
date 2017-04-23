package br.univel.ChatRedes.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import common.EntidadeUsuario;

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
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					EntidadeUsuario destinatario = getUsuarioTabela();
					if (destinatario == null) {
						return;
					}
					TelaConversa.getTelaConversa().abrirAba(destinatario);
				}
			}
		});
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

	private EntidadeUsuario getUsuarioTabela() {
		if (table.getSelectedRow() < 0) {
			JOptionPane.showMessageDialog(null, "Nenhum usuario foi selecionado!");
			return null;
		}

		Integer linha = table.getSelectedRow();

		return (EntidadeUsuario) table.getModel().getValueAt(linha, 2);
	}

	public void configurarModelo() {
		if (table != null) {
			table.getColumnModel().getColumn(0).setResizable(false);
			table.getColumnModel().getColumn(0).setPreferredWidth(200);
			table.getColumnModel().getColumn(1).setResizable(false);
			table.getColumnModel().getColumn(1).setPreferredWidth(30);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setDefaultEditor(Object.class, null);
		}

	}
}
