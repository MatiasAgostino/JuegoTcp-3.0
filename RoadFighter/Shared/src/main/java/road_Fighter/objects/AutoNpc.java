package road_Fighter.objects;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import roadFighter_interfaces.Collidator;
import roadFighter_interfaces.Collideable;
import roadFighter_interfaces.Renderable;
import roadFighter_interfaces.Updatable;
import road_Fighter.Config;
import road_Figther.utils.GameObjectBuilder;
import road_Figther.utils.IndividualSpriteAnimation;

public class AutoNpc extends Auto implements Updatable, Renderable, Collidator {

	private final int HEIGHT = 50;
	private final int WIDTH = 50;

	private Image auto;
	private Image explosionBase;
	private Image explosionMedia;
	private Image explosionAvanzada;
	private Image explosionFinal;

	private ImageView render;

	private IndividualSpriteAnimation explosionAnimacion;

	private Rectangle collider;
	private TranslateTransition translateTransition;
	private final double colliderTolerance = 0.75;
	private final int colliderWidth = (int) (WIDTH * colliderTolerance);
	private final int colliderHeight = (int) (HEIGHT * colliderTolerance);
	private int toleranciaPantalla = 0;

	private double direccionDeChoque;
	private boolean chocoGr;
	private boolean choco;
	private int tiempoChoque;
	private int tiempoDeExplosion;

	public AutoNpc(double x, double y) {
		super(x, y);

		this.estaVivo = true;

		this.explosionBase = new Image("file:src/main/resources/img/e1.png", WIDTH, HEIGHT, false, false);
		this.explosionMedia = new Image("file:src/main/resources/img/e2.png", WIDTH, HEIGHT, false, false);
		this.explosionAvanzada = new Image("file:src/main/resources/img/e3.png", WIDTH, HEIGHT, false, false);
		this.explosionFinal = new Image("file:src/main/resources/img/e4.png", WIDTH, HEIGHT, false, false);

		this.auto = new Image("file:src/main/resources/img/car-enemy.png", WIDTH, HEIGHT, false, false);
		this.render = new ImageView(auto);

		this.render.relocate(-this.HEIGHT / 2, -this.WIDTH);
		this.render.setX(this.x);
		this.render.setX(this.y);

		this.collider = new Rectangle(this.x - colliderWidth / 2, this.y - colliderHeight / 2, colliderWidth,
				colliderHeight);

		this.explosionAnimacion = iniciarExplosionAutoNPC();

	}

	public void setPosicion(double x, double y) {
		this.x = x;
		this.y = y;
		this.render.setX(this.x);
		this.render.setY(this.y);
		this.collider.setX(this.x);
		this.collider.setY(this.y);
	}

	public void trasladar(double x, double y) {
		x = x <= Config.limiteIzquierda ? Config.limiteIzquierda
				: (x > Config.limiteDerecha ? Config.limiteDerecha : x);

		translateTransition = new TranslateTransition(Duration.millis(1300), render);
		translateTransition.setToX(x);
		translateTransition.setToY(y);

		translateTransition.play();

		this.collider.setX(this.x + x);
		this.collider.setY(this.y - y);
	}

	private IndividualSpriteAnimation iniciarExplosionAutoNPC() {
		IndividualSpriteAnimation individualSpriteAnimation = new IndividualSpriteAnimation(
				new Image[] { explosionBase, explosionMedia, explosionAvanzada, explosionFinal }, render,
				Duration.millis(1000));
		individualSpriteAnimation.setCustomFrames(new int[] { 0, 1, 2, 1, 1 });
		individualSpriteAnimation.setCycleCount(Animation.INDEFINITE);
		return individualSpriteAnimation;
	}

	private boolean isFueraDePantalla() {
		return this.y == this.toleranciaPantalla;
	}

	private void setX(double x) {
		x = x <= Config.limiteIzquierda ? Config.limiteIzquierda
				: (x > Config.limiteDerecha ? Config.limiteDerecha : x);

		this.x = x;
		this.render.setX(x);
		this.collider.setX(this.x);
	}

	public void setY(double y) {
		this.y = y;
		this.render.setY(this.y);
		this.collider.setY(this.y - colliderHeight / 2);
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
		if (this.estaVivo) {
			if (this.choco) {
				if (this.tiempoChoque > 0) {
					this.setX(this.x + (this.direccionDeChoque * Config.velocidadNpcChoque));
					this.tiempoChoque--;
				} else {
					this.tiempoChoque = 0;
					this.choco = false;
				}
			} else {
				this.setX(this.x);
			}

			this.setY(y - Config.velocidadNpc * deltaTime);

			if (this.isFueraDePantalla()) {
				GameObjectBuilder.getInstance().remove(this);
			}
		} else {
			if (this.tiempoDeExplosion > 0) {
				this.tiempoDeExplosion--;
			} else {
				this.chocoGr = false;
				this.tiempoDeExplosion = 0;
				this.explosionAnimacion.stop();
				GameObjectBuilder.getInstance().remove(this);
			}
		}
	}

	@Override
	public Shape getCollider() {
		return this.collider;
	}

	@Override
	public void collide(Collideable collideable) {
		if (collideable.getClass() == AutoJugador.class) {
			this.choco = true;
			this.tiempoChoque = 30;

			if (this.render.getX() >= ((AutoJugador) collideable).getX())
				this.direccionDeChoque = 1;
			else
				this.direccionDeChoque = -1;

		} else if (!this.chocoGr && collideable.getClass() == GuardaRailDerecha.class) {
			this.chocoGr = true;
			this.explosionAnimacion.play();
			this.tiempoDeExplosion = 100;
			this.estaVivo = false;
		} else if (!this.chocoGr && collideable.getClass() == GuardaRailIzquierda.class) {
			this.chocoGr = true;
			this.explosionAnimacion.play();
			this.tiempoDeExplosion = 100;
			this.estaVivo = false;
		} else if (collideable.getClass() == Obstaculo.class) {
			this.choco = true;
			this.tiempoChoque = 30;

			if (this.render.getX() >= ((Obstaculo) collideable).getX())
				this.direccionDeChoque = 1;
			else
				this.direccionDeChoque = -1;
		}
	}
}