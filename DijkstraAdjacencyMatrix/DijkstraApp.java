package adjacencyMatrix;
//import java.util.Scanner; 

import java.util.Scanner;

//part(a) of the project
public class DijkstraApp {	
	
	public static void main(String args[]) {		
		Scanner sc = new Scanner(System.in);
		long startTime, endTime, duration; 
		
	
		
		//test with graph from tutorial 2 question 1
		int vertices,source,edges,start, end, weight; 

		System.out.println("Enter the number of vertices: "); 
		vertices = sc.nextInt(); 
		Graph g = new Graph(vertices);
		
		System.out.println("Enter the number of edges: "); 
		edges = sc.nextInt(); 
		
		System.out.println("Adding Edges: "); 
		
		for (int i = 0; i < edges; i++) {
			System.out.printf("Edge %d : \n", i + 1); 
			
			System.out.printf("Enter the start vertex for edge %d (from vertex %d to vertex %d) : \n", i+1, 0, vertices - 1);
			start = sc.nextInt(); 
			
			System.out.printf("Enter the end vertex for edge %d (from vertex %d to vertex %d) : \n", i+1, 0, vertices - 1);
			end = sc.nextInt(); 
			
			System.out.println("Enter the weight of this edge : "); 
			weight = sc.nextInt(); 
			
			g.addEdge(start, end, weight);
	
		}
	
		
		/*
		test with graph from tutorial 
		
		g.addEdge(0, 4, 8);
		g.addEdge(1, 3, 4);
		g.addEdge(3, 4, 3);
		g.addEdge(1, 4, 3);
		g.addEdge(2,3,1);
		g.addEdge(0, 1, 4);
		g.addEdge(0, 2, 2);
		g.addEdge(0, 3, 6);
		g.addEdge(3, 1, 1);

		 */
		g.printMatrix();
		g.printEdges(); 
		
		//test
		
		System.out.printf("Enter the source vertex to find the shortest path (from vertex %d to vertex %d) : \n", 0, vertices - 1); 
		source = sc.nextInt(); 
		Dijkstra test = new Dijkstra(g, source); 
		
		
		startTime = System.nanoTime(); 
		test.findShortestPath();
		endTime = System.nanoTime(); 
		
		duration = endTime - startTime; 
		System.out.printf("The total time taken is: %d.\n", duration); 
		test.printResults();
		
	}
	
	
		
	
}
