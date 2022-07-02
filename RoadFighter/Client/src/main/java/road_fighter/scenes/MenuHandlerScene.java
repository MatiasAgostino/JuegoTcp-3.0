package road_fighter.scenes;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import road_Fighter.objects.Background;
import road_Figther.utils.GameObjectBuilder;
import road_fighter.Main;

public class MenuHandlerScene extends SceneHandler {
	private Background background;

	private AudioClip audioInicial;

	private Group rootGroup;

	public MenuHandlerScene(Main juego) {
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
					audioInicial.stop();
					changeScene(Escenas.GAME);
					break;
				case Q:
				case ESCAPE:
					System.exit(0);
					break;
				default:
					break;
				}
			}
		};
	}

	public void load() {
		boolean fullStart = true;
		Group baseGroup = new Group();
		rootGroup.getChildren().add(baseGroup);
		this.audioInicial = new AudioClip("file:src/main/resources/sounds/intro.wav");

		audioInicial.play();
		background = new Background("file:src/main/resources/img/roadFighterIntro.png");

		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(baseGroup);
		gameOB.add(this.background);

		if (fullStart) {
			addTimeEventsAnimationTimer();
			addInputEventsMenu();
		}
	}

	public void unload() {
		rootGroup.getChildren().remove(0);
		this.estado = false;
		super.unload();
	}

	@Override
	public void load(boolean start) {
		// TODO Auto-generated method stub

	}
}