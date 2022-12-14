package adjacencyMatrix;


//graph implemented using adjacency matrix
public class Graph {
	private int vertices; 
	private int matrix[][]; 
	
	public Graph(int vertices) {
		this.vertices = vertices; 
		this.matrix = new int[vertices][vertices];  //initialized to 0
	}
	
	//getter functions
	public int getVertices() {
		return this.vertices;
	}
	
	public int[][] getMatrix() {
		return this.matrix;
	}
	
	
	public int getWeight(int u, int v) {
		return this.matrix[u][v]; 
	}
	

	//manipulation functions 
	public void addEdge(int start, int end, int weight) {
		if (start < this.vertices && end < this.vertices) {
			matrix[start][end] = weight; 
		}
		else {
			System.out.println("This edge cannot be removed. Please try again."); 
		}
	}
	
	public void removeEdge(int start, int end) {
		if (start < this.vertices && end < this.vertices) {
			matrix[start][end] = 0; 
		}
		else {
			System.out.println("This edge cannot be removed. Please try again."); 
		}
	}
	
	
	//print functions
	//print which node is connected to which 
	public void printEdges() {
		for (int i = 0; i < vertices;i++) {
			System.out.printf("Node %d is connected to : ", i);
			for (int j = 0; j < vertices;j++) {
				if (matrix[i][j] != 0) {
					System.out.printf("%d ", j);
				}
			}
			System.out.println(); 
		}
	}
	
	//print matrix 
	public void printMatrix() {
	    for (int i = 0; i < vertices; i++) {
	        for (int j = 0; j < vertices; j++) {
	            if (matrix[i][j] != 0)
	                System.out.printf("%8s", String.valueOf(matrix[i][j]));
	            else System.out.printf("%8s", "/");
	        }
	        System.out.println();
	}
	}
}
