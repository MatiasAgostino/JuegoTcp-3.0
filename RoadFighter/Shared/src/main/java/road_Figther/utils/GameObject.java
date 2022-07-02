package road_Figther.utils;

import javafx.scene.Node;
import road_Fighter.objects.Entidad;

public abstract class GameObject {
	protected double x;
	protected double y;
	protected boolean estaVivo;
	private final Entidad clase;
	protected Node render;
	public abstract void destroy();

	
	public GameObject (Entidad entidad, double x, double y) {
		this.clase = entidad;
		this.x = x;
		this.y = y;
	}

	public boolean estaVivo() {
		return this.estaVivo;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.x;
	}
	
	public Node getRender() {
		return render;
	}
}
