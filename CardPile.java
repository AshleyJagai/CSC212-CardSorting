/////////////////////////////
// DO NOT MODIFY THIS FILE //
/////////////////////////////

import java.awt.*;
import java.util.*;

/**
 *  This class stores a collection of playing cards 
 *  in a linked list format.
 *
 *  @author Nicholas R. Howe
 *  @version CSC 112, 22 February 2006
 */
public class CardPile extends LinkedList<Card> {
  
  /** Location of the pile of cards on the table */
  private int x,y;

  /** Offset between cards in the pile */
  private int offsetX = 12, offsetY = 0;

  /** Constructor initializes location of empty pile */
  public CardPile(int x, int y) {
    super();
    this.x = x;
    this.y = y;
  }

  /** Copy constructor */
  public CardPile(CardPile pile) {
    super(pile);
    this.x = pile.x;
    this.y = pile.y;
  }

  /** Constructor puts array of cards into pile */
  public CardPile(Card[] cards, int x, int y) {
    super(Arrays.asList(cards));
    this.x = x;
    this.y = y;
  }

  /** Accessor for x coordinate of pile */
  public int getX() {
	  return x;
  }

  /** Accessor for y coordinate of pile */
  public int getY() {
	  return y;
  }

  /** Manipulator for x coordinate of pile */
  public void setX(int x) {
    this.x = x;
  }

  /** Manipulator for y coordinate of pile */
  public void setY(int y) {
	  this.y = y;
  }

  /**
   *  Insert a card node before the specified marker
   *
   *  @param node  The card to insert
   *  @param mark  New card goes before this one
   */
  public void insertBefore(Card card, Card mark) {
    int index = indexOf(mark);
    add(index,card);
  }

  /**
   *  Insert a card node after the specified marker
   *
   *  @param card  The card to insert
   *  @param mark  New card goes after this one
   */
  public void insertAfter(Card card, Card mark) {
    int index = indexOf(mark);
    add(index+1,card);
  }

  /**
   *  Inserts a one pile into another, leaving the inserted pile empty
   *
   *  @param insert  list to insert
   *  @param mark  insert before this card
   */
  public void insertBefore(CardPile insert, Card mark) {
    ListIterator<Card> position = listIterator(size());
    // empty loop to find the position to insert at
    while (position.hasPrevious()&&(position.previous()!=mark)) {
      // nothing to do here
    }
    // now move cards one at a time
    while (insert.size()>0) {
      position.add(insert.removeFirst());
    }
  }

  /**
   *  Inserts a one pile into another, leaving the inserted pile empty
   *
   *  @param insert list to insert
   *  @param mark insert after this point
   */
  public void insertAfter(CardPile insert, Card mark) {
    ListIterator<Card> position = listIterator();
    // empty loop to find the position to insert at
    while (position.hasNext()&&(position.next()!=mark)) {
      // nothing to do here
    }
    // now move cards one at a time
    while (insert.size()>0) {
      position.add(insert.removeFirst());
    }
  }

  /**
   *  Moves every element after the mark into a new pile.
   *  If mark is null, entire pile is moved.
   *  The location of the new pile will be (0,0).
   *
   *  @param mark  elements including and after this are moved
   *  @return the suffix pile
   */
  public CardPile split(Card mark) {
	  CardPile suffix = new CardPile(0,0);
	  if (mark != null) {
	    while ((peekLast()!=null)&&(suffix.peekFirst()!=mark)) {
        suffix.addFirst(removeLast());
      }
    }
    return suffix;
  }

  /**
   *  Appends the provided suffix onto this list.
   *  If the suffix list is empty, nothing happens.
   *  If this list is empty, the suffix list takes its place.
   *
   *  @param suffix list to append and empty
   */
  public void append(CardPile suffix) {
    addAll(size(),suffix);
  }

  /**
   *  Draws the pile at its location on the table.
   *
   *  @param g  Graphics object to draw into
   */
  public void draw(Graphics g) {
    int cx = this.x, cy = this.y;
    for (Card card: this) {
      if (card.getIsFaceUp()) {
        g.drawImage(card.getFrontSide(),cx,cy,72,96,null);
          } else {
        g.drawImage(Card.getBackSide(),cx,cy,72,96,null);
      }
      cx += offsetX;
      cy += offsetY;
    }
  }

  /**
   *  Determine if the specified click falls upon a card in this pile.
   *  If so, return the node holding that card.
   *
   *  @param x  Coordinate of mouse click
   *  @param y  Coordinate of mouse click
   *  @return  Card holding clicked card
   */
  public Card locateCard(int x, int y) {
    Card result = null;
    int cx = this.x, cy = this.y;
    for (Card card: this) {
      if ((x >= cx)&&(x <= cx+72)&&(y >= cy)&&(y < cy+96)) {
  	    result = card;
      }
      cx += offsetX;
      cy += offsetY;
    }
    return result;
  }

  /**
   *  Prints a representation of a CardPile
   */
  public void print() {
    if (size()==0) {
      System.out.println("Empty pile.");
    } else {
      for (Card card: this){
        System.out.print(card+", ");
      }
      System.out.println("");
    }
  }
}
