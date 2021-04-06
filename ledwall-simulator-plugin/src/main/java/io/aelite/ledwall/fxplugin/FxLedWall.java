package io.aelite.ledwall.fxplugin;

import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.Color;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class FxLedWall implements Initializable {

    @FXML
    private GridPane gridPane;

    private Rectangle[][] rectangles;

    private int width;

    private int height;

    public FxLedWall(int width, int height) {
        this.width = width;
        this.height = height;

        this.rectangles = new Rectangle[this.width][this.height];
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                this.rectangles[x][y] = new Rectangle(25, 25);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int x = 0; x < this.width; x++) {
            this.gridPane.addColumn(x, this.rectangles[x]);
        }
    }

    public void updateFrame(Canvas canvas) {
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                int argb = canvas.get(x, y);
                this.rectangles[x][y].setFill(javafx.scene.paint.Color.rgb(Color.getRed(argb), Color.getGreen(argb), Color.getBlue(argb)));
            }
        }
    }

}
