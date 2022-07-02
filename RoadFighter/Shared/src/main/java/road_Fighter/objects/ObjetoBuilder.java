package road_Fighter.objects;

import java.util.Random;

import roadFighter_interfaces.Updatable;
import road_Fighter.Config;
import road_Figther.utils.GameObject;
import road_Figther.utils.GameObjectBuilder;

public class ObjetoBuilder extends GameObject implements Updatable {

	private final long NANOS_IN_SECONDS = 1_000_000_000;
	private long obstaculoTime;
	private long powerUpTime;
	private long npcTime;
	private double xAnteriorObstaculo = 0;
	private double xAnteriorPowerUp = 0;
	private double xAnteriorNpc = 0;

	public ObjetoBuilder() {
		super(Entidad.BUILDER, 0, 0);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void update(double deltaTime) {
		long currentNano = System.nanoTime();

		if (currentNano - obstaculoTime > 0) {
			obstaculoTime = currentNano + (long) (Config.obstaculosPorSegundo) * NANOS_IN_SECONDS;
			this.generarObstaculo();
		}
		if (currentNano - powerUpTime > 0) {
			powerUpTime = currentNano + (long) (Config.powerUpsPorSegundo) * NANOS_IN_SECONDS;
			this.generarPowerUp();
		}

		if (currentNano - npcTime > 0) {
			npcTime = currentNano + (long) (Config.npcsPorSegundo) * NANOS_IN_SECONDS;
			this.generarNpc();
		}
	}

	private void generarObstaculo() {
		Random random = new Random();
		double posX = random.nextInt(Config.xBaseGeneracion) + Config.margenDeAleatoreidad;
		double posY = -random.nextInt(Config.yBaseGeneracion) - Config.limiteSuperior;

		while (posX == xAnteriorObstaculo || posX == xAnteriorPowerUp || posX == xAnteriorNpc)
			posX = random.nextInt(Config.xBaseGeneracion) + Config.margenDeAleatoreidad;

		Obstaculo obstaculo = new Obstaculo(posX, posY);
		xAnteriorObstaculo = posX;

		GameObjectBuilder.getInstance().add(obstaculo);

	}

	public void generarPowerUp() {
		Random random = new Random();
		double posX = random.nextInt(Config.xBaseGeneracion) + Config.margenDeAleatoreidad;
		double posY = -random.nextInt(Config.yBaseGeneracion) - Config.limiteSuperior;

		while (posX == xAnteriorObstaculo || posX == xAnteriorPowerUp || posX == xAnteriorNpc)
			posX = random.nextInt(Config.xBaseGeneracion) + Config.margenDeAleatoreidad;

		PowerUp powerUp = new PowerUp(posX, posY);

		xAnteriorPowerUp = posX;
		GameObjectBuilder.getInstance().add(powerUp);
	}

	public void generarNpc() {
		Random random = new Random();
		double posX = random.nextInt(Config.xBaseGeneracion) + Config.margenDeAleatoreidad;

		while (posX == xAnteriorNpc) {
			posX = random.nextInt(Config.xBaseGeneracion) + Config.margenDeAleatoreidad;
		}

		AutoNpc autoNpc = new AutoNpc(posX, Config.yBaseGeneracion);
		xAnteriorNpc = posX;

		GameObjectBuilder.getInstance().add(autoNpc);
	}
}
