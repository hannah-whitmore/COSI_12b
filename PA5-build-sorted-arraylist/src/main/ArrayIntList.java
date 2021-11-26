// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12b
// April 12, 2020

package main;

import java.util.*;

public class ArrayIntList {
	private int size;
	private int initialCapacity;
	int [] list;
	
	// constructor for default capacity of 10
	public ArrayIntList() {
		this.list = new int [10];	
		this.size = 0;
		this.initialCapacity = 10;
	}
	
	// constructor for specified capacity 
	public ArrayIntList(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Capacity cannot be negative.");
		} else {
			this.list = new int[capacity];
			this.size = 0;
			this.initialCapacity = capacity;
		}
		
	}
	
	// appends an element to the end of the list, making the size bigger if needed
	public void add(int value) {
		if (this.size == this.initialCapacity) { // the amount of elements in the internal array = its capacity, need to make bigger
			ensureCapacity(this.initialCapacity);
		}
		
		this.list[this.size] = value;
		this.size++;
	}
		
	// adds an element at a given index, shifting elements over
	public void add (int index, int value) {	
		int[] shifted = new int[this.list.length + 1]; // shifted list one bigger for new element
		
		// copy over elements to new shifted list up until index of addition
		for (int i = 0; i < index; i++) {
			shifted[i] = this.list[i];
		} 
		
		// add the new element at the specified index
		shifted[index] = value;
		this.size++;
		
		// copy over the rest of the list after the added element
		for (int i = index + 1; i <= this.list.length; i++) {
			shifted[i] = this.list[i - 1];
		}
		//reassign 
		this.list = shifted;
	}
	
	// returns the element at a given index
	public int get(int index) {
		checkIndex(index, 0, size()-1);  // checkIndex is called here so that cannot try to get an element out of bounds
		return this.list[index];
	}
	
	// returns the first occurrence of a given value
	public int indexOf (int value) {		
		for (int i=0; i<size; i++) {
			if (this.list[i] == value) {
				return i;
			}
		}
		return -1;	// if element not found in list
	}
	
	// returns the capacity of the list
	public int getCapacity() {
		return this.list.length;
	}
	
	// removes an element at a given index
	public void remove(int index) {
		
		int[] shifted = new int[this.list.length - 1]; // new shifted list one smaller than original
		
		// copy over elements from original to shifted up until index of removal
		for (int i = 0; i < index; i++) {
			shifted[i] = this.list[i];
		}
		
		// continues to copy over elements but skips over the index being removed, so it's not there in final list
		for (int i= index; i< this.list.length -1 ; i++) {
			shifted [i] = this.list[i+1];
		}
		
		this.size--;
		this.list = shifted;
		
	}
	
	// returns the current number of elements in the list 
	public int size() {
		return this.size;
	}
	
	// returns a string version of the list
	public String toString() {  // fence post
		String listToString = "[" + this.list[0];  // start
		for (int i=1; i<this.size; i++) {
			listToString += ", " + this.list[i]; // each element
		}
		listToString += "]"; // end
		return listToString;
	}
	
	// removes all elements from the list
	public void clear() {
		this.size = 0;
	}
	
	// returns true if a given value is contained in the list, else false
	public boolean contains(int value) {
		for (int i=0; i<this.size; i++) {
			if (list[i] == value) {
				return true;
			}
		}
		return false;
	}
	
	// makes sure the internal array is large enough to store the given elements
	public void ensureCapacity (int capacity) {
		int [] biggerList = new int[capacity + capacity]; // the array will double in size to make space 
		for (int i =0; i<capacity; i++) { // copying over the elements from smaller array 
			biggerList [i] = list [i];
		}
		list = biggerList;
		
	}
	
	// returns true if the list does not contain any elements, else returns false
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		}
		return false;
		
	}
	
	// throws an index out of bounds exception if the index is not between min and max inclusive 
	private void checkIndex (int index, int min, int max) {
		if (!(index >= min && index <= max)) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	// throws exception because binarySearch cannot be called on unsorted array
	public int binarySearch (int value) {
		throw new UnsupportedOperationException();
		
	}
	
	public void setSize() {
		this.size++;
	}
	
}
