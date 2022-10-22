import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    public Tile(int x, int y){
        setWidth(ludoSnake.tileSize);
        setHeight((ludoSnake.tileSize));
        setFill(Color.RED);
        setStroke(Color.BLACK);
    }
}
