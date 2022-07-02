package road_Fighter.server;

public class Mensaje {
	private static final String ESPECIAL = ":";

	private String mensage;

	public Mensaje(Comandos comando) {
		mensage = comando.name();
	}

	public <T> void agregar(T valor) {
		mensage += ESPECIAL + String.valueOf(valor);
	}

	public static String[] decodificar(String msg) {
		return msg.split(ESPECIAL);
	}

	public static Sala paresarSala(String nombre, String maxima, String entorno, String actual) {
		Sala sala = new Sala(nombre, Integer.parseInt(maxima), entorno);
		sala.setCantidadActual(Integer.parseInt(actual));
		return sala;
	}

	@Override
	public String toString() {
		return mensage;
	}
}