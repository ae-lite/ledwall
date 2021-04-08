package io.aelite.ledwall.simulatorplugin;

import io.aelite.ledwall.core.LedWallApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimulatorApplication extends Application {

    private static LedWallApplication application;

    @Override
    public void start(Stage stage) throws Exception {
        SimulatorLedWall simulatorLedWall = new SimulatorLedWall(SimulatorApplication.application);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SimulatorLedWall.fxml"));
        loader.setController(simulatorLedWall);
        Parent simulator = loader.load();

        stage.setScene(new Scene(simulator));
        stage.setTitle("LedWallSimulatorPlugin");
        stage.setOnCloseRequest((e) -> {
            SimulatorApplication.application.stop();
        });
        stage.show();
    }

    static void setApplication(LedWallApplication application){
        SimulatorApplication.application = application;
    }

}
