package io.aelite.ledwall.fxplugin;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import io.aelite.ledwall.core.AnimationPlayer;
import io.aelite.ledwall.core.LedWallApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimulatorApplication extends Application {

    private int width;
    private int height;
    private LedWallApplication ledWallApplication;
    private AnimationPlayer animationPlayer;

    @Inject
    public SimulatorApplication(
        @Named("ledwall.core.width") int width,
        @Named("ledweall.core.height") int height,
        LedWallApplication ledWallApplication,
        AnimationPlayer animationPlayer
    ){
        this.width = width;
        this.height = height;
        this.ledWallApplication = ledWallApplication;
        this.animationPlayer = animationPlayer;
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FxLedWall.fxml"));

        FxLedWall ledWall = new FxLedWall(this.width, this.height);
        this.animationPlayer.subscribeToFrameUpdates(ledWall::updateFrame);

        fxmlLoader.setController(ledWall);
        Parent root = fxmlLoader.load();

        stage.setScene(new Scene(root));
        stage.setTitle("LedWallSimulatorPlugin");
        stage.setOnCloseRequest((e) -> {
            this.ledWallApplication.stop();
        });
        stage.show();
    }

}
