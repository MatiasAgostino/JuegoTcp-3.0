package server;

import java.util.ArrayList;
import java.util.List;

public class Salas {

	private static final int NAME_LENGTH = 120;
	private static List<SalaHandler> clients = new ArrayList<>();

	private Salas() {
	}

	public static void createSala(String salaName, int cantidad, String entorno) {
		if (salaName.isEmpty()) {
			return;
		}

		if (salaName.length() > NAME_LENGTH) {
			salaName = salaName.substring(0, NAME_LENGTH);
		}

		SalaHandler sala = new SalaHandler(salaName, cantidad, entorno);
		clients.add(sala);
	}

	public static void removeSala(String salaName, String entorno) {
		SalaHandler sala = new SalaHandler(salaName, entorno);
		if (clients.contains(sala)) {
			sala = clients.get(clients.indexOf(sala));
			sala.close();
			clients.remove(sala);
		}
	}

	public static SalaHandler addClient(String salaName, String entorno, ClientHandler client) {
		SalaHandler sala = new SalaHandler(salaName, entorno);

		if (clients.contains(sala)) {
			sala = clients.get(clients.indexOf(sala));
			if (sala.add(client)) {
				return sala;
			}
		}

		return null;
	}

	public static void removeClient(String salaName, String entorno, ClientHandler client) {
		SalaHandler sala = new SalaHandler(salaName, entorno);

		if (clients.contains(sala)) {
			sala = clients.get(clients.indexOf(sala));
			sala.remove(client);
		}
	}

	public static List<String> salasToString() {
		List<String> salas = new ArrayList<>();

		salas.add(String.valueOf(clients.size()));
		for (SalaHandler sala : clients) {
			salas.add(sala.getNombre());
			salas.add(String.valueOf(sala.getCantidadMaxima()));
			salas.add(sala.getOwner());
			salas.add(String.valueOf(sala.getCantidadActual()));
		}

		return salas;
	}
}