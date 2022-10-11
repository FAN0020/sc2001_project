package adjacencyMatrix;


//priority queue in ascending order implemented using 2D-array 
public class PriorityQueue {
	private int pq[][]; //2D array as we also need to store 'ID' as well as the key value to compare 
	private final int MAX; 
	private int count; 
	
	public PriorityQueue(int MAX) {
		this.MAX = MAX; 
		pq = new int[MAX][2]; 
		//pq[v][0] will return the id of the element v
		//pq[v][1] will return the key value of the element v
		
		count = 0; 
		
	}
	
	public boolean isEmpty() {
		return (count == 0); 
	}
	
	public void insert(int id, int key) {
		if (count == MAX) {
			System.out.println("The priority queue is full.");
			return; 
		}
		
		//add key to array 
		if (this.isEmpty()) {
			pq[0][0] = id; 
			pq[0][1] = key; 
		} 
		else {
			//get position and insert 
				int i = count -1; 
				while (i >=0 && key < pq[i][1]) {
					pq[i+1][0] = pq[i][0]; 
					pq[i+1][1] = pq[i][1]; 
					i--; 
				}
 
				pq[i+1][0] = id; 
				pq[i+1][1] = key; 
			
		}
		count++; 

	}
	
	//remove first element 
	void deleteMin() {
		for(int i = 1; i < count; i++) {
			pq[i-1] = pq[i]; 
		}
		count--; 
		
	}
	
	public int getMinID() {
		return pq[0][0]; 
	}
	
	public int getMinPriority() {
		return pq[0][1]; 
	}
	
	
	public void changeKey(int id, int new_key) {
		deleteKey(id); 
		insert(id, new_key); 
	}
	
	
	//helper function to delete a key using its id, used to change the value of the key. 
	private void deleteKey(int id) {
		int position = 0; 
		for (int i = 0; i < count; i++) {
			if (id == pq[i][0]) {
				position = i; 
				break; 
			}
		}
		
		for (int i = position; i < count; i++) {
			pq[i][0] = pq[i+1][0];
			pq[i][1] = pq[i+1][1]; 
		}
		count--; 

	}
	
	
	

}
