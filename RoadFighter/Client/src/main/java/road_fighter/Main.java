package road_fighter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import road_fighter.scenes.CreateSala;
import road_fighter.scenes.EndSceneHandler;
import road_fighter.scenes.Escenas;
import road_fighter.scenes.GameSceneHandler;
import road_fighter.scenes.Lobby;
import road_fighter.scenes.MenuIntro;
import road_fighter.scenes.SalaActual;
import road_fighter.scenes.SceneHandler;
import road_fighter.scenes.SetUsername;
import road_fighter.scenes.SinglePlayerScene;

public class Main extends Application {
	private Stage stage;
	public Client client;

	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		changeScene(null, Escenas.MENU_INTRO);
		stage.getIcons().add(new Image("file:src/main/resources/ico/logo.png"));
		stage.setTitle("Road Fighter");
		stage.setResizable(false);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

	public void exit() {
		if (client != null) {
			client.close();
		}
		System.exit(0);
	}

	public void changeScene(SceneHandler previousScene, Escenas nextScene) {
		if (previousScene != null) {
			previousScene.unload();
		}

		SceneHandler newScene = null;

		switch (nextScene) {
		case MENU_INTRO:
			newScene = new MenuIntro(this);
			break;

		case SINGLE_PLAYER:
			newScene = new SinglePlayerScene(this);
			break;

		case SET_USERNAME:
			newScene = new SetUsername(this);
			break;

		case LOBBY:
			newScene = new Lobby(this);
			break;

		case CREATE_SALA:
			newScene = new CreateSala(this);
			break;

		case SALA:
			newScene = new SalaActual(this);
			break;

		case GAME:
			newScene = new GameSceneHandler(this);
			break;

		case END:
			EndSceneHandler end = new EndSceneHandler(this);

			end.load(SinglePlayerScene.score.getScore());

			newScene = end;
			break;

		default:
			break;

		}

		if (newScene != null) {
			Scene scene = newScene.getScene();

			scene.getStylesheets().add(ClassLoader.getSystemResource("stylesheet.css").toString());
			this.stage.setScene(scene);
			newScene.load(true);
		}
	}
}