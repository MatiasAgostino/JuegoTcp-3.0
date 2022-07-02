package road_fighter.scenes;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import road_Figther.utils.GameObjectBuilder;
import road_fighter.Main;
import road_fighter.utils.CreateSalaMenu;

public class CreateSala extends SceneHandler {
	private Group root;
	private CreateSalaMenu menu;

	public CreateSala(Main main) {
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

				case Q:
				case ESCAPE:
					main.changeScene(me, Escenas.LOBBY);
					break;

				case RIGHT:
					menu.moveRight();
					break;

				case LEFT:
					menu.moveLeft();
					break;

				case Z:
					if (menu.inConfirmar()) {
						main.client.enviar(menu.crearSala());
						main.changeScene(me, Escenas.LOBBY);
					}
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
		menu = new CreateSalaMenu();

		menu.inputFocus();

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
