package br.univel.ChatRedes.model;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import br.univel.ChatRedes.view.FileTransfer;
import br.univel.ChatRedes.view.Modelo;
import br.univel.ChatRedes.view.Principal;
import br.univel.ChatRedes.view.TabelaUsuarios;
import br.univel.ChatRedes.view.TelaConversa;
import br.univel.ChatRedes.view.Conversa;
import common.EntidadeUsuario;
import common.InterfaceServidor;
import common.InterfaceUsuario;

public class Usuario implements InterfaceUsuario {

	private static Usuario usuario;
	private static String ipConexao = "";
	private static Integer portaConexao = 1819;
	private static InetAddress IP;
	private static Modelo modelo;

	public Usuario() {
		usuario = this;
	}

	public static void exportar() {
		try {
			InterfaceUsuario interfaceU = (InterfaceUsuario) UnicastRemoteObject.exportObject(usuario, 0);
			Registry registry = LocateRegistry.createRegistry(getPorta());
			registry.rebind(InterfaceServidor.NOME, interfaceU);
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "Erro ao exportar registro para o servidor, reinicie o programa!");
			return;
		}

		try {
			IP = InetAddress.getLocalHost();
			ipConexao = IP.getHostAddress();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao ler IP local, reinicie o programa!");
			return;
		}
	}

	private static int getPorta() {
		ServerSocket socket = null;
		try {
			socket = new ServerSocket(portaConexao);
		} catch (IOException e) {
			portaConexao++;
			getPorta();
		} finally {
			try {
				socket.close();
			} catch (Exception e) {
				portaConexao++;
				getPorta();
			}
		}
		return portaConexao;
	}

	@Override
	public void receberMensagem(EntidadeUsuario remetente, String mensagem) throws RemoteException {
		TelaConversa.getTelaConversa().abrirAba(remetente).mostrarMensagem(remetente.getNome(), mensagem, Color.BLUE);
	}

	@Override
	public void receberArquivo(EntidadeUsuario remetente, File arquivo) throws RemoteException {
		new FileTransfer(remetente, arquivo);
	}

	@Override
	public void receberListaParticipantes(List<EntidadeUsuario> lista) throws RemoteException {
		Modelo.deletarModelo();
		TabelaUsuarios.getTabelaUsuarios().getTable().setModel(Modelo.getModelo(lista));
	}

	/**
	 * @return the ipConexao
	 */
	public static String getIpConexao() {
		return ipConexao;
	}

	/**
	 * @return the portaConexao
	 */
	public static Integer getPortaConexao() {
		return portaConexao;
	}

	/**
	 * @return the modelo
	 */
	public static Modelo getModelo() {
		return modelo;
	}

	@Override
	public void desconectarForcado() throws RemoteException {
		JOptionPane.showMessageDialog(null, "Voce foi desconectado do servidor! Reinicie a aplicação.");
		System.exit(0);
	}

}
