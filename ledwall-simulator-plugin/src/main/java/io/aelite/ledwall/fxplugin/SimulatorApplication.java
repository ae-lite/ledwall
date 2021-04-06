package io.aelite.ledwall.fxplugin;

import io.aelite.ledwall.core.LedWallApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimulatorApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FxLedWall.fxml"));

        DispatcherLedWall dispatcherLedWall = LedWallApplication.INSTANCE.getLedWall();
        LedWall ledWall = new FxLedWall(dispatcherLedWall.getWidth(), dispatcherLedWall.getHeight());
        dispatcherLedWall.observe(ledWall);

        fxmlLoader.setController(ledWall);
        Parent root = fxmlLoader.load();

        stage.setScene(new Scene(root));
        stage.setTitle("LedWallFxPlugin");
        stage.setOnCloseRequest((e) -> {
            LedWallApplication.INSTANCE.stop();
        });
        stage.show();
    }

}
