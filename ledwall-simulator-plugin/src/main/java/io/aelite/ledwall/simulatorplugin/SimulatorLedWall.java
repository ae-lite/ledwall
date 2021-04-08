package io.aelite.ledwall.simulatorplugin;

import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.util.Color;
import io.aelite.ledwall.core.LedWallApplication;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class SimulatorLedWall implements Initializable {

    private final LedWallApplication application;
    private final int width;
    private final int height;
    private final Canvas frame;

    @FXML private GridPane gridPane;
    private Rectangle[][] rectangles;

    public SimulatorLedWall(LedWallApplication application){
        this.application = application;
        this.width = application.properties().getInt("io.aelite.ledwall.core.width");
        this.height = application.properties().getInt("io.aelite.ledwall.core.height");;
        this.frame = new Canvas(width, height);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.rectangles = new Rectangle[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.rectangles[x][y] = new Rectangle(25, 25);
            }
            this.gridPane.addColumn(x, this.rectangles[x]);
        }

        application.onFrameUpdate(this::updateFrame);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                SimulatorLedWall.this.drawFrame(frame);
            }
        };
        animationTimer.start();
    }

    private synchronized void updateFrame(Canvas canvas){
        this.frame.copyFrom(canvas);
    }

    private synchronized void drawFrame(Canvas canvas) {
        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                int argb = canvas.getPixel(x, y);
                this.rectangles[x][y].setFill(javafx.scene.paint.Color.rgb(
                        Color.getRed(argb), Color.getGreen(argb), Color.getBlue(argb)
                ));
            }
        }
    }

}
