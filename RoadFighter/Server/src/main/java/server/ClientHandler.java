package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import road_Fighter.server.Comandos;
import road_Fighter.server.Comunicador;
import road_Fighter.server.Mensaje;

public class ClientHandler implements Runnable, Comunicador {

	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private String username;
	private SalaHandler currentSala;
	private GameHandler currentGame;

	public ClientHandler(Socket socket) {
		try {
			this.socket = socket;
			this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.username = in.readUTF();
		} catch (IOException e) {
			close();
		}
	}

	@Override
	public void run() {
		String msg;

		while (socket.isConnected()) {
			try {
				msg = in.readUTF();
				accion(Mensaje.decodificar(msg));
			} catch (IOException e) {
				close();
				break;
			}
		}
	}

	@Override
	public synchronized void accion(String[] comandos) {
		Comandos comando = Comandos.valueOf(comandos[0]);

		switch (comando) {

		case CREAR_SALA:
			Salas.createSala(comandos[1], Integer.valueOf(comandos[2]), this.username);
			break;

		case OBTENER_SALAS:
			Mensaje msg = new Mensaje(Comandos.OBTENER_SALAS);
			List<String> salas = Salas.salasToString();

			for (String sala : salas) {
				msg.agregar(sala);
			}

			enviar(msg);
			break;

		case ELIMINAR_SALA:
			Salas.removeSala(comandos[1], username);
			break;

		case UNIRSE_SALA:
			this.currentSala = Salas.addClient(comandos[1], comandos[2], this);
			if (currentSala != null)
				currentSala.sendToAll(currentSala.join());
			break;

		case AL_LOBBY:
			currentSala.remove(this);
			currentSala.sendToAll(currentSala.join());
			break;

		case EMPEZAR_JUEGO:
			Mensaje empezar = new Mensaje(Comandos.EMPEZAR_JUEGO);
			System.out.println("ClientHandler - Empezar");
			currentSala.sendToAll(empezar);
			break;

		case MOV_ARRIBA:
			this.currentGame = new GameHandler(comandos[1], comandos[2], comandos[3], comandos[4], comandos[5]);

			Mensaje moverArriba = this.currentGame.crearMensaje(Comandos.ACTUALIZAR_ARRIBA);

			currentSala.sendToAll(moverArriba);
			break;
		default:
			break;
		}
	}

	@Override
	public void enviar(Mensaje msg) {
		try {
			this.out.writeUTF(msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendToLobby() {
		Mensaje msg = new Mensaje(Comandos.AL_LOBBY);
		enviar(msg);
	}

	public String getUsername() {
		return username;
	}

	public void close() {
		if(currentSala != null)
			currentSala.remove(this);
		try {
			if (this.in != null) {
				this.in.close();
			}
			if (this.out != null) {
				this.out.close();
			}
			if (this.socket != null) {
				this.socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("[SERVER-INFO] - Cliente desconectado.");
	}
}