package road_fighter.scenes;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import road_Figther.utils.GameObjectBuilder;
import road_fighter.Main;
import road_fighter.utils.SettingMenu;

public class MenuOptions extends SceneHandler {
	private Group root;
	private SettingMenu menu;

	public MenuOptions(Main main) {
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
				case UP:
					menu.moveUp();
					break;

				case DOWN:
					menu.moveDown();
					break;

				case Z:
				case ENTER:
					if (menu.getFocus() == 0) {
						if (main.client == null) {
							main.changeScene(me, Escenas.SET_USERNAME);
						} else {
							main.changeScene(me, Escenas.LOBBY);
						}
					}
					break;

				case Q:
				case X:
				case ESCAPE:
					main.changeScene(me, Escenas.MENU_INTRO);
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

		Image fondo = new Image("file:src/main/resources/img/logo.png", Config.anchoBase, Config.altoBase / 2, false,
				false);
		ImageView imageView = new ImageView(fondo);
		root.getChildren().add(imageView);

		menu = new SettingMenu();
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
