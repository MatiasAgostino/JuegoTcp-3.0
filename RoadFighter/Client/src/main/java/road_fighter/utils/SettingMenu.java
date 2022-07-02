package road_fighter.utils;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import roadFighter_interfaces.Renderable;
import road_Fighter.Config;

public class SettingMenu extends Menu implements Renderable {
	private Text[] texts;
	private int focus = 0;

	protected VBox vbox;
	protected Pane pane;

	public SettingMenu() {
		vbox = new VBox();
		texts = new Text[3];

		lobbyConnect();

		pane = new Pane(vbox, info("Presiona Start", 1));
	}

	@Override
	public void update(double delta) {
		texts[0].setFill(Color.CORAL);
	}

	public void lobbyConnect() {
		Text lobby = new Text("MULTIJUGADOR");
		lobby.setTextAlignment(TextAlignment.CENTER);
		lobby.setFont(font);
		lobby.setFill(Color.WHITE);

		lobby.setX(X);
		lobby.setY(Y);

		VBox renderTitle = new VBox(lobby);

		renderTitle.setAlignment(Pos.TOP_CENTER);
		renderTitle.setTranslateY(Y);
		renderTitle.setPrefWidth(Config.anchoBase);

		vbox.getChildren().add(renderTitle);
	}

	public void moveUp() {
		texts[focus].setFill(Color.WHITE);
		focus = (focus == 0) ? texts.length - 1 : focus - 1;
	}

	public void moveDown() {
		texts[focus].setFill(Color.WHITE);
		focus = (focus == texts.length - 1) ? 0 : focus + 1;
	}

	@Override
	public Node getRender() {
		return pane;
	}

	public int getFocus() {
		return focus;
	}
}