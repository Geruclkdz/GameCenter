import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pong.PongApp;
import memory.MemoryGame;
import java.util.HashMap;
import java.util.Map;

public class GameCenterController {

    @FXML
    private ImageView pongImage;
    @FXML
    private ImageView memoryImage;

    private final Map<ImageView, Class<?>> gameMap = new HashMap<>();

    @FXML
    private void initialize() {
        gameMap.put(pongImage, PongApp.class);
        gameMap.put(memoryImage, MemoryGame.class);

        for (ImageView imageView : gameMap.keySet()) {
            imageView.setOnMouseClicked(this::startGameApp);
        }
    }

    private void startGameApp(MouseEvent event) {
        ImageView clickedImageView = (ImageView) event.getSource();
        Class<?> gameAppClass = gameMap.get(clickedImageView);

        try {
            Application gameApp = (Application) gameAppClass.getDeclaredConstructor().newInstance();
            gameApp.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
