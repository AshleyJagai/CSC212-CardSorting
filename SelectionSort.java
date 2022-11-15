import java.util.ListIterator;  
//import SupportFiles.*;

public class SelectionSort 
{
  public static CardPile sort(CardPile unsorted, SortRecorder record)   
  {
    
    // register the starting configuration with the recorder
    record.add(unsorted);

    // Here is the result list you will be creating
    CardPile sorted = new CardPile(2,2);
    
    Card smallCard = unsorted.getFirst();
    while (unsorted.size() > 0) {
      ListIterator <Card> position = unsorted.listIterator(0);
      smallCard = unsorted.getFirst();
      while (position.hasNext()){
        
        Card compareCard = position.next();
        
        if (smallCard.compareTo(compareCard) > 0){
          smallCard = compareCard;
        }
      }
      unsorted.remove(smallCard);
      sorted.addLast(smallCard);
      record.next();        // tell it this is a new step
      record.add(sorted);   // the sorted pile
      record.add(unsorted); // the unsorted pile
      
    }
    System.out.println(sorted);

   
    // return the sorted result here
    
    return sorted;
    }
}
