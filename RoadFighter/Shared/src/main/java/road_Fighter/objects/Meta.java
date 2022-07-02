package road_Fighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import roadFighter_interfaces.Collideable;
import roadFighter_interfaces.Renderable;
import roadFighter_interfaces.Updatable;
import road_Fighter.Config;
//import roadFighter_interfaces.Updatable;
import road_Figther.utils.GameObject;

public class Meta extends GameObject implements Updatable, Renderable, Collideable {
	private final int HEIGHT = 100;
	private final int WIDTH = 850;
	private boolean movArriba;
	private boolean movAbajo;
	private double velocidadActual;

	private ImageView render;
	private Rectangle collider;
	
	public Meta() {
		super(Entidad.META, 130, -1*Config.distanciaAMeta);
		Image backgroundImage = new Image("file:src/main/resources/img/Finish-Line-PNG-HD.png", WIDTH, HEIGHT, false,
				false);
		ImageView bg = new ImageView(backgroundImage);
		render = bg;
		render.setX(this.x);
		render.setY(this.y);
		
		this.collider = new Rectangle(this.x, this.y, WIDTH,
				HEIGHT);

	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void destroy() {
	}

	public void update(double deltaTime) {
		if (this.movArriba) {
			if (this.velocidadActual + (Config.aceleracionBasePista * deltaTime) > Config.velocidadBase) {
				this.velocidadActual = Config.velocidadBase;
			} else {
				this.velocidadActual += Config.aceleracionBasePista * deltaTime;
			}
		} else if (!this.movArriba || this.movAbajo) {
			if (this.velocidadActual - (Config.aceleracionBasePista * deltaTime) < 0) {
				this.velocidadActual = 0;
			} else {
				this.velocidadActual -= Config.aceleracionBasePista * deltaTime;
			}
			if (this.movAbajo)
				this.velocidadActual *= 0.95;
		}

		this.render.setY(this.render.getY() + (this.velocidadActual * deltaTime));
		this.collider.setY(this.collider.getY() + (this.velocidadActual * deltaTime));
	}

	public void setMovArriba(boolean b) {
		this.movArriba = b;
	}

	public void setMovAbajo(boolean b) {
		this.movAbajo = b;
	}

	@Override
	public Shape getCollider() {
		return this.collider;
	}

}
