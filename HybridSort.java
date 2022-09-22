package project1;
import java.util.Random; 
import java.util.Scanner; 
import java.util.Arrays; 

public class HybridSort {
	
	private static final int MAX = 100000; //maximum possible number in an array, used for randomization 
	
	public static int hybrid_comparisons = 0; 
	public static int merge_comparisons = 0; 
	
	public static void insertionSort(int a[], int low, int high) { //takes in array a, upper bound and lower bound 
		int temp; 
		for (int i = low + 1; i <= high; i++) {
			for (int j = i; j > low; j--) {
			hybrid_comparisons++; 
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
	
	public static void hybridSort(int a[], int S, int low, int high) {
		if (high - low <= S) insertionSort(a, low ,high); 
		else {
			int mid = (low + high) / 2; 
			hybridSort(a, S, low, mid); //call on first list to sort
			hybridSort(a, S, mid+1, high); //call on second list to sort 
			hybrid_merge(a, low, mid, high);
		} 
	}

	
	public static void hybrid_merge(int a[], int low,int mid, int high) {
		//create new sub-arrays for left and right merge
		int left[] = Arrays.copyOfRange(a, low, mid+1); 
		int right[] = Arrays.copyOfRange(a, mid+1, high+1); 
		
		//pointers for first and second sub-array as well as the index of the merged sub-array 
		int left_idx = 0, right_idx = 0, idx = low; 
		
		//repeat while both arrays are not empty
		while (left_idx < left.length && right_idx < right.length) {
			hybrid_comparisons++; 
			if (left[left_idx] < right[right_idx]) { 
				a[idx++] = left[left_idx++]; 
			}
			else if (right[right_idx] < left[left_idx]) {
				a[idx++] = right[right_idx++]; 
			}
			else {
				a[idx++] = left[left_idx++]; 
				a[idx++] = right[right_idx++]; 
			}
		}
		
		//copy remaining elements of the left and right sub-arrays 
		while (left_idx < left.length) {
			a[idx++] = left[left_idx++]; 
		}
		
		while (right_idx < right.length) {
			a[idx++] = right[right_idx++]; 
		}
		
	}
	

	
	
	public static void mergeSort(int a[], int low, int high) {
		int mid = (low + high) /2; 

		if (high - low <= 0) return; 
		else if (high - low > 1) {
			mergeSort(a, low, mid);
			mergeSort(a, mid+1, high); 
		}
		merge(a,low, mid,high); 

	}
	
public static void merge(int a[], int low,int mid, int high) {
		
		//create new sub-arrays for left and right merge
		int left[] = Arrays.copyOfRange(a, low, mid+1); 
		int right[] = Arrays.copyOfRange(a, mid+1, high+1); 
		
		//pointers for first and second sub-array as well as the index of the merged sub-array 
		int left_idx = 0, right_idx = 0, idx = low; 
		
		//repeat while both arrays are not empty
		while (left_idx < left.length && right_idx < right.length) {
			merge_comparisons++; 
			
			if (left[left_idx] < right[right_idx]) { 
				a[idx++] = left[left_idx++]; 
			}
			else if (right[right_idx] < left[left_idx]) {
				a[idx++] = right[right_idx++]; 
			}
			else {
				a[idx++] = left[left_idx++]; 
				a[idx++] = right[right_idx++]; 
			}
		
		}
		
		//copy remaining elements of the left and right sub-arrays 
		while (left_idx < left.length) {
			a[idx++] = left[left_idx++]; 
		}
		
		while (right_idx < right.length) {
			a[idx++] = right[right_idx++]; 
		}
		
	}
	
	
	
	public static void printArray(int a[]) {
		System.out.println("The array elements are \n================"); 
		for (int i = 0; i < a.length; i++) {
			System.out.printf("%d\n", a[i]); 
		}
		System.out.println("================"); 
	}
	
	
	
	//generate an array of size n with random integers ; 
	public static int[] generate_array(int n) {
		Random r = new Random();
		int a[] = new int[n]; 
		
		for (int j = 0; j < n; j++) {
			a[j] = r.nextInt(MAX) + 1; 
		}
		return a; 
	}
	
	
	
	
	//driver code
	public static void main(String[] args) {
	
	Scanner sc = new Scanner(System.in) ;
	long startTime,endTime,duration; 
	

	System.out.println("Enter the value of S:"); 
	int S = sc.nextInt();
	
	System.out.println("Enter the size of the array:"); 
	int size = sc.nextInt(); 
	
	int a[]= generate_array(size); 
	int b[] = a.clone();
	
	//hybrid sort
	System.out.println("Hybrid Sort"); 
	System.out.println("============"); 
	System.out.printf("Current S: %d \n", S); 
	System.out.printf("Current size of the input array: %d. \n", size); 

	startTime= System.nanoTime(); 
	hybridSort(a,S, 0, size-1); 
	endTime = System.nanoTime(); 
	duration = (endTime - startTime) ; //in nanoseconds

	
	System.out.printf("The total time taken by hybrid sort is %d. \n", duration);
	System.out.printf("The number of comparisons by hybrid sort is %d. \n", hybrid_comparisons); 


	
	
	System.out.println("Merge Sort"); 
	System.out.println("============"); 
	System.out.printf("Current size of the input array: %d. \n", size); 
	
	startTime= System.nanoTime(); 
	mergeSort(b,0, size-1); 
	endTime = System.nanoTime(); 
	duration = (endTime - startTime) ; //in nanoseconds
	
	System.out.printf("The total time taken by merge sort is %d. \n", duration);
	System.out.printf("The number of comparisons by merge sort is %d. \n", merge_comparisons); 
	System.out.println("============"); 
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
