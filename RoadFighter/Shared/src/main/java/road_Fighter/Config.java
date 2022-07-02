package road_Fighter;

public class Config {

	public final static int altoBase = 920;
	public final static int anchoBase = 1080;

	public final static int limiteSuperior = 400;
	public final static int limiteInferior = 750;
	public final static int limiteIzquierda = 150;
	public final static int limiteDerecha = 960;

	public final static double aceleracionBaseAuto = 200;
	public final static double velocidadNpc = 200;
	public final static double velocidadPowerUps = 100;
	public final static double velocidadObstaculos = 100;
	public final static double velocidadMaxAutoX = 500;
	public final static double velocidadNpcChoque = 2;
	public static double velocidadMaxAutoY = 500;

	public final static double obstaculosPorSegundo = 2;
	public final static double powerUpsPorSegundo = 5;
	public final static double npcsPorSegundo = 1;

	public static double aceleracionBasePista = 300;
	public static double velocidadBaseDinamica = 1000;
	public static double distanciaAMeta = 20000;

	public final static double velocidadBase = 1000;
	public final static double velocidadMaxPowerUp = 3000;

	public final static int framesPowerUp = 270;
	public final static int framesAnimacionAutoChoque = 270;
	public final static int xBaseGeneracion = 810;
	public final static int yBaseGeneracion = 1000;
	public final static int margenDeAleatoreidad = 150;
	
	public static final int maxJugadores = 8;
	public static final int minJugadores = 2;

	private Config() {
	}
}
