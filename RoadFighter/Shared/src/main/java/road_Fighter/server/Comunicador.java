package road_Fighter.server;

public interface Comunicador {

	void accion(String[] comandos);
	void enviar(Mensaje msg);
}