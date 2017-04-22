package br.univel.ChatRedes.model;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import br.univel.ChatRedes.view.FileTransfer;
import br.univel.ChatRedes.view.MeuModelo;
import br.univel.ChatRedes.view.Principal;
import common.Arquivo;
import common.EntidadeUsuario;
import common.InterfaceServidor;
import common.InterfaceUsuario;

public class Usuario implements InterfaceUsuario {

	private static Usuario usuario;
	static Integer porta = 1819;

	public Usuario() {
		usuario = this;
	}

	public static void exportar() {
		try {
			InterfaceUsuario interfaceU = (InterfaceUsuario) UnicastRemoteObject.exportObject(usuario, 0);
			Registry registry = LocateRegistry.createRegistry(getPorta());
			registry.rebind(InterfaceServidor.NOME, interfaceU);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static int getPorta() {
		ServerSocket socket = null;
		try {
			socket = new ServerSocket(porta);
		} catch (IOException e) {
			porta++;
			getPorta();
		} finally {
			try {
				socket.close();
			} catch (Exception e) {
				porta++;
				getPorta();
			}
		}
		return porta;
	}

	@Override
	public void receberMensagem(EntidadeUsuario remetente, String mensagem) throws RemoteException {
		Principal.receberMensagem(remetente, mensagem);
	}

	@Override
	public void receberArquivo(EntidadeUsuario remetente, File arquivo) throws RemoteException {
		new FileTransfer(remetente, arquivo);
	}

	@Override
	public void receberListaParticipantes(List<EntidadeUsuario> lista) throws RemoteException {
		System.out.println("recebi a lista: " + lista.size());

		DefaultListModel<EntidadeUsuario> modelo = new MeuModelo(lista);

		lista.forEach(usuario -> {
			System.out.println("adicionando usuario " + usuario.getNome());
			if (!modelo.contains(usuario)) {
				modelo.addElement(usuario);
				System.out.println("usuario adicionado" + usuario.getNome());
			}
			;
		});
		System.out.println("MODELO: " + modelo);
		Principal.getListaUsuarios().setModel(modelo);
	}

}
