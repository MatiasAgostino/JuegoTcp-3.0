package road_fighter.scenes;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import road_Fighter.server.Comandos;
import road_Fighter.server.Mensaje;
import road_Figther.utils.GameObjectBuilder;
import road_fighter.Main;
import road_fighter.utils.SalaMenu;

public class SalaActual extends SceneHandler {
	private Group root;
	private SalaMenu menu;

	public SalaActual(Main main) {
		super(main);
	}

	@Override
	protected void prepareScene() {
		root = new Group();
		this.scene = new Scene(root, Config.anchoBase, Config.altoBase, Color.BLACK);
		main.client.setCurrentScene(this);
	}

	@Override
	protected void defineEventHandlers() {

		keyEventHandlerPressed = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				Mensaje msg;
				switch (e.getCode()) {
				case Q:
				case X:
				case ESCAPE:
					msg = new Mensaje(Comandos.AL_LOBBY);
					main.client.enviar(msg);
					break;

				case UP:
					menu.moveUp();
					break;

				case DOWN:
					menu.moveDown();
					break;

				case ENTER:
					msg = new Mensaje(Comandos.EMPEZAR_JUEGO); // + salaName
					main.client.enviar(msg);
					break;
				default:
					break;
				}
			}
		};
	}

	@Override
	public void update(double delta) {
		super.update(delta);

		switch (main.client.comandoPendiente) {
		case HACER_NADA:
			break;

		case UNIRSE_SALA:
			restart();
			main.client.comandoPendiente = Comandos.HACER_NADA;
			break;

		case AL_LOBBY:
			changeScene(Escenas.LOBBY);
			main.client.comandoPendiente = Comandos.HACER_NADA;
			break;

		case EMPEZAR_JUEGO:
			changeScene(Escenas.GAME);
			main.client.comandoPendiente = Comandos.HACER_NADA;
			break;

		default:
			break;
		}

	}

	public void restart() {
		GameObjectBuilder.getInstance().removeAll();
		load(false);
	}

	@Override
	public void load(boolean start) {
		GameObjectBuilder.getInstance().setRootNode(root);

		menu = new SalaMenu(main.client);
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