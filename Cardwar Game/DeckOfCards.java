import java.util.ArrayList;

public class DeckOfCards
{
    private final ArrayList<Card> deckOfCards;

    /* Generates a random card deck of initial size 10. */
    public DeckOfCards()
    {
        deckOfCards = new ArrayList<>();
        int initSize = 10;

        for (int i = 0; i < initSize; i++)
        {
            Card card = new Card();
            deckOfCards.add(card);
        }

    }

    private Card getFirstCard()
    {
        return this.deckOfCards.getFirst();
    }

    /* Adds a card to the bottom of a given card deck. */
    private void addCard(Card card)
    {
        this.deckOfCards.add(card);
    }

    /* Removes the first card from a deck and returns it. */
    private Card removeFirst()
    {
        Card temp = this.deckOfCards.getFirst();
        this.deckOfCards.removeFirst();
        return temp;
    }

    private int getSize()
    {
        return this.deckOfCards.size();
    }


    public void versus(DeckOfCards player2)
    {
        int result;
        Card temp1, temp2;

        while (this.getSize() != 0 && player2.getSize() != 0)
        {
            /* Prints the decks of each player after every iteration. */
            System.out.println("Player 1 deck: ");
            DeckOfCards.printDeck(this);
            System.out.println("Player 2 deck: ");
            DeckOfCards.printDeck(player2);

            /* Compare a card from player 1 to a card from player 2. */
            result = this.getFirstCard().compare(player2.getFirstCard());

            if (result == Card.player1takes)
            {
                /* Takes the first card of player 1 and 2 and adds it to player 1 card deck. */
                temp1 = this.removeFirst();
                temp2 = player2.removeFirst();

                this.addCard(temp1);
                this.addCard(temp2);
            }
            else if (result == Card.player2takes)
            {
                /* Takes the first card of player 1 and 2 and adds it to player 2 card deck. */
                temp1 = this.removeFirst();
                temp2 = player2.removeFirst();

                player2.addCard(temp2);
                player2.addCard(temp1);
            }
            else
            {
                /* Create stack 1 and 2 to hold the drawn cards of player 1 and 2 respectively. */
                ArrayList<Card> stack1 = new ArrayList<>();
                ArrayList<Card> stack2 = new ArrayList<>();
                draw3(this, stack1, player2, stack2);
            }
        }

        if (this.getSize() == 0)
        {
            System.out.println("Player 2 wins!!!");
        }
        if (player2.getSize() == 0)
        {
            System.out.println("Player 1 wins!!!");
        }
    }

    private void draw3(DeckOfCards player1, ArrayList<Card> stack1, DeckOfCards player2, ArrayList<Card> stack2)
    {
        int result;
        boolean flag;
        Card temp1, temp2;

        /* Draws 3 cards, including the card that has initiated the draw 3 state,
           from player 1 and add it to his stack. If player 1 had drawn all of his cards,
           then player 2 wins automatically and we return. */
        flag = player1.draw3cards(stack1);
        if (flag) {return;}

        flag = player2.draw3cards(stack2);
        if (flag) {return;}


        /* Compares the third card, after the card that has initiated draw 3 state, of player 1 and 2. */
        result = this.getFirstCard().compare(player2.getFirstCard());
        if (result == Card.player1takes)
        {
            /* Takes the first card of player 1 and 2 and adds it to player 1 card deck.
               Then, add the stacks of the drawn cards to player 1 card deck.             */
            temp1 = player1.removeFirst();
            temp2 = player2.removeFirst();

            player1.addCard(temp1);
            player1.addCard(temp2);

            player1.addStack(stack1);
            player1.addStack(stack2);
        }
        else if (result == Card.player2takes)
        {
            /* Takes the first card of player 1 and 2 and adds it to player 2 card deck.
             *  Then, add the stacks of the drawn cards to player 2 card deck.             */
            temp1 = player1.removeFirst();
            temp2 = player2.removeFirst();

            player2.addCard(temp2);
            player2.addCard(temp1);

            player2.addStack(stack2);
            player2.addStack(stack1);
        }
        else
        {
            /* Repeat the cycle, with the same stacks as before. */
            draw3(player1, stack1, player2, stack2);
        }

    }

    /* Adds a stack of cards to the bottom of a given player card deck. */
    private void addStack(ArrayList<Card> stack)
    {
        while (!stack.isEmpty())
        {
            this.addCard(stack.getFirst());
            stack.removeFirst();
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
        if (this.getSize == 0)
        {
            flag = true;
        }
        return flag;
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
