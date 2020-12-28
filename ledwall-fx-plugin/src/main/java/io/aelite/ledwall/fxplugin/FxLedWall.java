package io.aelite.ledwall.fxplugin;

import io.aelite.ledwall.core.BufferedLedWall;
import io.aelite.ledwall.core.Color;
import io.aelite.ledwall.core.DispatcherLedWall;
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
    private int ledSize;

    private DispatcherLedWall dispatcherLedWall;

    public FxLedWall(DispatcherLedWall dispatcherLedWall) {
        super(dispatcherLedWall.getWidth(), dispatcherLedWall.getHeight());
        this.rectangles = new Rectangle[super.getWidth()][super.getHeight()];
        this.ledSize = 25;
        this.dispatcherLedWall = dispatcherLedWall;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (int x = 0; x < super.getWidth(); x++) {
            for (int y = 0; y < super.getHeight(); y++) {
                this.rectangles[x][y] = new Rectangle(this.ledSize, this.ledSize);
            }
            this.gridPane.addColumn(x, this.rectangles[x]);
        }

        this.dispatcherLedWall.observe(this);
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
