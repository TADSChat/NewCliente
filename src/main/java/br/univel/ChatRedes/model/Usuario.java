package br.univel.ChatRedes.model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import br.univel.ChatRedes.view.FileTransfer;
import br.univel.ChatRedes.view.Modelo;
import br.univel.ChatRedes.view.TabelaUsuarios;
import br.univel.ChatRedes.view.TelaConversa;
import common.EntidadeUsuario;
import common.InterfaceUsuario;
import common.Status;
import common.TipoMensagem;

public class Usuario implements InterfaceUsuario {

	private static Usuario usuario;
	private static String ipConexao = "";
	private static Integer portaConexao = 1819;
	private static InetAddress IP;
	private static Modelo modelo;
	private EntidadeUsuario todos;

	public Usuario() {
		usuario = this;
		todos = new EntidadeUsuario().setNome("Transmissão").setId(0).setEmail("TODOS").setStatus(Status.ONLINE);
	}

	public static void exportar() {
		try {
			InterfaceUsuario interfaceUsuario = (InterfaceUsuario) UnicastRemoteObject.exportObject(usuario, 0);
			Registry registry = LocateRegistry.createRegistry(getPorta());
			registry.rebind(InterfaceUsuario.NOME, interfaceUsuario);
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

	@SuppressWarnings("static-access")
	@Override
	public void receberMensagem(EntidadeUsuario remetente, TipoMensagem tipoMensagem, String mensagem) throws RemoteException {
		if(tipoMensagem == TipoMensagem.PRIVADA){
			TelaConversa.getTelaConversa().abrirAba(remetente).mostrarMensagem(remetente.getNome(), mensagem, Color.BLUE);
		}else{
			TelaConversa.getTelaConversa().abrirAba(todos).mostrarMensagem(remetente.getNome(), mensagem, Color.BLUE);
		}
	}

	@Override
	public void receberArquivo(EntidadeUsuario remetente, File arquivo, byte[] bytes) throws RemoteException {
		new FileTransfer(remetente, arquivo, bytes);
	}

	@SuppressWarnings("static-access")
	@Override
	public void receberListaParticipantes(List<EntidadeUsuario> lista) throws RemoteException {
		Modelo.deletarModelo();
		TabelaUsuarios.getTabelaUsuarios().getTable().setModel(Modelo.getModelo(lista));
		TabelaUsuarios.getTabelaUsuarios().configurarModelo();
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
	public synchronized void desconectarForcado() throws RemoteException, SocketException {
		System.exit(0);
	}

}
