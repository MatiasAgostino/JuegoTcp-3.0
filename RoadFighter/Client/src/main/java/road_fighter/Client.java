package road_fighter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import road_Fighter.objects.AutoJugador;
import road_Fighter.server.Comandos;
import road_Fighter.server.Comunicador;
import road_Fighter.server.Mensaje;
import road_Fighter.server.Sala;
import road_fighter.scenes.SceneHandler;

public class Client implements Comunicador {
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private String username;
	@SuppressWarnings("unused")
	private SceneHandler currentScene;
	private List<Sala> salas;
	private Sala currentSala;
	private List<String> playersInSala;
	public Comandos comandoPendiente;
	public AutoJugador user;

	public Client(String ip, int port, String username, SceneHandler currentScene) throws IOException {
		this.socket = new Socket(ip, port);
		this.username = username;
		this.currentScene = currentScene;
		this.in = new DataInputStream(socket.getInputStream());
		this.out = new DataOutputStream(socket.getOutputStream());
		this.out.writeUTF(username);
	}

	public void listen() {
		Thread listener = new Thread(() -> {
			String msg;
			while (socket.isConnected()) {
				try {
					msg = in.readUTF();
					accion(Mensaje.decodificar(msg));
				} catch (IOException e) {
					close();
				}
			}
		});

		listener.start();
	}

	@Override
	public void enviar(Mensaje msg) {
		try {
			this.out.writeUTF(msg.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void accion(String[] comandos) {
		Comandos comando = Comandos.valueOf(comandos[0]);

		switch (comando) {
		case OBTENER_SALAS:
			int cantSalas = Integer.parseInt(comandos[1]);

			this.salas = new ArrayList<>(cantSalas);

			for (int i = 0, j = 0; j < cantSalas; i += 4, j++) {

				Sala sala = Mensaje.paresarSala(comandos[2 + i], comandos[3 + i], comandos[4 + i], comandos[5 + i]);
				this.salas.add(sala);
			}

			break;

		case AL_LOBBY:
			break;

		case UNIRSE_SALA:
			this.currentSala = Mensaje.paresarSala(comandos[1], comandos[2], comandos[3], comandos[4]);

			int size = Integer.parseInt(comandos[5]);

			this.playersInSala = new ArrayList<>(size);

			for (int i = 0; i < size; i++) {
				this.playersInSala.add(comandos[6 + i]);
			}

			break;

		case ACTUALIZAR_ARRIBA:
			System.out.println("Comando[3]" + comandos[3]);
			user.setY(Double.valueOf(comandos[3]));;
			break;
		default:
			break;
		}

		comandoPendiente = comando;
		notifyAll();
	}

	public synchronized List<String> getSalasNames() {
		Mensaje msg = new Mensaje(Comandos.OBTENER_SALAS);
		enviar(msg);

		try {
			wait();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			e.printStackTrace();
		}

		List<String> salasNames = new ArrayList<>();
		for (Sala sala : salas) {
			salasNames.add(sala.toString());
		}

		return salasNames;
	}

	public boolean isOwnerOf(String salaName) {
		for (Sala sala : salas) {
			if (sala.getNombre().equalsIgnoreCase(salaName) && sala.getOwner().equals(username)) {
				return true;
			}
		}
		return false;
	}

	public void close() {
		try {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setCurrentScene(SceneHandler currentScene) {
		this.currentScene = currentScene;
	}

	public String getUsername() {
		return username;
	}

	public Sala getCurrentSala() {
		return currentSala;
	}

	public List<String> getPlayersInSala() {
		return playersInSala;
	}

	public Sala getSala(int index) {
		if (index < 0 || index >= salas.size()) {
			return null;
		}

		return this.salas.get(index);
	}

	public AutoJugador getUser() {
		return this.user;
	}

	public void setUser(AutoJugador autoJugador) {
		this.user = autoJugador;
	}
}