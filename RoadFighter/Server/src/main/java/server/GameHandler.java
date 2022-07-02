package server;

import java.util.ArrayList;
import java.util.List;

import road_Fighter.Config;
import road_Fighter.server.Comandos;
import road_Fighter.server.Mensaje;

public class GameHandler {
	private List<ClientHandler> players = new ArrayList<>();
	private String username;

	private double posX;
	private double posY;
	private double vX;
	private double vY;
	private double trayectoX = 15;
	private double trayectoY = 15;

	public GameHandler(String username, String posX, String posY, String vX, String vY) {
		this.username = username;
		this.posX = Double.valueOf(posX);
		this.posY = Double.valueOf(posY);
		this.vX = Double.valueOf(vX);
		this.vY = Double.valueOf(vY);
	}

	private Mensaje actualizarArriba() {
		Mensaje mensaje = new Mensaje(Comandos.ACTUALIZAR_ARRIBA);
		double posicionY = posY;

		if (posicionY <= 700 && posicionY > 400)
			posicionY -= trayectoY;

		mensaje.agregar(username);
		mensaje.agregar(posX);
		mensaje.agregar(posicionY);
		mensaje.agregar(vX);
		mensaje.agregar(vY);

		mensaje.agregar(posX);
		mensaje.agregar(posY + trayectoY);
		mensaje.agregar(vX);
		mensaje.agregar(vY);

		return mensaje;
	}

	public Mensaje crearMensaje(Comandos comando) {
		Mensaje mensaje = null;

		switch (comando) {
		case ACTUALIZAR_ARRIBA:
			mensaje = this.actualizarArriba();

			break;

		default:
			break;
		}

		return mensaje;
	}
}