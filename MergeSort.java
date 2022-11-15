import java. util.ArrayDeque;
import java.util.Iterator;

public class MergeSort 
{
  public static CardPile merge (CardPile a, CardPile b)
  {
    System.out.println("merging " );

    //create new enpty cardoito
    CardPile c = new CardPile(2,2);

    while ((a.isEmpty() == false) & (b.isEmpty () == false)) 
    {
      //comapres cards
      if (a.getFirst().compareTo(b.getFirst()) < 0) 
      {
        c.addLast(a.remove());
      }
      else
      {
        c.addLast(b.remove());
      }
    }
    //adds a or b in cardoito
    c.append(a);
    c.append(b);
    return c;
  }
  
  public static CardPile sort(CardPile unsorted, SortRecorder record)
  {
    ArrayDeque<CardPile> queue = new ArrayDeque<CardPile>();
    
    Iterator<Card> iterator = unsorted.iterator();
    
    //queue to create singleton lists
    while (iterator.hasNext())
    {
      CardPile next_cardpile = new CardPile(2,2);
      next_cardpile.addFirst(iterator.next());
      queue.addFirst(next_cardpile);
    }
  
    while (queue.size() > 1)
    {
      System.out.println("queue size: " + queue.size ());
      record.next();
      //popping cards
      CardPile list_one = new CardPile(2,2);
      list_one = queue.remove();
      CardPile list_two = new CardPile(2,2);
      list_two = queue.remove();
      CardPile list_three = new CardPile(2,2);
      //merging lists
      list_three = merge(list_one, list_two);
      queue.addLast(list_three);

      for (CardPile pile: queue)
      {
         record.add(pile);
      }
    }
    return queue.remove();
  }
}  
    
