package br.univel.ChatRedes.view;

import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.swing.DefaultListModel;

import br.univel.ChatRedes.comum.MeuModelo;
import common.Arquivo;
import common.EntidadeUsuario;
import common.InterfaceServidor;
import common.InterfaceUsuario;

public class Usuario implements InterfaceUsuario {

	Integer porta = 1819;

	public Usuario() {
		try {
			InterfaceUsuario interfaceU = (InterfaceUsuario) UnicastRemoteObject.exportObject(this, 0);
			Registry registry = LocateRegistry.createRegistry(getPorta());
			registry.rebind(InterfaceServidor.NOME, interfaceU);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int getPorta() {
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
		// TODO Auto-generated method stub

	}

	@Override
	public void receberArquivo(EntidadeUsuario remetente, Arquivo arquivo) throws RemoteException {
		// TODO Auto-generated method stub

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
