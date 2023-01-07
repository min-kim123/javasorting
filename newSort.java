/*
 * Author: Min Kim
 * Program Description: This program will help you sort your numbers. You have the option of bubble, selection, table, or quicksort.
 * Date: 1/6/23
 */
package Sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class newSort {
	
	Scanner consoleInput = new Scanner(System.in);
	String input;
	Scanner fileInput;
	int[] inputArray;
	long startTime;
	
	public newSort() {
		System.out.println("Enter a number for the input file.");
		System.out.println("1: input1.txt   2: input2.txt   3: input3.txt");
		input = consoleInput.nextLine();
		if (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2'
				&& input.charAt(0) != '3') {
			System.out.println("Enter 1, 2, 3, 4, or 5");
			while (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2' 
					&& input.charAt(0) != '3') {
				input = consoleInput.nextLine();
			}
		}
		//try catch for opening the file
		try {
			fileInput = new Scanner(new File("input" + input.charAt(0) + ".txt"));
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
			System.exit(0);
		}
		
		String infile = fileInput.nextLine();
		String[] inputStringArray = infile.split(",");
		inputArray = new int[inputStringArray.length];
		for (int i = 0; i < inputStringArray.length; i++) {
			inputArray[i] = Integer.parseInt(inputStringArray[i]);
			System.out.println(inputArray[i]);
		}
		System.out.println("Enter a number for the sort you want to use.");
		System.out.println("1: Bubble   2: Selection   3: Table   4: Quick Sort");
		input = consoleInput.nextLine();
		if (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2'
				&& input.charAt(0) != '3') {
			System.out.println("Enter 1, 2, or 3");
			while (input.length() != 1 && input.charAt(0) != '1' && input.charAt(0) != '2' 
					&& input.charAt(0) != '3' && input.charAt(0) != '4') {
				input = consoleInput.nextLine();
			}
		}
		
		startTime = System.currentTimeMillis();
		if (input.equals("1")) {//bubble sort
			inputArray = bubbleSort(inputArray);
		}
		if (input.equals("2")) {//selection sort
			inputArray = selectionSort(inputArray);
		}
		if (input.equals("3")) {//table sort
			inputArray = tableSort(inputArray);
		}
		if (input.equals("4")) { //quick sort
			quickSort(inputArray, 0, inputArray.length - 1);
		}
		long totalTime = System.currentTimeMillis() - startTime;
		PrintWriter pw;
		
		try {
			pw = new PrintWriter(new FileWriter(new File("output.txt")));
			String output = "";
			for (int i = 0; i < inputArray.length; i++) {
				output += inputArray[i] +",";
			}
			output += "\nTotal Time: " + totalTime + " Milliseconds";
			pw.write(output);
			pw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
			System.exit(0);
		}

		for (int i = 0; i < inputArray.length; i++) {
			System.out.println(inputArray[i]);
		}
		System.out.println();
		System.out.println("Total Time: " + totalTime + " Milliseconds");
	}
		// compare each pair of numbers and move the larger to the right
		int[] bubbleSort(int[] array) {
			for (int j = 0; j < array.length; ++j) {
				for (int i = 0; i < array.length - 1; ++i) {
					// if the one on the left is larger
					if (array[i] > array[i+1]) {
						// swap
						int temp = array[i];
						array[i] = array[i+1];
						array[i+1] = temp;
					}
				}
			}
			return array;
		}
		int[] selectionSort(int[] array) {
			for (int j = 0; j < array.length; ++j) {
				int smallestNumber = array[j];
				int smallestIndex = j;
				for (int i = j; i < array.length; i++) {
					if (array[i] < smallestNumber) {
						smallestNumber = array[i];
						smallestIndex = i;
					}
				}
				int temp = array[smallestIndex];
				array[smallestIndex] = array[j];
				array[j] = temp;
			}
			
			return array;
		}
		// tally how often you see each number, print out that number of times
		int [] tableSort(int[] array) {
			int[] tally = new int[1001];
			for (int i = 0; i < array.length; i++) {
				tally[array[i]]++;
			}
			
			int count = 0;
			//i keeps track of the actual number
			for (int i = 0; i < tally.length; i++) {
				// j keeps track of how many times we've seen that number
				for (int j = 0; j < tally[i]; j++) {
					array[count] = i;
					count++;
				}
			}
			return array;
		}
	
		void quickSort(int[] array, int low, int high) {
			if (low < high) {
				int a = partition(array, low, high);
				quickSort(array, low, a - 1);
				quickSort(array, a + 1, high);
			}
		}
		
		int partition(int[] array, int firstNumber, int lastNumber) {
			int pivot = firstNumber; 
			int smallestNumber = array[firstNumber];
			int smallestIndex = firstNumber + 1;
			
			for (int j = firstNumber + 1; j <= lastNumber; j++) {
					if (array[j] < array[pivot]) {
						int temp = array[j];//set temp to number
						array[j] = array[smallestIndex];
						array[smallestIndex] = temp;
						
						smallestIndex++;
					}
				}
			int temp = array[smallestIndex - 1];
			array[smallestIndex - 1] = array[pivot];
			array[pivot] = temp;
			
			return smallestIndex - 1;
		}
		

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new newSort();
	}
}
