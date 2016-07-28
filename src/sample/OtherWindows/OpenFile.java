package sample.OtherWindows;

import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class OpenFile {

    private static String PATH;

    public static String getPath() {
        return PATH;
    }

    public static void show(String title) throws NullPointerException{
        Stage window = new Stage();
        final DirectoryChooser directoryChooser = new DirectoryChooser();
        window.initModality(Modality.APPLICATION_MODAL);
        directoryChooser.setTitle(title);
        File directory = directoryChooser.showDialog(window);
        PATH = directory.getAbsolutePath();
    }

}
