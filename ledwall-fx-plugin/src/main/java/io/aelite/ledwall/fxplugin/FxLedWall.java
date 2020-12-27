package io.aelite.ledwall.fxplugin;

import io.aelite.ledwall.core.DispatcherLedWall;
import io.aelite.ledwall.core.LedWall;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class FxLedWall extends LedWall implements Initializable {

    @FXML
    private GridPane gridPane;
    private Rectangle[][] rectangles;
    private Color[][] canvas;
    private int ledSize;

    private DispatcherLedWall dispatcherLedWall;

    public FxLedWall(DispatcherLedWall ledWallDispatcher) {
        super(ledWallDispatcher.getWidth(), ledWallDispatcher.getHeight());

        this.rectangles = new Rectangle[super.getWidth()][super.getHeight()];
        this.initCanvas();
        this.ledSize = 25;
        this.dispatcherLedWall = ledWallDispatcher;
    }

    private void initCanvas(){
        this.canvas = new Color[super.getWidth()][super.getHeight()];
        for (int x = 0; x < super.getWidth(); x++) {
            for (int y = 0; y < super.getHeight(); y++) {
                this.canvas[x][y] = Color.color(0, 0, 0);
            }
        }
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
    public void setPixel(int x, int y, int red, int green, int blue) {
        this.canvas[x][y] = Color.color((double) red / 255, (double) green / 255, (double) blue / 255);
    }

    @Override
    public void setPixels(int r, int g, int b) {
        for (int x = 0; x < super.getWidth(); x++) {
            for (int y = 0; y < super.getHeight(); y++) {
                this.setPixel(x, y, r, g, b);
            }
        }
    }

    @Override
    public void clear() {
        this.setPixels(0, 0, 0);
    }

    @Override
    public void show() {
        for (int x = 0; x < super.getWidth(); x++) {
            for (int y = 0; y < super.getHeight(); y++) {
                Color color = this.canvas[x][y];
                this.rectangles[x][y].setFill(color);
            }
        }
    }

}
