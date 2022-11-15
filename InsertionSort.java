import java.util.ListIterator;

public class InsertionSort {
  
  public static CardPile sort(CardPile unsorted, SortRecorder record) {
    
    // register the starting configuration with the recorder
    record.add(unsorted);

    // Here is the result list you will be creating
    CardPile sorted = new CardPile(2,2);
  
    // ***********************************************************
    // Here is where you'll do the "work" of InsertionSort:
    //   - Use sorted to store the "sorted portion"
    //   - Don't forget to register the new state with the
    //     recorder after each card is transferred:
    //        record.next();        // tell it this is a new step
    //        record.add(sorted);   // the sorted pile
    //        record.add(unsorted); // the unsorted pile
    // ***********************************************************

    while (unsorted.size() > 0)
    {
      Card unsortedCard = unsorted.removeFirst();
      
      //adding card to sorted list if empty
      if (sorted.isEmpty())
      {
        sorted.add(unsortedCard);
      } 
      else 
      {
        for (int i = 0; i < sorted.size(); i++)
        {  
          Card sortedCard = sorted.get(i);
          
          //Compares cards

          //Unsortedcard is smaller 
          if (unsortedCard.compareTo(sortedCard) < 0)
          {
            System.out.println(unsortedCard + " vs "+  sortedCard);
            System.out.println(unsortedCard + " is Smaller! ");
            sorted.insertBefore(unsortedCard,sortedCard);
            break;
          } 
          //Sortedcard is smaller
          else if (unsortedCard.compareTo(sortedCard) > 0)
          {
            if (sorted.indexOf(sortedCard) == sorted.size()-1)
            {
              System.out.println(unsortedCard + " vs "+  sortedCard);
              System.out.println(sortedCard + " is Smaller! ");
              sorted.insertAfter(unsortedCard,sortedCard);
              break;
            } 
            else 
            {
              Card sortedCardNext = sorted.get(i+1);
              
              //gets the next card in sorted list to comapre
              if (unsortedCard.compareTo(sortedCardNext) < 0)
              {
                sorted.insertAfter(unsortedCard,sortedCard);
                break;
              }
            }
          }
        }
      }
        record.next();        // tell it this is a new step
        record.add(sorted);   // the sorted pile
        record.add(unsorted); // the unsorted pile
    }
      // return the sorted result here
      return sorted;
    
  }
}
