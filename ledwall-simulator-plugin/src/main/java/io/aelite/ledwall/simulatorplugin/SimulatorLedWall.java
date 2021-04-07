package io.aelite.ledwall.simulatorplugin;

import io.aelite.ledwall.core.Canvas;
import io.aelite.ledwall.core.Color;
import io.aelite.ledwall.core.LedWallApplication;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class SimulatorLedWall implements Initializable {

    @FXML private GridPane gridPane;
    private Rectangle[][] rectangles;
    private Canvas frame;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LedWallApplication.INSTANCE
                .getAnimationManager().getAnimationPlayer()
                .subscribeToFrameUpdates(this::updateFrame);

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                SimulatorLedWall.this.drawFrame(frame);
            }
        };
        animationTimer.start();
    }

    private synchronized void updateFrame(Canvas canvas){
        if(this.frame == null){
            this.frame = new Canvas(canvas);
        }
        this.frame.copyFrom(canvas);
    }

    private synchronized void drawFrame(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        if(this.rectangles == null
            || width != this.rectangles.length
            || height != this.rectangles[0].length
        ){
            this.resize(canvas.getWidth(), canvas.getHeight());
        }

        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                int argb = canvas.getPixel(x, y);
                this.rectangles[x][y].setFill(javafx.scene.paint.Color.rgb(
                        Color.getRed(argb), Color.getGreen(argb), Color.getBlue(argb)
                ));
            }
        }
    }

    private void resize(int width, int height){
        this.frame = new Canvas(width, height);
        this.rectangles = new Rectangle[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.rectangles[x][y] = new Rectangle(25, 25);
            }
            this.gridPane.addColumn(x, this.rectangles[x]);
        }
    }

}
