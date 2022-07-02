package server;

import java.util.ArrayList;
import java.util.List;

import road_Fighter.server.Comandos;
import road_Fighter.server.Mensaje;
import road_Fighter.server.Sala;

public class SalaHandler extends Sala {

	private List<ClientHandler> players = new ArrayList<>();

	public SalaHandler(String nombre, int cantidadMaxima, String owner) {
		super(nombre, cantidadMaxima, owner);
	}

	public SalaHandler(String nombre, String owner) {
		super(nombre, owner);
	}

	public void close() {
		players.forEach(ClientHandler::sendToLobby);
		players.clear();
	}

	public boolean add(ClientHandler client) {
		if (this.cantidadActual < this.cantidadMaxima) {
			players.add(client);
			this.cantidadActual++;
			return true;
		}

		return false;
	}

	public void remove(ClientHandler client) {
		if (players.remove(client)) {
			this.cantidadActual--;
			client.sendToLobby();
		}
	}

	public Mensaje join() {
		Mensaje join = new Mensaje(Comandos.UNIRSE_SALA);

		join.agregar(this.nombre);
		join.agregar(this.cantidadMaxima);
		join.agregar(this.entorno);
		join.agregar(this.cantidadActual);
		join.agregar(this.players.size());

		for (ClientHandler player : players) {
			join.agregar(player.getUsername());
		}

		return join;
	}

	public void sendToAll(Mensaje msg) {
		for (ClientHandler player : players) {
			player.enviar(msg);
		}
	}
}