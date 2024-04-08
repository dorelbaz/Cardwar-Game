import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Card
{
    final static int isEqual = 3, player2takes = 2, player1takes = 1;

    final private ArrayList<String> cards = new ArrayList<>(Arrays.asList(
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "Jack",
            "Queen",
            "King",
            "Ace",
            "Joker"
    ));

    private final String card;

    /* Constructs a random card from the cards array list.  */
    public Card()
    {
        Random randomizer = new Random();
        int randomCard = randomizer.nextInt(cards.size());
        card = cards.get(randomCard);
    }

    /* Compares 2 cards from player 1 and 2. */
    public int compare(Card card)
    {
        String cardsDrawn = "Player 1: " + this + "   Player 2: " + card.toString();
        DeckOfCards.setHeaderText(cardsDrawn);
        if (cards.indexOf(this.toString()) < cards.indexOf(card.toString()))
        {
            DeckOfCards.setContentText("Player 2 takes");
            return player2takes;
        }
        if (cards.indexOf(this.toString()) > cards.indexOf(card.toString()))
        {
            DeckOfCards.setContentText("Player 1 takes");
            return player1takes;
        }
        DeckOfCards.setContentText("DRAW 3");
        return isEqual;
    }

    public String toString()
    {
        return card;
    }
}
