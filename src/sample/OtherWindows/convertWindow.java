package sample.OtherWindows;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class convertWindow {
    Stage window;
    public void show() throws IOException {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        Parent root = FXMLLoader.load(getClass().getResource("convertFXML.fxml"));
        convertController.getMainWind(this);
        window.setTitle("Settings for convert");
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    public void close() {
        window.close();
    }
}
