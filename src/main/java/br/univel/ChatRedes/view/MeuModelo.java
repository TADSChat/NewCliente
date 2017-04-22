package br.univel.ChatRedes.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import common.EntidadeUsuario;

public class MeuModelo extends DefaultListModel<EntidadeUsuario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Object[][] matriz;
	private int linha;

	public MeuModelo(List<EntidadeUsuario> lista) {

		lista.forEach(e -> {
			matriz[linha][0] = e.getNome();
			matriz[linha][1] = e.getStatus();

			linha++;
		});

	}

	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return linha;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return matriz[rowIndex][columnIndex];
	}

	public String getColumnName(int i) {

		switch (i) {
		case 0:
			return "Nome";
		case 1:
			return "Status";
		}

		return null;
	}

}