package sample.OtherWindows;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class convertController implements Initializable{

    @FXML
    Button OKButton;
    @FXML
    Button CancelButton;
    @FXML
    ChoiceBox ChoiseSort;
    @FXML
    RadioButton Radio1;
    @FXML
    RadioButton Radio2;
    @FXML
    RadioButton Radio3;
    @FXML
    Text Text1;
    @FXML
    Text Text2;
    @FXML
    Text Text3;

    private static convertWindow cw;

    public static void getMainWind(convertWindow convertWindow){
        cw = convertWindow;
    }

    public void action_cancel(){
        cw.close();
    }

    public void action_OK(){
        cw.close();
    }

    public void action_radio1() {
        if (Radio1.isSelected()) {
            Text1.setFill(Color.BLACK);
            ChoiseSort.setDisable(false);
        }
        else {
            Text1.setFill(Color.DARKGRAY);
            ChoiseSort.setDisable(true);
        }
    }

    public void action_radio2() {
        if (Radio2.isSelected()) {
            Text2.setFill(Color.BLACK);
        }
        else {
            Text2.setFill(Color.DARKGRAY);
        }
    }

    public void action_radio3() {
        if (Radio3.isSelected()) {
            Text3.setFill(Color.BLACK);
        }
        else {
            Text3.setFill(Color.DARKGRAY);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Text1.setFill(Color.DARKGRAY);
        Text2.setFill(Color.DARKGRAY);
        Text3.setFill(Color.DARKGRAY);
    }
}
