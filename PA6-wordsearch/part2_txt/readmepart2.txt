Hannah Whitmore
COSI 12b, Spring 2020, Section 01
Programming Assignment #6, Part2, 4/23/20

Warm-up : 

Construct time for ArrayList: 234 milliseconds
Construct time for LinkedList: 500 milliseconds
Deletion time for ArrayList: 37703 milliseconds
Deletion time for LinkedList: 45 milliseconds

Constructing the ArrayList was faster than constructing the LinkList. But when deleting the first 10,000 numbers,
the LinkedList was significantly faster than the ArrayList. This makes sense because when an ArrayList removes
an element there is a shift of all the rest of the elements, but a LinkList can very easy remove an element 
and reassign pointers. 

Word-Search : 

Importing the gibberish file into the ArrayList took 1271 milliseconds
Importing the gibberish file into the LinkedList took 1521 milliseconds

Importing the words in gibberish.txt into an ArrayList and LinkedList took a pretty similar amount of time,
but the ArrayList was slightly faster than the LinkedList, as was true for the warm up 

Message printed:
YOU
SOLVED
THE
CONTRIVED
DELETION
PUZZLE
GREAT
JOB