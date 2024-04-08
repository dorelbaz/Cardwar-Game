import java.util.ArrayList;

public class DeckOfCards
{
    private ArrayList<Card> deckOfCards, stack;
    private static boolean endOfGame;
    private static String headerText, contentText;

    /* Generates a random card deck of initial size 10. */
    public DeckOfCards()
    {
        deckOfCards = new ArrayList<>();
        stack = new ArrayList<>();
        int initSize = 10;

        for (int i = 0; i < initSize; i++)
        {
            Card card = new Card();
            deckOfCards.add(card);
        }
    }

    private Card getFirstCard()
    {
        return this.deckOfCards.get(0);
    }

    /* Adds a card to the bottom of a given card deck. */
    private void addCard(Card card)
    {
        this.deckOfCards.add(card);
    }

    /* Removes the first card from a deck and returns it. */
    private Card removeFirst()
    {
        Card temp = this.deckOfCards.get(0);
        this.deckOfCards.remove(0);
        return temp;
    }

    public void versus(DeckOfCards player2)
    {
        int result;
        Card temp1, temp2;
        
        /* Compare a card from player 1 to a card from player 2. */
        result = this.getFirstCard().compare(player2.getFirstCard());
                
        if (result == Card.player1takes)
        {
            /* Takes the first card of player 1 and 2 and adds it to player 1 card deck. */
            temp1 = this.removeFirst();
            temp2 = player2.removeFirst();
    
            this.addCard(temp1);
            this.addCard(temp2);
                
            this.addStack(this.getStack());
            this.addStack(player2.getStack());
        }
        else if (result == Card.player2takes)
        {
            /* Takes the first card of player 1 and 2 and adds it to player 2 card deck. */
            temp1 = this.removeFirst();
            temp2 = player2.removeFirst();
    
            player2.addCard(temp2);
            player2.addCard(temp1);
                
            player2.addStack(player2.getStack());
            player2.addStack(this.getStack());
        }
        else
        {
            /* Create stack 1 and 2 to hold the drawn cards of player 1 and 2 respectively. */
            draw3(this, player2);
        }
            
        if (this.getSize() == 0)
        {
            setHeaderText("Player 2 wins!!!");
            setContentText("Click on new game to play again");
            setEndOfGame(true);
        }
        if (player2.getSize() == 0)
        {
            setHeaderText("Player 1 wins!!!");
            setContentText("Click on new game to play again");
            setEndOfGame(true);
        }
    }

    private void draw3(DeckOfCards player1, DeckOfCards player2)
    {
        int result;
        boolean flag;
        Card temp1, temp2;

        /* Draws 3 cards, including the card that has initiated the draw 3 state,
           from player 1 and add it to his stack. If player 1 had drawn all of his cards,
           then player 2 wins automatically and we return. */
        flag = player1.draw3cards(player1.getStack());
        if (flag) {return;}

        flag = player2.draw3cards(player2.getStack());
        if (flag) {return;}
    }

    /* Adds a stack of cards to the bottom of a given player card deck. */
    private void addStack(ArrayList<Card> stack)
    {
        while (!stack.isEmpty())
        {
            this.addCard(stack.get(0));
            stack.remove(0);
        }
    }

    /* Adds 3 cards to a given player stack of cards.
    *  flag keeps track if the player card deck hasn't been emptied. */
    private boolean draw3cards(ArrayList<Card> stack)
    {
        int counter = 0;
        boolean flag = false;
        Card temp;

        while (counter < 3 && !flag)
        {
            if (this.getSize() != 0)
            {
                temp = this.removeFirst();
                stack.add(temp);
            }
            else
            {
                flag = true;
            }
            counter++;
        }
        if (this.getSize() == 0)
        {
            flag = true;
        }

        return flag;
    }
    
    public static void setEndOfGame(boolean state)
    {
        endOfGame = state;
    }
    
    public static boolean getEndOfGame()
    {
        return endOfGame;
    }
    
    public static void setHeaderText(String text)
    {
        headerText = text;
    }
    
    public static String getHeaderText()
    {
        return headerText;
    }
    
    public static void setContentText(String text)
    {
        contentText = text;
    }
    
    public static String getContentText()
    {
        return contentText;
    }
    
    private int getSize()
    {
        return this.deckOfCards.size();
    }
    
    private ArrayList<Card> getStack()
    {
        return this.stack;
    }

    public static void printDeck(DeckOfCards deck)
    {
        for (int i = 0; i < deck.getSize(); i++)
        {
            System.out.print(deck.getCard(i).toString() + " ");
        }
        System.out.println("\n");
    }

    private Card getCard(int index)
    {
        return this.deckOfCards.get(index);
    }
}
