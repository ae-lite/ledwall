package io.aelite.ledwall.fxplugin;

import io.aelite.ledwall.core.BufferedLedWall;
import io.aelite.ledwall.core.Color;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class FxLedWall extends BufferedLedWall implements Initializable {

    @FXML
    private GridPane gridPane;
    private Rectangle[][] rectangles;

    public FxLedWall(int width, int height) {
        super(width, height);

        this.rectangles = new Rectangle[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.rectangles[x][y] = new Rectangle(25, 25);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int x = 0; x < super.getWidth(); x++) {
            this.gridPane.addColumn(x, this.rectangles[x]);
        }
    }

    @Override
    public void show() {
        for (int x = 0; x < super.getWidth(); x++) {
            for (int y = 0; y < super.getHeight(); y++) {
                int argb = super.get(x, y);
                this.rectangles[x][y].setFill(javafx.scene.paint.Color.rgb(Color.getRed(argb), Color.getGreen(argb), Color.getBlue(argb)));
            }
        }
    }

}
