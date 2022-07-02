package road_fighter.utils;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import roadFighter_interfaces.Renderable;
import road_Fighter.Config;
import road_Fighter.objects.Entidad;
import road_Figther.utils.GameObject;
import road_Figther.utils.GameObjectBuilder;

public class Menu extends GameObject implements Renderable {
	protected static final double X = Config.anchoBase * 0.25;
	protected static final double Y = Config.altoBase / 2;
	protected static final double FONT_SIZE = Config.altoBase / 30;

	protected Font font;
	protected Text info;

	protected Menu() {
		super(Entidad.TEXT, 0, 0);
		font = Font.loadFont(ClassLoader.getSystemResource("font/monogram.ttf").toString(), FONT_SIZE);
	}

	protected Text info(String msg, double y_offset) {
		Text t = new Text(msg);
		Font auxFont = Font.loadFont(ClassLoader.getSystemResource("font/monogram.ttf").toString(), FONT_SIZE / 2);

		t.setFont(auxFont);
		t.setFill(Color.color(0.4, 0.59, 0.93, 0.75));
		t.setLayoutX(FONT_SIZE / 4);
		t.setLayoutY(Config.altoBase - FONT_SIZE - 200 / y_offset);

		return t;
	}

	protected Text info2(String msg, double y_offset) {
		Text t = new Text(msg);
		Font auxFont = Font.loadFont(ClassLoader.getSystemResource("font/monogram.ttf").toString(), FONT_SIZE / 2);

		t.setFont(auxFont);
		t.setFill(Color.color(0.4, 0.59, 0.93, 0.75));
		t.setLayoutX(FONT_SIZE * 15);
		t.setLayoutY(Config.altoBase - FONT_SIZE - 50 / y_offset);

		return t;
	}

	public void update(double delta) {
		// TODO Auto-generated method stub

	}

	public void remover() {
		GameObjectBuilder.getInstance().remove(this);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}