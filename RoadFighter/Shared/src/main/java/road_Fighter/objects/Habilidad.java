package road_Fighter.objects;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import roadFighter_interfaces.Renderable;
import road_Figther.utils.GameObject;

public class Habilidad extends GameObject implements Renderable{
	private final int Y = 80;
	private int habilidades = 3;

	private VBox render;
	private Animation zoomAnimation;
	private Text habilidadText;
	private Text habilidadName;

	public Habilidad() {
		super(Entidad.HABILIDAD, 0.0, 0.0);
		this.habilidadText = new Text("" + habilidades);
		this.habilidadName = new Text("HABILIDADES:");
		this.render = new VBox(habilidadName, habilidadText);
		this.render.setSpacing(5);
		this.render.setAlignment(Pos.TOP_LEFT);
		this.render.setTranslateY(Y);
		this.render.setPrefWidth(1000);
		this.habilidadText.setTextAlignment(TextAlignment.CENTER);
		this.habilidadText.setFont(Font.font("MONOSPACE", 15));
		this.habilidadText.setFill(Color.BLACK);
		this.habilidadName.setFont(Font.font("MONOSPACE", 15));
	}

	@Override
	public Node getRender() {
		return this.render;
	}

	public void decrease() {
		this.habilidades -=1;
		this.habilidadText.setText("" + habilidades);
		this.zoomAnimation = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(habilidadText.scaleXProperty(), 1),
						new KeyValue(habilidadText.scaleYProperty(), 1)),
				new KeyFrame(Duration.seconds(0.15),
						new KeyValue(habilidadText.scaleXProperty(), Math.min(1.05 + habilidades / 200.0, 2)),
						new KeyValue(habilidadText.scaleYProperty(), Math.min(1.05 + habilidades / 200.0, 2))),
				new KeyFrame(Duration.seconds(0.4), new KeyValue(habilidadText.scaleXProperty(), 1),
						new KeyValue(habilidadText.scaleYProperty(), 1)));
		this.zoomAnimation.play();
	}

	public int getHabilidad() {
		return habilidades;
	}

	@Override
	public void destroy() {
	}

}
