package uet.oop.bomberman;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.audio.Music;
import uet.oop.bomberman.graphics.Sprite;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ScoreBoardController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane myPane;
    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, Integer> rankings;

    @FXML
    private TableColumn<User, String> name;

    @FXML
    private TableColumn<User, Integer> score;
    @FXML
    private Button backButton;

    ObservableList<User> list = FXCollections.observableArrayList(
            new User(1, "Nam", 3000),
            new User(2, "Son", 1500),
            new User(3, "NULL", 200)
    );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rankings.setCellValueFactory(new PropertyValueFactory<User, Integer>("rankings"));
        name.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        score.setCellValueFactory(new PropertyValueFactory<User, Integer>("score"));
        table.setItems(list);
    }

    @FXML
    public void backOptionClickedHandle(ActionEvent event) throws IOException {
        if(!BombermanGame.muted.isMutedSound()) {
            new Music(Music.CLICKED).play();
        }
        switchToMenu(event);
    }
    @FXML
    public void switchToMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void readScoresFromFile() throws IOException {
        File file = new File("/scoreboard/scores.txt");
        FileReader reader = new FileReader(file);
        BufferedReader in = new BufferedReader(reader);
        String s;
        StringBuffer sb = new StringBuffer();
        try {
            int count = 0;
            while ((s = in.readLine())!= null) {
                sb.append(s);
                count++;
                if (count != 3) {
                    sb.append("          ");
                } else {
                    count = 0;
                    BombermanGame.scores.add(new Text(10 * Sprite.SCALED_SIZE, 15 * Sprite.SCALED_SIZE, sb.toString()));
                    sb = new StringBuffer();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        reader.close();
    }
}
