/////////////////////////////
// DO NOT MODIFY THIS FILE //
/////////////////////////////

import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *  Program keeps track of sorting activities and then displays them
 *
 *  @author Nicholas R. Howe
 *  @version 28 February 2011
 */
public class SortRecorder extends JComponent implements ChangeListener {
  /** Keeps track of moves */
  private LinkedList<LinkedList<CardPile>> record;

  /** Keeps track of where new piles go */
  int offset;

  /** Keeps track of how big window needs to be */
  int maxoffset;

  /** Keeps track of current state to display */
  int index;

  /** Constructor */
  public SortRecorder() {
    record = new LinkedList<LinkedList<CardPile>>();
    record.add(new LinkedList<CardPile>());
    offset = 2;
    index = 0;
  }

  /** Adds a pile to the current record */
  public void add(CardPile pile) {
    LinkedList<CardPile> piles = record.removeLast();
    CardPile cpile = new CardPile(pile);  // make a copy
    cpile.setX(offset);
    cpile.setY(2);
    piles.add(cpile);
    record.add(piles);
    offset += 72+12*pile.size();
    if (offset > maxoffset) {
      maxoffset = offset;
    }
  }

  /** Adds a card in a newly created singleton pile */
  public void add(Card c) {
    CardPile pile = new CardPile(2,2);
    pile.add(c);
    add(pile);
  }

  /** Adds an array to the current record */
  public void add(Card[] cards) {
    LinkedList<CardPile> piles = record.removeLast();
    CardPile cpile = new CardPile(cards,offset,2);
    piles.add(cpile);
    record.add(piles);
    offset += 72+12*cards.length;
    if (offset > maxoffset) {
      maxoffset = offset;
    }
  }

  /** Moves on to the next record */
  public void next() {
    record.add(new LinkedList<CardPile>());
    offset = 0;
  }

  /** Creates a window to put the display into */
  public void display(String title) {
    if (record.getLast().size()==0) {
      // remove empty last record
      record.remove();
    }

    // Make sure we have nice window decorations.
    JFrame.setDefaultLookAndFeelDecorated(true);

    // Create and set up the window.
    JFrame frame = new JFrame(title);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add components
    JSlider slider = new JSlider(JSlider.HORIZONTAL,1,record.size(),1);
    slider.setMajorTickSpacing(record.size());
    slider.setMinorTickSpacing(1);
    slider.setPaintTicks(true);
    slider.setSnapToTicks(true);
    slider.addChangeListener(this);
    frame.getContentPane().add(this);
    frame.getContentPane().add(slider,BorderLayout.SOUTH);

    // Display the window.
    frame.pack();
    frame.setVisible(true);
  }

  public void stateChanged(ChangeEvent e) {
    JSlider source = (JSlider)e.getSource();
    index = (int)source.getValue()-1;
    repaint();
  }

  public void paintComponent(Graphics g) {
    g.setColor(Color.GREEN.darker());
    g.fillRect(0,0,maxoffset,100);
    LinkedList<CardPile> piles = record.get(index);
    for (CardPile pile: piles) {
        pile.draw(g);
    }
  }

  public Dimension getMinimumSize() {
    return new Dimension(Math.min(1000,maxoffset),100);
  }

  public Dimension getPreferredSize() {
    return new Dimension(Math.min(1000,maxoffset),100);
  }
}
