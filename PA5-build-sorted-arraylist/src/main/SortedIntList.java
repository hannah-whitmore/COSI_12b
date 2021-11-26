// Hannah Whitmore
// hwhitmore@brandeis.edu
// COSI 12b
// April 12, 2020

package main;

import java.util.*;

public class SortedIntList extends ArrayIntList {
	private boolean isUnique;
	
	// constructor with default size of 10 and unique set to false
	public SortedIntList() {
		super();
		this.isUnique = false;	
	}
	
	// constructor with default size 10 and given unique setting
	public SortedIntList(boolean unique) {
		super();
		this.isUnique = unique;
	}
	
	// constructor with given capacity and unique setting, would throw exception if negative capacity because of super call
	public SortedIntList (int capacity){
		super(capacity);
		this.isUnique = false;
		
	}
	
	// constructor with unique setting and given capacity
	public SortedIntList (boolean unique, int capacity) {
		super(capacity);
		this.isUnique = unique;
	}
	
	// adds elements in a sorted order
	public void add(int value) {
	
	// if isUnique = false -- add any element to the list
	// if isUnique = true -- only add element if binary search hasn't found it already in the list
															// aka binarySearch has returned a negative number
	if ((!getUnique()) || (getUnique() && binarySearch(value)<0)){
			
			int i; // i will hold the index where the element should be added 
			for(i=0;i<this.size();i++){
		        if(this.list[i]>value) // once an elment is larger than the value, break, bc this is the index where the element
		          break; 			// should be added to retain sorted order
		      }     
			
			// ex: [1, 4, 7], adding 3 -- 7 is bigger than 3, add at index 2
			
			// new list one element bigger 
			int[] shifted = new int[size() + 1];
			
			// copy over elements to new bigger list up until index where adding element
			for (int j = 0; j < i; j++) {
				shifted[j] = this.list[j];
					
				}
			// add the element at i, which has been holding the index
			shifted[i] = value;
			setSize();
			
			// after i, copy over the remaining elements to new list
			for (int j = i+1; j<shifted.length; j++) {	
				shifted[j] = this.list[j-1];
				
			}
			this.list = shifted;
		}
	}
	
	// should not be able to add an element at any index bc then wouldn't be sorted, throw exception
	public void add(int index, int value) {
		throw new UnsupportedOperationException();
	}
	
	// overrides toString in arrayIntList to include the S before and the U at the end if unique is true
	public String toString() {
		String listToString = "S: [" + this.list[0]; // start
		for (int i=1; i<size(); i++) {
			listToString += ", " + this.list[i]; // each element
		}
		if (getUnique()) {
			listToString += "]U"; // end
		} else {
			listToString += "]";
		}
		return listToString;
		
	}
	
	// returns the maximum element in the list
	public int max() {
		if (size() == 0) { // if empty list, throw exception
			throw new NoSuchElementException();
		}
		int maximum = 0; // each element compared to find largest 
		for (int i=0; i<size(); i++) {
			if (this.list[i]>maximum) {
				maximum = this.list[i];
			}
		}
		return maximum;
	}
	
	// returns the minimum element in the list
	public int min() {
		if (size() == 0) {
			throw new NoSuchElementException();
		}
		
		int minimum = 10000; // each element compared to find the smallest 
		for (int i=0; i<size(); i++) {
			if (this.list[i]<minimum) {
				minimum = this.list[i];
			}
			
		}
		return minimum;
	}
	
	// returns unique setting
	public boolean getUnique() {
		return this.isUnique;
	}
	
	// set unique, if unique is true, removes any duplicates in the list 
	public void setUnique(boolean unique) {
		this.isUnique = unique;
		if (this.isUnique) {
			for (int i=0; i<size()-1; i++) {
				if (this.list[i] == this.list[i+1]) { // bc sorted, duplicates will be next to each other
					remove(i);
					i-=1; // bc removing shifts the list, the next element is now at what i previously was
							// this is why I decrement before it increments to keep i 
				}
			}
		}
	}
	// calls binary search, since would return the same value
	public int indexOf(int value) {
		return binarySearch(value);
		
	}
	
	// implements binary search as faster search, returns the index of value, and negative if not found
	public int binarySearch(int value) {
		int index = Arrays.binarySearch(this.list, 0, size(), value); // searches whole list
		return index;
	}
	
}
