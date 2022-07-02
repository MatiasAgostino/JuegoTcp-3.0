package road_Fighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import roadFighter_interfaces.Collideable;
import roadFighter_interfaces.Renderable;
import roadFighter_interfaces.Updatable;
import road_Fighter.Config;
import road_Figther.utils.GameObject;

public class GuardaRailDerecha extends GameObject implements Updatable, Renderable, Collideable {
    private Rectangle collider;
    private ImageView render;
    private Image imagenRail;
    private double width = 200;
    private double heigth = 2500;
    private double velocidadActual;

    private boolean movArriba;
    private boolean movAbajo;
    
    public GuardaRailDerecha(double x, double y) {
        super(Entidad.GUARDARAIL, x, y);
        
        imagenRail = new Image("file:src/main/resources/img/right-guardrail.png", width, heigth, false, false);
        this.render = new ImageView(imagenRail);
        
        this.render.relocate(-this.width / 2, -600);
        this.render.setX(x);
        this.render.setY(y);
        
        this.collider = new Rectangle(this.x, this.y, 10, 1000);
    }
    
    @Override
    public Node getRender() {
        return render;
    }
    
    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }
    
    @Override
    public Shape getCollider() {
        // TODO Auto-generated method stub
        return collider;
    }
    public void setMovArriba(boolean b) {
        this.movArriba = b;
    }
    public void setMovAbajo(boolean b) {
        this.movAbajo = b;
    }
    @Override
    public void update(double deltaTime) {
        if (this.movArriba) {
            if (this.velocidadActual + (Config.aceleracionBasePista * deltaTime) > Config.velocidadBase) {
                this.velocidadActual = Config.velocidadBase;
            } else {
                this.velocidadActual += Config.aceleracionBasePista * deltaTime;
            }
        } else if (!this.movArriba || this.movAbajo) {
            if (this.velocidadActual - (Config.aceleracionBasePista * deltaTime) < 0) {
                this.velocidadActual = 0;
            } else {
                this.velocidadActual -= Config.aceleracionBasePista * deltaTime;
            }
            if (this.movAbajo)
                this.velocidadActual *= 0.95;
        }
        this.y += this.velocidadActual * deltaTime;
        this.render.setTranslateY(this.y % this.width);
    }
}