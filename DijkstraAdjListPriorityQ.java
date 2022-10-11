package helloWorld;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Dijkstra {
	
	static class Edge implements Comparator<Edge>{
		int destination;
		int weight;
		
		// constructor1
		public Edge() {}
		
		//constructor2
		public Edge(int destination, int weight) {
			this.destination = destination;
			this.weight = weight;
		}
		@Override public int compare(Edge edge1, Edge edge2) {
			if(edge1.weight < edge2.weight) {
				return -1;
			}
			else if(edge1.weight > edge2.weight) {
				return 1;
			}
			return 0;
		}
	}
	
	static class Graph{
		int vertices;
		LinkedList<Edge>[] adjList;
		
		public Graph(int vertices) {
			this.vertices = vertices;
			adjList = new LinkedList[vertices];
			
			//init the adj list
			for(int i = 0; i<vertices; i++) {
				adjList[i] = new LinkedList<>();
			}
		}
		
		public void addEdge(int source, int destination, int weight) {
			Edge e = new Edge(destination, weight);
			adjList[source].addFirst(e);
		}
		
		public void dijkstraList(int start) {
			//array of size V for lengths of shortest paths
			int[] d = new int[vertices];

			//priority q
			PriorityQueue<Edge> q  = new PriorityQueue<Edge>(vertices, new Edge());
			
			//previous array
			Edge[] pi = new Edge[vertices];
			
			//Solution set
			boolean[] s = new boolean[vertices];
			//initialize all to default
			for(int i=0; i<vertices; i++) {
				s[i] = false;
				d[i] = Integer.MAX_VALUE;
				pi[i] = null;
			}
			pi[start] = new Edge(start, 0);
			d[start] = 0;
			Edge[] list = new Edge[vertices];
			// add to priority q and list of 
			for(int i=0; i<vertices; i++) {
				Edge e = new Edge(i, d[i]);
				q.add(e);
				list[i] = e;
				
			}
			
			while(q.size()>0) {
				 Edge tempp = q.poll();
				 int cur = tempp.destination;
				 s[cur] = true;
				 int x = 0;
				 
				 for(x = 0; x<adjList[cur].size(); x++) {
					 Edge temp = adjList[cur].get(x);
					 int vertex = temp.destination;
					 if((s[vertex] == false) && (d[vertex]>d[cur]+ temp.weight)) {
						 q.remove(list[vertex]);
						 d[vertex] = d[cur]+ temp.weight;
						 pi[vertex] = new Edge(cur, d[vertex]);
						 q.add(new Edge(vertex, d[vertex]));
					 }
				 }
				 
			}
			printDijkstra(d, pi, start);
		}
		
		public void printDijkstra(int[] d, Edge[] pi, int start) {
			for (int i = 0; i <vertices ; i++) {
				 System.out.println("Source Vertex: " + start + " to vertex " + + i +
				 " distance: " + d[i] + " previous: " + pi[i].destination);
			}
		}
	}
	
	
	
	
	public static void main(String[] args) {
		 int vertices = 9;
		 Graph graph = new Graph(vertices);
		 graph.addEdge(0, 1, 4);
		 graph.addEdge(0, 7, 8);
		 graph.addEdge(1, 2, 8);
		 graph.addEdge(1, 7, 11);
		 graph.addEdge(1, 0, 7);
		 graph.addEdge(2, 1, 8);
		 graph.addEdge(2, 3, 7);
		 graph.addEdge(2, 8, 2);
		 graph.addEdge(2, 5, 4);
		 graph.addEdge(3, 2, 7);
		 graph.addEdge(3, 4, 9);
		 graph.addEdge(3, 5, 14);
		 graph.addEdge(4, 3, 9);
		 graph.addEdge(4, 5, 10);
		 graph.addEdge(5, 4, 10);
		 graph.addEdge(5, 6, 2);
		 graph.addEdge(6, 5, 2);
		 graph.addEdge(6, 8, 6);
		 graph.addEdge(6, 7, 1);
		 graph.addEdge(7, 0, 8);
		 graph.addEdge(7, 1, 11);
		 graph.addEdge(7, 6, 1);
		 graph.addEdge(7, 8, 7);
		 graph.addEdge(8, 2, 2);
		 graph.addEdge(8, 6, 6);
		 graph.addEdge(8, 7, 1);
		 graph.dijkstraList(0);
	}

}
