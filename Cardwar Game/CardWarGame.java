import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class CardWarGame extends Application 
{
    public static DeckOfCards player1;
    public static DeckOfCards player2;
    
    @Override
    public void start(Stage stage) throws IOException 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Dialog Pane.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Card War Game");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) 
    {
        launch();
    }
}