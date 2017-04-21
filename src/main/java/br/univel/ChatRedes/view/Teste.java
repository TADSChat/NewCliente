package br.univel.ChatRedes.view;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import common.Arquivo;
import common.EntidadeUsuario;
import common.InterfaceServidor;
import common.InterfaceUsuario;

public class Teste implements InterfaceUsuario {

	public Teste() {
		try {
			InterfaceUsuario interfaceU = (InterfaceUsuario) UnicastRemoteObject.exportObject(this, 0);
			Registry registry = LocateRegistry.createRegistry(1819);
			registry.rebind(InterfaceServidor.NOME, interfaceU);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void receberContatosOnline(List<EntidadeUsuario> lista) throws RemoteException {
		// TODO Auto-generated method stub

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
	public void receberListaParticipantes(ArrayList<EntidadeUsuario> arrayList) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
