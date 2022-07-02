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
import road_Fighter.Config;
import road_Figther.utils.GameObject;

public class Score extends GameObject implements Renderable {
	private final int Y = 80;
	private int score = 0;
	private Text scoreText;
	private Text scoreName;
	private VBox render;
	private Animation zoomAnimation;

	public Score() {
		super(Entidad.SCORE, 0.0, 0.0);
		this.scoreText = new Text("" + score);
		this.scoreName = new Text("SCORE");
		this.render = new VBox(scoreName, scoreText);
		this.render.setSpacing(5);
		this.render.setAlignment(Pos.TOP_RIGHT);
		this.render.setTranslateY(Y);
		this.render.setPrefWidth(1040);
		this.scoreText.setTextAlignment(TextAlignment.CENTER);
		this.scoreText.setFont(Font.font("MONOSPACE", 15));
		this.scoreText.setFill(Color.BLACK);
		this.scoreName.setFont(Font.font("MONOSPACE", 15));
	}

	@Override
	public Node getRender() {
		return this.render;
	}

	public void increase(double velocidadActual) {
		this.score += Config.velocidadBaseDinamica / 1000;
		this.scoreText.setText("" + score);
		this.zoomAnimation = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(scoreText.scaleXProperty(), 1),
						new KeyValue(scoreText.scaleYProperty(), 1)),
				new KeyFrame(Duration.seconds(0.15),
						new KeyValue(scoreText.scaleXProperty(), Math.min(1.05 + score / 200.0, 2)),
						new KeyValue(scoreText.scaleYProperty(), Math.min(1.05 + score / 200.0, 2))),
				new KeyFrame(Duration.seconds(0.4), new KeyValue(scoreText.scaleXProperty(), 1),
						new KeyValue(scoreText.scaleYProperty(), 1)));
		this.zoomAnimation.play();
	}

	public int getScore() {
		return score;
	}

	@Override
	public void destroy() {
	}
}