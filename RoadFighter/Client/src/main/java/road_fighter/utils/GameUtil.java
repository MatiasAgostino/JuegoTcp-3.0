package road_fighter.utils;

import road_Fighter.server.Comandos;
import road_Fighter.server.Mensaje;

import road_fighter.Client;

public class GameUtil {
	private Client client;

	public GameUtil(Client client) {
		this.client = client;
	}
	
	public Mensaje crearMensaje(Comandos comando) {
		System.out.println(comando);
		Mensaje mensaje = new Mensaje(comando);
		
		mensaje.agregar(client.getUsername());
		mensaje.agregar(client.getUser().getX());
		mensaje.agregar(client.getUser().getY());
		mensaje.agregar(client.getUser().getVelocidadActualX());
		mensaje.agregar(client.getUser().getVelocidadActualY());
		
		//System.out.println(mensaje);
		
		return mensaje;
	}
}