package helloWorld;

public class Knapsack {
	
	// max function
	public static int max(int j, int k) {
		return(((j>k) ? j: k));
	}
	
	
	//computed using bottom up
	public static int unboundedKnapsack(int[] weight, int[] price, int capacity, int n, int[] dp) {
		
		for(int i = 0 ; i<=capacity; i++) {
			for(int j = 0; j<n ; j++) {
				if(weight[j]<= i) {
					dp[i] = max(dp[i], dp[i-weight[j]]+ price[j]);
				}
			}
		}
		return dp[capacity];
	}
	
	
	
	
	
	public static void main(String[] args) {
		int capacity = 14;
//		int weight[] = {4, 6, 8};
//		int price[] = {7, 6, 9};
		int weight[] = {5, 6, 8};
		int price[] = {7, 6, 9};
		int n = weight.length;
		//dp 1D array 
		int dp[] = new int[capacity+1];
		
		System.out.println(unboundedKnapsack(weight, price, capacity, n, dp));
	}
}
