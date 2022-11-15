import java.util.Collections;

//import CardPlie.*;

class Main  {

  /** Starts the program running */
  public static void main(String args[]) {

    // set up a class to record and display the sorting results
    SortRecorder recorder = new SortRecorder();
   
    // set up the deck of cards
    Card.loadImages(recorder);
    CardPile cards = new CardPile(Card.newDeck(true), 2, 2);

    // uncomment this to work with a smaller number of cards:
   // cards = cards.split(cards.get(43));

    // mix up the cards
    Collections.shuffle(cards);

    // if you want to sort in array form, use:
    Card[] card_arr = cards.toArray(new Card[0]);

    // in your program, this would be a call to a sorting algorithm
   // cards = InsertionSort.sort(cards, recorder);

    cards = SelectionSort.sort(cards, recorder);

    // We can print out the (un)sorted result:
    System.out.println("Sorted cards: "+ cards);
    

    
    //System.out.println("sorted: "+ sorted);
    recorder.display("Card Sort Demo: FakeSort");
  }
}



