public class Quicksort {
  
  public static CardPile sort(CardPile unsorted, SortRecorder record) {

    // ***********************************************************
    // Here is where you'll check the stop condition and return
    // if it is satisfied.
    // ***********************************************************
    
    // Here are the two partitions you will be creating
    CardPile smaller = new CardPile(2,2);
    CardPile bigger = new CardPile(2,2);

    // ***********************************************************
    // Here is where you'll do the partition part of Quicksort:
    //   - Choose a pivot
    //   - Partition the unsorted list into two piles
    // ***********************************************************
    /**The pivot*/
    Card pivot = unsorted.remove();
    /**Holds the value of the current card*/
    Card current_card;
    
    while(unsorted.isEmpty() == false){
      current_card = unsorted.remove();
    if(current_card.compareTo(pivot) < 0){
      smaller.add(current_card);
    } else {
      bigger.add(current_card);
    }
  }
    
    // register the partitions with the recorder
    record.add(smaller);
    record.add(pivot);
    record.add(bigger);
    record.next();

    // This will hold the assembled result
    CardPile result = new CardPile(2,2);
    
    // ***********************************************************
    // Here is where you'll do the remaining work of Quicksort:
    //   - Make recursive calls on the partitions
    //   - Assemble the sorted results into a single pile
    // ***********************************************************
    if(smaller.isEmpty() == false){
      smaller = sort(smaller, record);
    }
    if(bigger.isEmpty() == false){
      bigger = sort(bigger, record);
    }
    record.add(smaller);
    record.add(pivot);
    record.add(bigger);
    System.out.println(result);
    // record the sorted result
    record.add(result);
    record.next();
    
    // return the sorted result here
    return result;
  }
}
