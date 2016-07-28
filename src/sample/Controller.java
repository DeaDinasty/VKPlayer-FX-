package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import sample.OtherWindows.OpenFile;
import sample.searchF.Search;
import sample.trackInfo.BaggageTI;
import sample.trackInfo.InfoT;
import sample.pojo.Data;
import sample.OtherWindows.convertWindow;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringJoiner;

public class Controller implements Initializable{

    @FXML
    private Button search_da0;
    @FXML
    private Button convertButton;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private Text progressText;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TableView tableView;
    @FXML
    private TableColumn<Data, Integer> numColumn;
    @FXML
    private TableColumn<Data, String> nameColumn;
    @FXML
    private TableColumn<Data, String> artistColumn;
    @FXML
    private TableColumn<Data, String> albumColumn;
    @FXML
    private TableColumn<Data, String> timeColumn;

    private ObservableList tracks = FXCollections.observableArrayList();

    private Search search = new Search();
    private final String MUSIC_PATH = "/home/deadboy/VKApp/src/sample/Music/";

    public void action_search_da0() throws Exception {
        tracks.clear();
            OpenFile.show("Choose directory with da0 files:");
            progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
            progressText.setText("");
            InfoT name = new InfoT();
            List files_da0 = null;
            try {
                files_da0 = search.getFind(OpenFile.getPath(), "^(.)*.mp3");
                long[] dates = search.getFilesDate(files_da0);
                name.goQuickSort(dates, files_da0);
                InfoT it = new InfoT();
                it.goFileInfo(files_da0);
                BaggageTI bTI = it.getTracksInfo();
                for (int i = files_da0.size()-1; i > -1; i--) {
                    tracks.add(new Data(i+1, bTI.trackName[i], bTI.trackArtist[i], bTI.trackAlbum[i], bTI.trackTime[i]));
                }
                tableView.setItems(tracks);
            }catch (NullPointerException e) {
                e.printStackTrace();
            }
            //
            progressBar.setProgress(0);
            //
            /*if (search.getNewMusic(str, str, 1, 2)) {
                convertButton.setDisable(false);
            }
            */
            progressText.setText("");
            tracks.removeAll();
    }

    public void action_convert() {
        convertWindow cw = new convertWindow();
        try {
            cw.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (!search.getPathExist(MUSIC_PATH, true));
        else {
            Pane pane = new Pane();
            List music = null;
            try {
                music = search.getFind(MUSIC_PATH, "^(.)*");
            } catch (Exception e) {
                System.out.println("No music files");
            }
            if (music.size() != 0) {
                Text[] text = new Text[music.size()];
                for (int i = 1; i < music.size()+1; i++) {
                    text[i-1] = new Text(0, i*20, music.get(i-1).toString().substring(MUSIC_PATH.length()));
                }
                pane.getChildren().addAll(text);
                scrollPane.setContent(pane);
            }
        }
        numColumn.setCellValueFactory(new PropertyValueFactory<Data, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Data, String>("name"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<Data, String>("artist"));
        albumColumn.setCellValueFactory(new PropertyValueFactory<Data, String>("album"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<Data, String>("time"));
    }
}
