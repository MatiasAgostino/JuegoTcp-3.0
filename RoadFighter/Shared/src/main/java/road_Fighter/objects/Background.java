package road_Fighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import roadFighter_interfaces.Renderable;
import road_Figther.utils.GameObject;

public class Background extends GameObject implements Renderable {
	private final int HEIGHT = 1080;
	private final int WIDTH = 1080;

	private ImageView render;

	public Background(String path) {
		super(Entidad.ESCENARIO, 0, 0);
		Image backgroundImage = new Image(path, this.HEIGHT, this.WIDTH, false, false);
		ImageView bg = new ImageView(backgroundImage);
		render = bg;
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void destroy() {}
}