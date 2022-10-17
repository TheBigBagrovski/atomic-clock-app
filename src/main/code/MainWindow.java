package code;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainWindow {

    @FXML
    private Label timeLabel = new Label();

    @FXML
    private Button button;

    public void setTime() {
            timeLabel.setText(Main.getTime());
    }

}
