package project1;
import java.util.Random; 
import java.util.Scanner; 


public class HybridSort {
	
	private static final int MAX = 10000000; //maximum possible number in an array, used for randomization 
	
	public static int comparisons = 0; 
	
	private static void insertionSort(int a[], int low, int high) { //takes in array a, upper bound and lower bound 
		int temp; 
		for (int i = low + 1; i <= high; i++) {
			for (int j = i; j > low; j--) {
			comparisons++; 
			if (a[j] < a[j-1]) {
					temp = a[j]; 
					a[j] = a[j-1]; 
					a[j-1] = temp; 
				}
			else break; 
			}
		}
		return; 
	}
	
	private static void mergeSort(int a[], int S, int low, int high) {
		if (high - low <= S) insertionSort(a, low ,high); 
		else {
			int mid = (low + high) / 2; 
			mergeSort(a, S, low, mid); //call on first list to sort
			mergeSort(a, S, mid+1, high); //call on second list to sort 
			merge(a, low, mid, high);
		} 
	}
	
	private static void merge(int a[], int low,int mid, int high) {
		int start = mid+1; //start of second array 
		
		//base case
		if (high - low <= 0)return; 
		
		//use low and start to maintain the positions of both arrays
		while (low <= mid && start <= high) {//while both halves are not empty
			comparisons++; 
			if (a[low] < a[start]) low++; //no need to swap, first element of first list is in right place 
			
			else if (a[low] > a[start]){ 
				int temp = a[start]; //to be put into the end of merge list 
				int index = start; 
				
				//shift all the elements between low and start to the right by 1
				while (index != low) {
					a[index] = a[index-1]; 
					index--; 
				}
				a[low] = temp; //put the original start element into the correct place 
				
				//increment all pointers 
				low++;
				mid++; 
				start++; 
			}
			
			else { //equal : put both in the final slot 
				//if last element 
				if(low+1 == start) break; 
				
				//else move both to their position 
				else {
					low++; //1st element of 1st array is already is correct position so increment low 
					
					//shift all the elements between low and start to the right by 1
					int temp = a[start]; 
					int index = start; 
					while(index != low) {
						a[index] = a[index-1]; 
						index--; 
					}
					a[low++] = temp; 
					mid++; 
					start++; 
				}
			}
		}
	}
	
	
	public static void printArray(int a[]) {
		System.out.println("The array elements are ====="); 
		for (int i = 0; i < a.length; i++) {
			System.out.printf("%d\n", a[i]); 
		}
		System.out.println("================"); 
	}
	
	//driver code
	public static void main(String[] args) {	
			
		Random r = new Random();
		Scanner sc = new Scanner(System.in); 
		long startTime,endTime,duration; 
		
		
		System.out.println("Enter the size of the array:"); 
		int size = sc.nextInt(); //array to store different sizes for test data set
		
		
		//array declaration 
		int a[] = new int[size]; 
		
		//populate current array with random integers 

        ////random case

		/*for (int j = 0; j < size; j++) {
			a[j] = r.nextInt(MAX) + 1; 
        } 
		*/
	
        //best case
		/*
		 * for (int j = 0; j < size; j++){
			a[j] = (j + 1);
		}

		*/
        
        //worst case
        
		 for (int j = 0; j < size; j++){
			a[j] = (size - j - 1);
		}
       
         
		
		//System.out.println("========Before Sorting======="); 
		//printArray(a); 
		
		
		startTime= System.nanoTime(); 
		mergeSort(a, 10, 0, size-1); //test with （i.e. threshold = 10）
		endTime = System.nanoTime(); 
		
		duration = (endTime - startTime) ; 
		//System.out.println("========After Sorting========"); 
		//printArray(a); 
		
		System.out.printf("The total time taken by hybrid sort is %d. \n", duration);
		System.out.printf("The number of comparisons is %d. \n", comparisons); 

	}
}