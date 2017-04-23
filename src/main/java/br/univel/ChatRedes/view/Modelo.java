package br.univel.ChatRedes.view;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import common.EntidadeUsuario;
import common.Status;

public class Modelo extends AbstractTableModel implements TableModel {

	private static final long serialVersionUID = 1L;

	private static Modelo modelo;
	private Object[][] matriz;
	private int linha = 0;
	private static int index = 0;

	private static List<EntidadeUsuario> listaUsuarios;

	private Modelo(List<EntidadeUsuario> lista) {

		if (lista == null) {
			return;
		}

		listaUsuarios = lista;

		EntidadeUsuario usuarioTodos = new EntidadeUsuario().setNome("Transmissão").setId(0).setEmail("TODOS")
				.setStatus(Status.ONLINE);
		listaUsuarios.add(0, usuarioTodos);

		matriz = new Object[lista.size()][3];

		for (EntidadeUsuario usuario : lista) {
			matriz[linha][0] = usuario.getNome();
			matriz[linha][1] = usuario.getStatus();
			matriz[linha][2] = usuario;

			linha++;
		}
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

	/**
	 * @return the modelo
	 */
	public synchronized static Modelo getModelo(List<EntidadeUsuario> lista) {
		if (modelo == null) {
			modelo = new Modelo(lista);
		}
		return modelo;
	}

	public synchronized static void deletarModelo() {
		listaUsuarios = null;
		modelo = null;
	}

	/**
	 * @return the listaUsuarios
	 */
	public static synchronized List<EntidadeUsuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public static synchronized void removerMeuUsuario() {
		try {
			if (Login.getMeuUsuario() != null) {
				if (Login.getMeuUsuario().getId() != null) {
					index = -1;
					listaUsuarios.forEach(usuarioLista -> {
						if (usuarioLista != null) {
							if (usuarioLista.getId() != null) {
								if (usuarioLista.getId().equals(Login.getMeuUsuario().getId())) {
									index = listaUsuarios.indexOf(usuarioLista);
								}
							}
						}
					});
					if (index >= 0) {
						listaUsuarios.remove(index);
					}
				}
			}
		} catch (Exception e) {
			return;
		}
	}
}