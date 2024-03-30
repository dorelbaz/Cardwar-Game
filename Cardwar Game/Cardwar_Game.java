public class Cardwar_Game
{
    public static void main(String[] args)
    {
        /* Generates 2 random card decks for player 1 and 2.*/
        DeckOfCards player1 = new DeckOfCards();
        DeckOfCards player2 = new DeckOfCards();

        /* Starts the card war game. */
        player1.versus(player2);
    }
}
