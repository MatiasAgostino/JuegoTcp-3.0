package road_fighter_objects.menu;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import roadFighter_interfaces.Renderable;
import road_Fighter.Config;
import road_Fighter.objects.Entidad;
import road_Figther.utils.GameObject;

public class TextoFin extends GameObject implements Renderable {
	private final int Y = Config.altoBase * 3 / 5;

	private Text text;
	private VBox render;

	public TextoFin(int score) {
		super(Entidad.TEXT, 0, 0);

		Integer s = score;
		text = new Text(s.toString());

		render = new VBox(text);
		render.setAlignment(Pos.TOP_CENTER);
		render.setTranslateY(Y);

		render.setPrefWidth(Config.anchoBase);

		Font font = Font.font("Verdana", FontWeight.NORMAL, 60);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFont(font);
		text.setFill(Color.WHITE);
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void destroy() {
	}

}