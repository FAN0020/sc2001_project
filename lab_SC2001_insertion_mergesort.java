package helloWorld;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class lab_SC2001_insertion_mergesort {
	static int key;
	public static void insertionSort(ArrayList<Integer> list, int s, int e){
		int temp;
		for(int i = s+1; i<=e; i++) {
			for(int j=i; j>s; j--) {
				if(list.get(j)<list.get(j-1)) {
					key++;
					temp = list.get(j);
					list.set(j, list.get(j-1));
					list.set(j-1, temp);
				}
				else break;
			}
		}
	}
	
	public static void printList (ArrayList<Integer> list) {
		//print out contents
		for(int j : list) {
			System.out.print(j);
			System.out.print(" ");
		}
		System.out.println();
	}
	
	private static void ms(ArrayList<Integer> list, int s, int e) {
		key+=3;
		if(s>=e) 
			return;
		// size greater than S then break into 2, if not then call insertion sort
		int S = 2;
		if(e-s <= S) {
			insertionSort(list, s, e);
			return;
		}
		else if (e-s >= 1) {
			int mid = (s+e)/2;
			ms(list, s, mid);	
			ms(list, mid+1, e);
			merge(list, s, e);
		}
		
	}
	
	private static void merge(ArrayList<Integer> list, int s, int e) {
		key++;
		if(e-s<=0)
			return;
		int mid = (s+e)/2;
		int temp, a=s, b=mid+1, i;
		while(a<=mid && b<=e) {
			//first is smaller
			key+=3;
			if(list.get(a)< list.get(b)) {
				a++;
			}
			//second is smaller
			else if(list.get(a)>list.get(b)) {
				temp = list.get(b);
				for(i=b; i>=a+1; i--) {
					list.set(i, list.get(i-1));
				}
				list.set(a++, temp);
				b++;
				mid++;
			}
			//same value
			else if(list.get(a)==list.get(b)) {
				if(a+1 == b) {
					break;
				}
					
				else {
					temp = list.get(b);
					a++;
					for(i=b; i>a; i--) {
						list.set(i, list.get(i-1));
					}
					list.set(a++, temp);
					b++;
					mid++;
				}
			}
			
		}
	}

	public static void main(String[] args) {
		int size = 10_000_000, i;
		Scanner sc = new Scanner(System.in);
		System.out.println("Size of list: ");
		size = sc.nextInt();
		ArrayList<Integer> list = new ArrayList<Integer>();
		// init the arrayList with random integers
		Random rand = new Random();
		for(i=0; i<size; i++) {
			list.add(rand.nextInt(size)+1);
		}
		printList(list);
		ms(list, 0, size-1);
		printList(list);
		System.out.println("Key Comparisons: " + key);
	}

}
