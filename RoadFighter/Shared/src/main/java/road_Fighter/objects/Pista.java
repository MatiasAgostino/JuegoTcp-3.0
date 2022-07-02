package road_Fighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import roadFighter_interfaces.Renderable;
import roadFighter_interfaces.Updatable;
import road_Fighter.Config;
import road_Figther.utils.GameObject;

public class Pista extends GameObject implements Updatable, Renderable {
	private final int WIDTH = 850;
	private final int HEIGHT = 1500;
	
	private boolean movArriba;
	private boolean movAbajo;
	private double velocidadActual;

	private VBox render;

	public Pista() {
		super(Entidad.PISTA,  0, 0);

		Image pistaImg = new Image("file:src/main/resources/img/road.png");

		ImagePattern pistaPattern = new ImagePattern(pistaImg, 210, 200, 220, 200, false);
		Rectangle pista = new Rectangle(this.WIDTH, this.HEIGHT);

		pista.setTranslateX(130);
		pista.setTranslateY(-200);
		pista.setFill(pistaPattern);

		this.render = new VBox(pista);

		this.render.setLayoutY(-200);
	}

	public void setMovArriba(boolean b) {
		this.movArriba = b;
	}

	public void setMovAbajo(boolean b) {
		this.movAbajo = b;
	}

	@Override
	public Node getRender() {
		return this.render;
	}

	@Override
	public void update(double deltaTime) {
		if (this.movArriba) {
			if (this.velocidadActual + (Config.aceleracionBasePista * deltaTime) > Config.velocidadBaseDinamica) {
				this.velocidadActual = Config.velocidadBaseDinamica;
			} else {
				this.velocidadActual += Config.aceleracionBasePista * deltaTime;
			}
		} else if(!this.movArriba || this.movAbajo){
			if (this.velocidadActual - (Config.aceleracionBasePista * deltaTime) < 0) {
				this.velocidadActual = 0;
			} else {
				this.velocidadActual -= Config.aceleracionBasePista * deltaTime;
			}
			if(this.movAbajo)
				this.velocidadActual *= 0.95;
		}

		this.render.setLayoutY(this.render.getLayoutY() + (this.velocidadActual * deltaTime));

		if (this.render.getLayoutY() >= 100) {
			this.render.setLayoutY(-100);
		}
	}

	@Override
	public void destroy() {
	}
}