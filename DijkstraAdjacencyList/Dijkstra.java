package helloWorld;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Random;

public class Dijkstra {
	
	static class MinHeap{
		private Edge[] Heap;
		private int size;
		private int maxsize;
		
		private static final int FRONT = 1;
		//constructor
		public MinHeap(int maxsize) {
			this.maxsize = maxsize;
			this.size = 0;
			Heap = new Edge[maxsize + 1];
			Heap[0] = new Edge(Integer.MIN_VALUE, Integer.MIN_VALUE);
	  
		}
		
		//accessor functions
		public int parent(int pos) {
			return pos/2;
		}
		public int leftChild(int pos) {
			return pos*2;
		}
		
		public int rightChild(int pos) {
			return pos*2 +1;
		}
		public boolean isLeaf(int pos) {
			if(pos > (size/2)) {
				return true;
			}
			return false;
		}
		//method functions
		public void swap(int pos1, int pos2) {
			Edge temp;
			temp = Heap[pos1];
			Heap[pos1] = Heap[pos2];
			Heap[pos2] = temp;
		}
		public void insert(Edge element) {
			if(size>=maxsize) {
				return;
			}
			
			Heap[++size] = element;
			int current = size;
			while(Heap[current].weight < Heap[parent(current)].weight ) {
				swap(current, parent(current));
				current = parent(current);
			}
		}
		//For distance values that are updated, change the values
		//and swap up
		public void inserted(int pos, int distance) {
			int i;
			for(i=1; i<=size; i++) {
				if(Heap[i].destination == pos) {
					break;
				}
			}
			Heap[i].weight = distance;
			while(Heap[i].weight < Heap[parent(i)].weight ) {
				swap(i, parent(i));
				i = parent(i);
			}
		}
		//get the highest priority edge
		public Edge pop(){
			
			Edge pop = Heap[FRONT];
			Heap[FRONT] = Heap[size--];
			fixHeap(FRONT);
			return pop;
		}
		
		public void fixHeap(int pos) {
			if(!isLeaf(pos)) {
				//find the smaller subtree
				int smallerSubHeap = leftChild(pos);
				if(rightChild(pos)<=size) {
					if(Heap[leftChild(pos)].weight > Heap[rightChild(pos)].weight){
						smallerSubHeap = rightChild(pos);
					}
				}
				// if root is bigger than subtree, swap it with smallest
				if(Heap[pos].weight>Heap[leftChild(pos)].weight || Heap[pos].weight> Heap[rightChild(pos)].weight) {
					swap(pos, smallerSubHeap);
					fixHeap(smallerSubHeap);
				}
				
				if(Heap[pos].weight==Heap[rightChild(pos)].weight) {
					swap(pos, rightChild(pos));
					fixHeap(pos*2+1);
				}
			}
		}
		
	}
	
	static class Edge{
		int destination;
		int weight;
		
		//constructor
		public Edge(int destination, int weight) {
			this.destination = destination;
			this.weight = weight;
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

			//priority q min heap
			MinHeap q =  new MinHeap(vertices);
			
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
			
			// add to priority q
			for(int i=0; i<vertices; i++) {
				Edge e = new Edge(i, d[i]);
				q.insert(e);
			}
			
			while(q.size>0) {
				 Edge tempp = q.pop();
				 int cur = tempp.destination;
				 s[cur] = true;
				 int x = 0;
				 
				 for(x = 0; x<adjList[cur].size(); x++) {
					 Edge temp = adjList[cur].get(x);
					 int vertex = temp.destination;
					 if((s[vertex] == false) && (d[vertex]>d[cur]+ temp.weight)) {
						 d[vertex] = d[cur]+ temp.weight;
						 pi[vertex] = new Edge(cur, d[vertex]);
						 q.inserted(vertex, d[vertex]);
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
		
		//adding the random edges based on the vertices
		public void randEdges(int vertices, int edges) {
			Random rand = new Random();
			//ensure the graph is connected with V-1 edges
			for(int i = 0; i<vertices-1; i++) {
				if(i!=vertices-1) {
					addEdge(i, i+1, rand.nextInt(1, 10));
				}
				else {
					addEdge(i, 0, rand.nextInt(1, 10));
				}
				
			}
			//generate random edges, assuming no repeats
			for(int i=0; i<(edges-vertices+1); i++) {
				int equals, first, second;
				do{
					equals = 0;
					first = rand.nextInt(0, vertices);
					second = rand.nextInt(0, vertices);
					if(first == second) {
						equals = 1;
					}
					for(int x = 0; x<adjList[first].size(); x++) {
						if(adjList[first].get(x).destination == second) {
							equals = 1;
						}
					}
					for(int x = 0; x<adjList[second].size(); x++) {
						if(adjList[second].get(x).destination == first) {
							equals = 1;
						}
					}
				}while(equals == 1);
				addEdge(first, second, rand.nextInt(1, 10));	
			}
			
		}
	}
	
	
	
	
	public static void main(String[] args) {
		int vertices = 1000;
		Graph graph = new Graph(vertices);
		graph.randEdges(vertices, 999);
		long startTime = System.currentTimeMillis();
		graph.dijkstraList(0);
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("Time in ms: " + elapsedTime );
	}

}
