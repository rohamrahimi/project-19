package view.Graphic;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class StartNewGame extends MenuController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void goToSinglePlayerMode() {
        changeMenu("singleGameMenu.fxml");
    }

    public void goToMultiPlayerMode() {
        changeMenu("multiGameMenu.fxml");
    }

    public void exit() {
        changeMenu("MainMenu.fxml");
    }
}
