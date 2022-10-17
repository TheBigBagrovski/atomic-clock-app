package code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    public static final String URL = "https://www.timeanddate.com/worldclock/fullscreen.html?n=4757";
    private static String time;
    private static Document doc;

    public static String getTime() {
        update();
        return time;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void update() {
        Runnable runnable = () -> {
            try {
                doc = Jsoup.connect(URL).data("query", "Java")
                        .userAgent("Mozilla")
                        .cookie("auth", "token")
                        .timeout(3000)
                        .post();
                time = doc.select("#i_time").text();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread secThread = new Thread(runnable);
        secThread.start();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Atomic time");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main-window.fxml")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
