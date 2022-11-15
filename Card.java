/////////////////////////////
// DO NOT MODIFY THIS FILE //
/////////////////////////////

import java.awt.*;

/**
 *  This class stores information about a specific card.
 *
 *  @author Nicholas R. Howe
 *  @version CSC 112, Sept. 2009
 */
public class Card implements Comparable<Card> {
  /** Prototype of card -- ranks, suit, appearance */
  private final ProtoCard prototype;

  /** Orientation of card */
  private boolean isFaceUp;

  /** Array of every card prototype */
  private static final ProtoCard protoDeck[];

  // This section initializes static fields
  static {
    // Initialize prototype deck
    protoDeck = new ProtoCard[Rank.values().length*Suit.values().length];
    Rank[] ranks = Rank.values();
    Suit[] suits = Suit.values();
    for (int i = 0; i < ranks.length; i++) {
      for (int j = 0; j < suits.length; j++) {
        protoDeck[i+ranks.length*j] = new ProtoCard(ranks[i], suits[j], null);
      }
    }
  }

  /** Constructor initializes data elements */
  public Card(ProtoCard prototype, boolean isFaceUp) {
    this.prototype = prototype;
    this.isFaceUp = isFaceUp;
  }

  /** Accessor for card orientation */
  public boolean getIsFaceUp() {
    return isFaceUp;
  }

  /** Manipulator for card orientation */
  public void flipCard() {
    isFaceUp = !isFaceUp;
  }

  /** Accessor for rank of this card */
  public Rank getRank() { 
    return prototype.rank;
  }

  /** Accessor for suit of this card */
  public Suit suit() { 
    return prototype.suit; 
  }

  /** Text description of card = RANK of SUIT */
  public String toString() { 
    return prototype.rank + " of " + prototype.suit; 
  }

  /** Accessor for image of this card */
  public Image getFrontSide() {
    return prototype.frontSide;
  }

  /** Accessor for image of card back */
  public static Image getBackSide() {
    return ProtoCard.backSide;
  }

  /** Compares two cards */
  public int compareTo(Card c2) {
    return 13*prototype.suit.compareTo(c2.prototype.suit)
        +prototype.rank.compareTo(c2.prototype.rank);
  }

  /** Generates a new deck of cards */
  public static Card[] newDeck(boolean faceUp) {
    Card deck[] = new Card[protoDeck.length];
    for (int i = 0; i < protoDeck.length; i++) {
        deck[i] = new Card(protoDeck[i], faceUp);
    }
    return deck;
  }

  /** 
   *  Make sure images have loaded 
   *
   *  @param c  Component that will use these images
   */
  public static void loadImages(Component c) {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    MediaTracker mediaTracker = new MediaTracker(c);
    try {
      int[] rank_im_order = {12,11,10,9,8,7,6,5,4,3,2,1,0};
      int[] suit_im_order = {0,3,2,1};
      Rank[] ranks = Rank.values();
      Suit[] suits = Suit.values();
      for (int i = 0; i < ranks.length; i++) {
        for (int j = 0; j < suits.length; j++) {
          // line below accounts for different ordering of images
          // from cards in our deck
          int id = suits.length*rank_im_order[i]+suit_im_order[j]+1;
          protoDeck[i+ranks.length*j].frontSide = 
            toolkit.getImage(new java.net.URL("http://cs.smith.edu/~nhowe/teaching/csc212/Graphics/"+id+".png"));
          mediaTracker.addImage(protoDeck[i+ranks.length*j].frontSide,i+ranks.length*j);
        }
      }
      ProtoCard.backSide = toolkit.getImage(new java.net.URL("http://cs.smith.edu/~nhowe/teaching/csc212/Graphics/b1fv.png"));
      mediaTracker.addImage(ProtoCard.backSide,protoDeck.length);
      for (int i = 0; i < protoDeck.length; i++) {
        mediaTracker.waitForID(i);
      }
      mediaTracker.waitForID(protoDeck.length);
    } catch (Exception e) {
      System.out.println("Exception.");
      System.exit(-1);
    }
  }

  /**
   *  Enumerated type for card ranks
   */
  public static enum Rank {DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE}

  /**
   *  Enumerated type for card suits
   */
  public static enum Suit {CLUBS, DIAMONDS, HEARTS, SPADES}

  /**
   *  Holds images of playing cards, along with their rank and suit.
   *
   *  @author  Nicholas R. Howe
   *  @version  CSC 112, 20 February 2006
   */
  private static class ProtoCard {
    /** Rank of card */
    private final Rank rank;
    
    /** Suit of card */
    private final Suit suit;
    
    /** Each card has its own front side image */
    private Image frontSide;

    /** Single back side image shared by all cards */
    private static Image backSide;


    /**
     *  Constructor is private so that we only keep one copy
     *  of each card around.
     */
    private ProtoCard(Rank rank, Suit suit, Image frontSide) {
        this.rank = rank;
        this.suit = suit;
        this.frontSide = frontSide;
    }
  }
}
