import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;

public class DialogPaneController {
    private boolean firstClick = true;

    @FXML
    private Button action;

    @FXML
    private DialogPane dialogpane;

    @FXML
    void on_Button_Clicked(ActionEvent event) 
    {
        if (!DeckOfCards.getEndOfGame())
        {
            if (firstClick)
            {
                action.setText("Draw Card");
                
                /* Generates 2 random card decks for player 1 and 2.*/
                CardWarGame.player1 = new DeckOfCards();
                CardWarGame.player2 = new DeckOfCards();
        
                DeckOfCards.setEndOfGame(false);
                firstClick = false;
            }
            
            /* Prints the players decks.*/
            System.out.println("Player 1 ");
            DeckOfCards.printDeck(CardWarGame.player1);
            System.out.println("Player 2 ");
            DeckOfCards.printDeck(CardWarGame.player2);
    
            /* Each press of the button starts a single match. */
            (CardWarGame.player1).versus(CardWarGame.player2);
        }
        if (DeckOfCards.getEndOfGame())
        {
            action.setText("New Game");
            firstClick = true;
            DeckOfCards.setEndOfGame(false);
        }
        
        /* Prints the match's result. */
        dialogpane.setHeaderText(DeckOfCards.getHeaderText());
        dialogpane.setContentText(DeckOfCards.getContentText());
    }
}





