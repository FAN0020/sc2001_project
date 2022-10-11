package adjacencyMatrix;

//uses adjacency matrix to represent graphs and array to represent a priority queue.
public class Dijkstra{
	private static final int MAX = 10000; 
	private static final int infinity = Integer.MAX_VALUE; 
	private int d[]; //array to store distance from node 
	private int s[]; //set of vertices whose shortest paths from the source node have already been determined 
	private int pi[]; //array to store predecessors for each vertex 
	private Graph g; 
	private int source; 
	private PriorityQueue pq; 

	public Dijkstra(Graph g, int source) {
		this.g = g; 
		this.source = source; 
		
		int vertices = g.getVertices(); 
		this.pq = new PriorityQueue(vertices);
		this.d = new int[vertices];
		this.pi = new int[vertices]; 
		this.s = new int[vertices]; 
		
		//initialization for each vertex i in graph g
		for (int v = 0; v < g.getVertices(); v++) {
			d[v] = infinity; 
			pi[v] = -1; 
			s[v] = 0; 
		}
	}
	
	public void findShortestPath() {
		int vertices = g.getVertices(); 
		int matrix[][] = g.getMatrix();
		d[source] = 0; 
		//put all vertices in priority queue according to d[v];
		for (int v = 0 ; v < g.getVertices(); v++) {
			pq.insert(v, d[v]);
		}
		
		while (!pq.isEmpty()) {
			int u = pq.getMinID(); 
			pq.deleteMin(); 
			s[u] = 1; 
			
			//for each vertex adjacent to u
			for (int v = 0; v < vertices; v++) {
				if (matrix[u][v] != 0) { //v is adjacent to u
					int w = g.getWeight(u, v); 
					if (s[v] == 0 && d[v] > d[u] + w) {
						d[v] = d[u] + w; 
						pi[v] = u; 
						pq.changeKey(v, d[v]); 
					}
				}
			}
		}
	}
	
	public void printResults() {
		//print headers for vertices; 
		System.out.printf("%8s", "   "); 
		for (int i = 0; i < g.getVertices(); i++) {
           System.out.printf("%8s", String.valueOf(i));
		}
		System.out.println(); 
		
		
		//print S array 
		System.out.printf("%8s","S  " ); 
		for (int i = 0; i < g.getVertices(); i++) {
	           System.out.printf("%8s", String.valueOf(s[i]));
			}
		System.out.println(); 	
		

		//print d array 
		System.out.printf("%8s","d  " ); 
		for (int i = 0; i < g.getVertices(); i++) {
	           System.out.printf("%8s", String.valueOf(d[i]));
			}
		System.out.println(); 	

		
		//print pi array 
		System.out.printf("%8s","pi " ); 
		for (int i = 0; i < g.getVertices(); i++) {
	           System.out.printf("%8s", String.valueOf(pi[i]));
			}
		System.out.println(); 
	}
	


	
}
