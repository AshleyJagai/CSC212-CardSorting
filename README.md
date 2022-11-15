# CSC212-CardSorting

Assignment 7: Card Sorting
In this assignment, we'll put combine what we know about LinkedLists with our recent exploration of various sorting algorithms to implement an animated playing-card sorter. As we've seen, nearly anything that can be done using an Array can also be done using a LinkedList -- sometimes more easily, and sometimes less so. You will demonstrate the truth of this by implementing several sorting algorithms on LinkedLists instead of Arrays.

For full credit, you will implement all four of the following:

Insertion Sort
Selection Sort
Merge Sort
Quicksort
(For kudos, you may also choose to implement additional algorithms.)

Note that the wikipedia articles linked above mostly reference the array version of the algorithm, whereas we will be implementing similar stategies using a linked list.

We know that sorting algorithms vary in their efficiency, particularly for longer lists. In the final part of this assignment, you will test your implementions on lists of varying lengths to see how the sorting time changes.

🚧 Heads up! 🚧
In the lecture videos, we've discussed the analysis and implementation of these algorithms using Arrays; you may want to refer to these as you develop your list implementation, but reader beware! We are using the Java LinkedList implementation (which includes some methods with array-like behavior, such as get(int index)) rather than your custom implementation from A6. For full credit, be sure to avoid the use of any methods that involve traversal across the list, including index-based methods and also ones that require searching for a particular element. These undermine the efficiency of a list-based approach!

Running a Demo
We have provided all of the necessary code to drive the graphical portion of this assignment in Card.java and CardPile.java; you will not need to modify either of these files, but feel free to take a look inside to get a sense for how they work.

Main.java contains a visual demonstration of the progression of the (ineffective) sorting algorithm implemented in FakeSort.java:

FakeSort in action

This program doesn't do any actual sorting, it just moves the cards from the "unsorted" area to the "sorted" area one-by-one, allowing you to scrub through the algorithm step by step to see it progress. Run the program and move the slider to see what happens.

Program Specifications
In order to keep things organized, you will write separate classes for each sorting algorithm you implement in files called InsertionSort.java, SelectionSort.java, MergeSort.java, and Quicksort.java respectively. Stubs for these classes have already been provided.

Your programs should follow the outline given in FakeSort.java: initialize a deck of cards, shuffle them, and then sort them. As it sorts, the program should create a record of what it does using the SortRecorder class, as shown in the demo. You should create one snapshot per outer loop iteration in insertion sort and/or selection sort, and one snapshot per merge operation in merge sort.

Phase 1: Sorting on LinkedLists
Your programs will use the CardPile class (which extends LinkedList<Card>) to store each collection of cards that you want to sort. This provides some useful functionality, including being able to call Collections.shuffle(...) on a CardPile (as we do in Line 19 of Main.java) to shuffle the cards.

As we've seen in class, processing a list differs somewhat from processing an array: insertion and deletion of elements is easy on a list, but hard in an array (because a full array includes no room for insertion, and deletion leaves an empty hole in an otherwise full array). Because of this, the algorithms we developed for sorting on arrays involved lots of swapping, because the only way to make room for an element was to move another one out of the way.

With lists, instead of swapping elements to reorder them, you will tend instead to work with two lists, removing elements from the unsorted list and inserting them into the sorted list without disturbing the order of any other items. Note that the unsorted list will begin full and shrink as elements are removed from it, while the sorted list will begin empty and grow until it contains all the elements of the original unsorted list. As mentioned above, if you find yourself swapping values using set(), you're probably using the lists too much like arrays, and failing to take advantage of their strengths.

For reference, below are summaries each of the three sorting algorithms referenced in this assignment as they are implemented on lists:

SelectionSort
Until unsorted is empty:
Scan unsorted for the smallest remaining element.
Remove that element from unsorted and add it to the tail of sorted.
One way to do this: Loop through all the nodes, keeping the index of the smallest element seen so far as you continue to scan through the list, then remove that element by index.

Another way: avoid using an index by actually pulling out the smallest element seen so far, and then swapping it back in if and when you encounter a smaller one.

Yet another: use two iterators, one to traverse the loop element by element and the other to hold the place of the smallest element seen so far, so you get a stable sort.

InsertionSort
Until unsorted is empty:
Remove the first element from unsorted and find the point where it should go in sorted (the point where all previous elements are smaller than the removed element, and all following elements are greater than or equal to it).
Insert the removed element into sorted at this point.
MergeSort
Begin by placing each element of unsorted into its own new singleton CardPile, and add all those piles to a queue.
While more than one list remains on the queue:
Remove the first two lists from the queue and merge them, preserving their sorted order.
Put the result back at the end of the queue.
To merge two sorted lists into a single sorted list:

Look at the first element in each list.
Take the smaller of the two off the front of its old list and put it at the end of a new (merged) list.
Repeat this until both one of the old lists is empty, at which point you can append the remainder of the other original list to the new list.
If the original lists were sorted, and you always take the smallest element available, then the resulting list will also be sorted. (You might want to convince yourself of this fact before continuing.)
Note: the key operation here is the merging of two sorted lists. Probably you will want to develop a method for this and test it thoroughly before tackling the full program.

Quicksort
Quicksort on lists is a bit more straightforward than on arrays. Like MergeSort, it's a recursive algorithm: the stop condition is a list with 0 or 1 elements, which is already sorted and can simply be returned. For the recursive step, do the following:

Take the first element as the pivot. (Note: This works well unless the list is already sorted, but we'll ignore that detail.)
Pull the remaining elements off the list one at a time and append them to either of two new sublists: one for elements less than the pivot and one for elements greater than or equal to the pivot.
Recursively sort the two sublists, then glue the results back together with the pivot in the middle, and return that as the result.
Phase 2: Experimentation
Now that you have working (and throroughly debugged!) implementations of each of the algorithms above, we are ready do some empirical investigation of differences in their running time.

Start by making a variant of each of your sorting algorithms that doesn't contain any reference to SortRecorder, following the demo contained in FakeSortTimer.java. This should not require a major overhaul of the code doing the actual sorting work, and you can copy the main(...) method verbatim.

(Note: Eliminating the recording is necessary because recording takes up both time and memory, which gets in the way of measuring the time required for the sorting itself. Make sure you disable any debugging printouts as well.)

Call the variant versions InsertionSortTimer.java, SelectionSortTimer.java, MergeSortTimer.java, and QuicksortTimer.java, respectively. You will use these stripped-down versions to sort large CardPiles of different sizes, as described below, so that you can see for yourself how they compare for speed.

Using the Unix time command
You can time a program on unix-based systems (like the one driving repl.it) by preceding the call to run it with time, for example:

> time java MergeSortTimer 10000

This will print out a rather cryptic result, with timing numbers in different orders depending on the system. On replit, the first number (labeled real) gives the actual time elapsed. The second number (labeled user) gives the time spent running the program by the CPU, which is the number you are most interested in. The third number (labeled sys) gives the amount of time spent in system calls. If you're running your code on your own computer, the information provided may be somewhat different, but you should still be able to figure out which number is the CPU time.

Please run a sort using each of your implemented algorithms on inputs that double progressively in size: 10000 cards, 20000 cards, 40000 cards, etc. Continue until you see a clear difference in speed, or until one method is unable to finish in a reasonable time (say a few minutes). You may also want to test them out on various kinds of inputs: random order, already sorted, sorted in reverse.

Do your experiments match your expectations? When you're done, write up a short summary of your findings to include in your readme.md.

Kudos: Additional Sorting Algorithms
In lecture and in this assignment, we've explored a small selection of commonly-studied sorting algorithms... but there are many more. If you have time, consider implementing one of the more unusual ones to see how it stacks up!
