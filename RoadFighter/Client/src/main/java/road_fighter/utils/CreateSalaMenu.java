package road_fighter.utils;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import roadFighter_interfaces.Renderable;
import road_Fighter.Config;
import road_Fighter.server.Comandos;
import road_Fighter.server.Mensaje;

public class CreateSalaMenu extends Menu implements Renderable {
	private static final double PADDING = 100;

	private TextField input;
	private Text players;
	private Text confirm;
	private int maxPlayers = Config.minJugadores;
	private Node[] options;
	private int focus = 0;

	public CreateSalaMenu() {
		Text crearSala = new Text("CREAR SALA");
		crearSala.setTextAlignment(TextAlignment.CENTER);
		crearSala.setFont(Font.loadFont(ClassLoader.getSystemResource("font/monogram.ttf").toString(), FONT_SIZE * 2));
		crearSala.setFill(Color.CORNFLOWERBLUE);
		crearSala.setTranslateX(X);
		crearSala.setTranslateY(Y / 1.1);

		setInput();
		setPlayers();
		setConfirm();

		render = new Pane(crearSala, input, players, confirm);
		options = new Node[] { input, players, confirm };
	}

	public boolean inConfirmar() {
		System.out.println(focus + " " + (options.length - 1));
		return (focus + 1) == options.length - 1;
	}

	public void inputFocus() {
		input.requestFocus();
	}

	public void moveUp() {
		if (options[focus] instanceof Text) {
			((Text) options[focus]).setFill(Color.WHITE);
		}

		focus = (focus == 0) ? options.length - 1 : focus - 1;
		options[focus].requestFocus();

		if (options[focus] instanceof Text) {
			((Text) options[focus]).setFill(Color.CORNFLOWERBLUE);
		}
	}

	public void moveDown() {
		if (options[focus] instanceof Text) {
			((Text) options[focus]).setFill(Color.WHITE);
		}
		focus = (focus == options.length - 1) ? 0 : focus + 1;
		options[focus].requestFocus();
		if (options[focus] instanceof Text) {
			((Text) options[focus]).setFill(Color.CORNFLOWERBLUE);
		}
	}

	public void moveRight() {
		switch (focus) {
		case 1:
			maxPlayers = (maxPlayers == Config.maxJugadores) ? Config.minJugadores : maxPlayers + 1;
			players.setText("Cantidad max. de Jugadores: < " + maxPlayers + " >");
			break;

		default:
			break;
		}
	}

	public void moveLeft() {
		switch (focus) {
		case 1:
			maxPlayers = (maxPlayers == Config.minJugadores) ? Config.maxJugadores : maxPlayers - 1;
			players.setText("Cantidad max. de Jugadores: < " + maxPlayers + " >");
			break;

		default:
			break;
		}
	}

	public Mensaje crearSala() {
		Mensaje msg = new Mensaje(Comandos.CREAR_SALA);
		msg.agregar(input.getText().trim());
		msg.agregar(maxPlayers);
		return msg;
	}

	private void setInput() {
		input = new TextField();
		input.setPromptText("Sala Name");
		input.setPrefSize(X * 2, FONT_SIZE);
		input.setTranslateX(Config.anchoBase / 2 - input.getPrefWidth() / 2);
		input.setTranslateY(Y - FONT_SIZE / 2);
		input.setFont(font);
		input.setFocusTraversable(false);

		input.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case ENTER:
					moveDown();
					break;

				case ESCAPE:
					render.requestFocus();
					break;

				case UP:
					moveUp();
					break;

				case DOWN:
					moveDown();
					break;

				default:
					break;
				}
			}
		});
	}

	private void setPlayers() {
		players = new Text("Cantidad max. de Jugadores: < " + maxPlayers + " >");
		players.setTextAlignment(TextAlignment.CENTER);
		players.setFont(font);
		players.setFill(Color.WHITE);
		players.setTranslateX(X / 1);
		players.setTranslateY((input.getTranslateY() + input.getPrefHeight() + PADDING) * 1.2);
	}

	private void setConfirm() {
		confirm = new Text("CONFIRMAR");
		confirm.setTextAlignment(TextAlignment.CENTER);
		confirm.setFont(font);
		confirm.setFill(Color.WHITE);
		confirm.setTranslateX(Config.anchoBase / 2 - confirm.getLayoutBounds().getWidth() / 2);
		confirm.setTranslateY(Config.altoBase / 2 - confirm.getLayoutBounds().getHeight() - 300);
	}
}
