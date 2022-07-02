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

public class MenuIntro extends SceneHandler {
	private Group root;

	public MenuIntro(Main main) {
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
				case S:
					main.changeScene(me, Escenas.SINGLE_PLAYER);
					break;
				case SPACE:
				case ENTER:
				case Z:
					if (main.client == null) {
						main.changeScene(me, Escenas.SET_USERNAME);
					} else {
						main.changeScene(me, Escenas.LOBBY);
					}
					break;

				case Q:
				case X:
				case ESCAPE:
					main.exit();
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

		Image fondo = new Image("file:src/main/resources/img/start2.jpeg", 1024, 1024, false, false);
		ImageView imageView = new ImageView(fondo);
		root.getChildren().add(imageView);

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