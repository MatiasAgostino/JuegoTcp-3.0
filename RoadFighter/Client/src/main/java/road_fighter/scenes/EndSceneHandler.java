package road_fighter.scenes;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import road_Fighter.objects.Background;
import road_fighter_objects.menu.TextoFin;
import road_Figther.utils.GameObjectBuilder;
import road_fighter.Main;

public class EndSceneHandler extends SceneHandler {
	private Background background;
	private TextoFin textoFin;

	private Group rootGroup;

	public EndSceneHandler(Main juego) {
		super(juego);
		this.estado = true;
	}

	protected void prepareScene() {
		rootGroup = new Group();
		scene = new Scene(rootGroup, Config.anchoBase, Config.altoBase, Color.BLACK);
	}

	protected void defineEventHandlers() {

		keyEventHandlerPressed = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case ENTER:
					changeScene(Escenas.MENU_INTRO);
					break;
				case ESCAPE:
					System.exit(0);
					break;
				default:
					break;
				}
			}
		};
	}

	public void load(int score) {
		boolean fullStart = true;
		Group baseGroup = new Group();
		rootGroup.getChildren().add(baseGroup);

		background = new Background("file:src/main/resources/img/roadFighterEnd.png");

		textoFin = new TextoFin(score);

		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(baseGroup);
		gameOB.add(this.background, this.textoFin);

		if (fullStart) {
			addTimeEventsAnimationTimer();
			addInputEventsMenu();
		}
	}

	public void unload() {
		rootGroup.getChildren().remove(0);
		super.unload();
		this.estado = false;
	}

	@Override
	public void load(boolean start) {
		// TODO Auto-generated method stub

	}
}
