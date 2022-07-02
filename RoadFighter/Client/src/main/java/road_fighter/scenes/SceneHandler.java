package road_fighter.scenes;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import roadFighter_interfaces.Updatable;
import road_Figther.utils.GameObjectBuilder;
import road_fighter.Main;

public abstract class SceneHandler {
	protected final long NANOS_EN_SEGUNDOS = 1_000_000_000;
	protected final double NANOS_EN_SEGUNDOS_D = 1_000_000_000.0;

	protected int frames = 0;
	protected int ultimoFps = 0;
	protected AtomicInteger fps = new AtomicInteger(0);

	protected AnimationTimer gameTimer;
	protected long previousNanoFrame;
	protected long previousNanoSecond;
	protected RoadFighter juego;
	protected boolean estado;

	protected Main main;
	protected Scene scene;

	protected EventHandler<KeyEvent> keyEventHandlerPressed;
	protected EventHandler<KeyEvent> keyEventHandlerReleased;

	public SceneHandler(RoadFighter juego) {
		this.juego = juego;
		prepareScene();
		defineEventHandlers();
	}

	public SceneHandler(Main main) {
		this.main = main;
		prepareScene();
		defineEventHandlers();
	}

	public void oneSecondUpdate(double delta) {
		fps.set(frames - ultimoFps);
		ultimoFps = frames;
	}

	public Scene getScene() {
		return scene;
	}

	public void update(double delta) {
		frames++;

		List<Updatable> updatables = GameObjectBuilder.getInstance().getUpdatables();
		for (Updatable updatable : updatables) {
			updatable.update(delta);
		}
	}

	protected void addTimeEventsAnimationTimer() {
		gameTimer = new AnimationTimer() {
			@Override
			public void handle(long currentNano) {
				update((currentNano - previousNanoFrame) / NANOS_EN_SEGUNDOS_D);
				previousNanoFrame = currentNano;

				if (currentNano - previousNanoSecond > NANOS_EN_SEGUNDOS) {
					oneSecondUpdate((currentNano - previousNanoSecond) / NANOS_EN_SEGUNDOS_D);
					previousNanoSecond = currentNano;
				}

			}
		};

		previousNanoSecond = System.nanoTime();
		previousNanoFrame = System.nanoTime();
		gameTimer.start();
	}

	protected void addInputEvents() {
		if (keyEventHandlerPressed != null) {
			scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEventHandlerPressed);
		}

		if (keyEventHandlerReleased != null) {
			scene.addEventHandler(KeyEvent.KEY_RELEASED, keyEventHandlerReleased);
		}
	}

	protected void addInputEventsMenu() {
		if (keyEventHandlerPressed != null) {
			scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEventHandlerPressed);
		}
	}

	protected void removeInputEvents() {
		if (keyEventHandlerPressed != null) {
			scene.removeEventHandler(KeyEvent.KEY_PRESSED, keyEventHandlerPressed);
		}
	}

	protected abstract void prepareScene();

	protected abstract void defineEventHandlers();

	public abstract void load(boolean start);

	public void unload() {
		GameObjectBuilder.getInstance().removeAll();
		gameTimer.stop();
		removeInputEvents();
	}

	public void changeScene(Escenas nextScene) {
		main.changeScene(this, nextScene);
	}
}