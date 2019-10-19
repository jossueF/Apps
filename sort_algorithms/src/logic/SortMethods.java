package logic;

import java.util.ArrayList;
import java.util.Random;

public class SortMethods{
	private ArrayList<Integer> theArray = new ArrayList<>();
	
	public void randomArray(int size) {
		Random random = new Random();
		for(int i = 0; i < size; i++) {
			theArray.add(random.nextInt(100)); // 0 a 99
		}
	}
	
	public ArrayList<Integer> getTheArray() {
		return theArray;
	}
	
	public void cleanArray() {
		theArray.clear();
	}
	
	/*public String bubbleMethod() {
		ArrayList<Integer> bubbleArray = new ArrayList<>();
		bubbleArray.addAll(theArray);
		for(int i = bubbleArray.size() - 1; i > 1; i--) {
			for(int j = 0; j < i; j++) {
				if(bubbleArray.get(j) > bubbleArray.get(j + 1)) {
					swapValues(j, j + 1, bubbleArray);
				}
			}
		}
		return bubbleArray.toString();
	}*/
	
	
	public void swapValues(int indexOne, int indexTwo, ArrayList<Integer> array) {
		int temp = array.get(indexOne);
		array.set(indexOne, array.get(indexTwo));
		array.set(indexTwo, temp);
	}
	
	public void swapValues(int indexOne, int indexTwo, int[] array) {
		int temp = array[indexOne];
		array[indexOne] = array[indexTwo];
		array[indexTwo] = temp;
	}
}
