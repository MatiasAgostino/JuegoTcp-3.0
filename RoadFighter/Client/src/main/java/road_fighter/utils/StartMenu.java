package road_fighter.utils;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import roadFighter_interfaces.Renderable;
import road_Fighter.Config;

public class StartMenu extends Menu implements Renderable {
	private Text text;

	public StartMenu() {
		VBox box = new VBox(text);
		box.setAlignment(Pos.TOP_CENTER);
		box.setTranslateY(Y);
		box.setPrefWidth(Config.anchoBase);

		text.setTextAlignment(TextAlignment.CENTER);
		text.setFont(font);
		text.setFill(Color.WHITE);

		this.render = box;
	}
}
