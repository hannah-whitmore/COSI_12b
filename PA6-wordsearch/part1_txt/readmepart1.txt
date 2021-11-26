Hannah Whitmore
COSI 12b, Spring 2020, Section 01
Programming Assignment #6, Part1, 4/23/20

TreasureHunter

Importing each text file : 

Importing each file into the ArrayList vs into the LinkedList were pretty similar for each file, but
generally the ArrayList was a little faster. Both lists took a longer time as the text files became 
larger
The first map was of size 80000 and the last was 2880000, so the time it took to import into both lists increased with each file

Finding the treasure : 

Finding the Treasure using an ArrayList was significantly faster than using a LinkedList. This makes sense because
an ArrayList has random access, so getting an element at any given index is extremely fast. With every new index,
the ArrayList could go directly to it. Conversely, because an iterator was not used in this part with the 
LinkedList, it was very slow at getting elements. This is because it had to start at the beginning of the list
with every new index in order to find it.

The length of the treasure path was 1000 for all 8 maps.

Locations the treasure was found at in increasing order:
tmap1 : 4855
tmap2 : 84240
tmap7 : 121099
tmap6 : 132109
tmap3 : 136260
tmap4 : 309940
tmap5 : 324801
tmap8 : 520828