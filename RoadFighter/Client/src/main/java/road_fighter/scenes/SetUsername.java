package road_fighter.scenes;

import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import road_Figther.utils.GameObjectBuilder;
import road_fighter.Client;
import road_fighter.Main;
import road_fighter.utils.SetUsernameMenu;

public class SetUsername extends SceneHandler{
	private Group root;
	private SetUsernameMenu menu;

	public SetUsername(Main main) {
		super(main);
	}

	@Override
	protected void prepareScene() {
		root = new Group();
		this.scene = new Scene(root, Config.anchoBase, Config.altoBase, Color.BLACK);
	}

	@Override
	protected void defineEventHandlers() {
		SceneHandler me = this;

		keyEventHandlerPressed = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case Q:
				case X:
				case ESCAPE:
					main.changeScene(me, Escenas.MENU_OPTIONS);
					break;

				case ENTER:
				case Z:
					root.requestFocus();
					if (main.client == null) {
						try {
							main.client = new Client("localhost", 30000, menu.getUsername(), me);
							main.client.listen();
						} catch (IOException io) {
							io.printStackTrace();
						}
					}
					main.changeScene(me, Escenas.LOBBY);
					break;

				default:
					break;
				}
			}
		};
	}

	@Override
	public void load(boolean start) {
		GameObjectBuilder.getInstance().setRootNode(root);
		menu = new SetUsernameMenu();
		GameObjectBuilder.getInstance().add(menu);

		if (start) {
			addTimeEventsAnimationTimer();
			addInputEvents();
		}
	}

	@Override
	public void unload() {
		root.getChildren().clear();
		super.unload();
	}
}
