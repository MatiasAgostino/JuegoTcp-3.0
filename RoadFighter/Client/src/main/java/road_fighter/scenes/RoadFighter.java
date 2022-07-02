package road_fighter.scenes;
/*
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import road_Fighter.objects.Score;*/

public class RoadFighter{
//	private Stage stage;
//	private GameSceneHandler gameSceneHandler;
//	private MenuHandlerScene menuSceneHandler;
//	private EndSceneHandler endSceneHandler;
//
//	@Override
//	public void start(Stage stage) throws Exception {
//		this.stage = stage;
//
//		menuSceneHandler = new MenuHandlerScene(this);
//		Scene scene = menuSceneHandler.getScene();
//
//		stage.setScene(scene);
//		menuSceneHandler.load();
//		this.stage.setTitle("Road fighter");
//		this.stage.getIcons().add(new Image("file:src/main/resources/img/car-player.png"));
//		this.stage.show();
//	}
//
//	public void startGame() {
//		if (menuSceneHandler != null && menuSceneHandler.estado)
//			menuSceneHandler.unload();
//
//		if (endSceneHandler != null && endSceneHandler.estado)
//			endSceneHandler.unload();
//
//		gameSceneHandler = new GameSceneHandler(this);
//		Scene scene = gameSceneHandler.getScene();
//		stage.setScene(scene);
//		gameSceneHandler.load(true);
//	}
//
//	public void startMenu() {
//		gameSceneHandler.unload();
//		menuSceneHandler = new MenuHandlerScene(this);
//		Scene scene = menuSceneHandler.getScene();
//		stage.setScene(scene);
//		menuSceneHandler.load();
//	}
//
//	public void endGameScreen() {
//		Score score = gameSceneHandler.getScore();
//		gameSceneHandler.unload();
//		endSceneHandler = new EndSceneHandler(this);
//		Scene scene = endSceneHandler.getScene();
//		stage.setScene(scene);
//		endSceneHandler.load(score.getScore());
//	}
//
//	public static void main(String[] args) {
//		launch();
//	}
}