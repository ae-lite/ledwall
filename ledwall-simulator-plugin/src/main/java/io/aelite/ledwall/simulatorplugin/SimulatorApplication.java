package io.aelite.ledwall.simulatorplugin;

import io.aelite.ledwall.core.LedWallApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimulatorApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SimulatorLedWall.fxml"));
        Parent root = fxmlLoader.load();

        stage.setScene(new Scene(root));
        stage.setTitle("LedWallSimulatorPlugin");
        stage.setOnCloseRequest((e) -> {
            LedWallApplication.INSTANCE.stop();
        });
        stage.show();
    }

}
