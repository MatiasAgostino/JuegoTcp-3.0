package road_Fighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import roadFighter_interfaces.Collidator;
import roadFighter_interfaces.Collideable;
import roadFighter_interfaces.Renderable;
import roadFighter_interfaces.Updatable;
import road_Fighter.Config;
import road_Figther.utils.GameObject;
import road_Figther.utils.GameObjectBuilder;

public class Obstaculo extends GameObject implements Updatable, Renderable, Collidator {
	private int DESPLAZAMIENTO = 3;
	private final int HEIGHT = 110;
	private final int WIDTH = 110;
	private int toleranciaPantalla = 1080;
	private Image obstaculo;
	private ImageView render;
	private Rectangle collider;
	private final double colliderTolerance = 0.75;
	private final int colliderWidth = (int) (WIDTH * colliderTolerance);
	private final int colliderHeight = (int) (HEIGHT * colliderTolerance);

	public Obstaculo(double x, double y) {
		super(Entidad.OBSTACULO, x, y);
		this.estaVivo = true;
		this.obstaculo = new Image("file:src/main/resources/img/manchaAceite.png", WIDTH, HEIGHT, false, false);
		this.render = new ImageView(obstaculo);
		this.render.relocate(-this.HEIGHT / 2, -this.WIDTH);
		this.render.setX(this.x);
		this.render.setY(this.y);
		this.collider = new Rectangle(this.x - colliderWidth / 2, this.y - colliderHeight / 2, colliderWidth,
				colliderHeight);
	}

	public void setX(double x) {
		this.x = x;
		this.render.setX(this.x);
		this.collider.setX(this.x - colliderWidth / 2);
	}

	public void setY(double y) {
		this.y = y;
		this.render.setY(this.y);
		this.collider.setY(this.y - colliderHeight / 2);
	}

	public int getDesplazamiento() {
		return this.DESPLAZAMIENTO;
	}

	private boolean isFueraDePantalla() {
		return this.y + this.HEIGHT > this.toleranciaPantalla;
	}

	@Override
	public Node getRender() {
		return this.render;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void update(double deltaTime) {
		this.setX(x);
		this.setY(y + Config.velocidadObstaculos * deltaTime);
		if (this.isFueraDePantalla()) {
			GameObjectBuilder.getInstance().remove(this);
		}
	}

	@Override
	public Shape getCollider() {
		return this.collider;
	}

	@Override
	public void collide(Collideable collideable) {
		if (collideable.getClass() == AutoJugador.class) {
			GameObjectBuilder.getInstance().remove(this);
		}
	}
}