package io.aelite.ledwall.fxplugin;

import io.aelite.ledwall.core.Color;
import io.aelite.ledwall.core.DispatcherLedWall;
import io.aelite.ledwall.core.LedWall;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
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

    public FxLedWall(DispatcherLedWall dispatcherLedWall) {
        super(dispatcherLedWall.getWidth(), dispatcherLedWall.getHeight());

        this.rectangles = new Rectangle[super.getWidth()][super.getHeight()];
        this.canvas = new Color[super.getWidth()][super.getHeight()];
        this.clear();
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
    public void set(int x, int y, Color color) {
        this.canvas[x][y] = color;
    }

    @Override
    public io.aelite.ledwall.core.Color get(int x, int y) {
        return this.canvas[x][y];
    }

    @Override
    public void fill(io.aelite.ledwall.core.Color color) {
        for (int x = 0; x < super.getWidth(); x++) {
            for (int y = 0; y < super.getHeight(); y++) {
                this.set(x, y, color);
            }
        }
    }

    @Override
    public void clear() {
        this.fill(new Color(0, 0, 0));
    }

    @Override
    public void show() {
        for (int x = 0; x < super.getWidth(); x++) {
            for (int y = 0; y < super.getHeight(); y++) {
                Color color = this.canvas[x][y];
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                this.rectangles[x][y].setFill(javafx.scene.paint.Color.color((double) red / 255, (double) green / 255, (double) blue / 255));
            }
        }
    }

}
