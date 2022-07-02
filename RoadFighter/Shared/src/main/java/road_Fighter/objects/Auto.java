package road_Fighter.objects;

import road_Figther.utils.GameObject;

public abstract class Auto extends GameObject {
	protected double aceleracion;
	protected double velocidadMax;
	protected double velocidadActual = 100;

	public Auto(double x, double y) {
		super(Entidad.AUTO, x, y);
	}

	protected void choqueConMapa() {
		this.estaVivo = false;
	}
}