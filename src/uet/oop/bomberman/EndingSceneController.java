package uet.oop.bomberman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import uet.oop.bomberman.audio.Music;

import java.io.IOException;

public class EndingSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button backToMenu;

    @FXML
    private ImageView myImageView;
    Image winScene = new Image(getClass().getResourceAsStream("/win.jpg"));
    Image loseScene = new Image(getClass().getResourceAsStream("/lose.png"));

    @FXML
    public void backToMenuClickedHandle(ActionEvent event) throws IOException {
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

    public void getEndingScene(ActionEvent event) throws IOException {
        if (BombermanGame.lc.isWon()) {
            root = FXMLLoader.load(getClass().getResource("/fxml/WinScene.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            root = FXMLLoader.load(getClass().getResource("/fxml/LoseScene.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

}
