import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problems1_3_5 {

	private int V;
	private List<List<Integer>> adj;

	Problems1_3_5(int v) {
		V = v;
		adj = new ArrayList<>();
		for (int i = 0; i < v; i++) {
			adj.add(new ArrayList<>());
		}
	}

	void addEdge(int u, int v) {
		adj.get(u - 1).add(v - 1);
	}

	void DFS(int v, boolean[] visited, int[] color) {
		visited[v] = true;
		for (int w : adj.get(v)) {
			if (!visited[w]) {
				color[w] = 1 - color[v]; // assign opposite color
				DFS(w, visited, color);
			} else if (color[w] == color[v]) {
				// if adjacent vertices have the same color, graph is not bipartite
				throw new RuntimeException("Graph is not bipartite");
			}
		}
	}

	boolean isBipartite() {
		boolean[] visited = new boolean[V];
		int[] color = new int[V]; // 0 and 1 represent two colors
		for (int v = 0; v < V; v++) {
			if (!visited[v]) {
				try {
					DFS(v, visited, color);
				} catch (RuntimeException e) {
					return false;
				}
			}
		}
		return true;
	}

	void DFS(int v, boolean[] visited) {
		visited[v] = true;
		for (int w : adj.get(v)) {
			if (!visited[w]) {
				DFS(w, visited);
			}
		}
		visited[v] = true; // mark v as visited again
	}

	boolean DFS(int v, boolean[] visited, boolean[] recStack) {
		visited[v] = true;
		recStack[v] = true;
		for (int w : adj.get(v)) {
			if (!visited[w]) {
				if (DFS(w, visited, recStack)) {
					return true;
				}
			} else if (recStack[w]) {
				return true;
			}
		}
		recStack[v] = false;
		return false;
	}

	int numProblem1() {
		boolean[] visited = new boolean[V];
		int count = 0;
		for (int v = 0; v < V; v++) {
			if (!visited[v]) {
				DFS(v, visited);
				count++;
			}
		}
		return count;
	}

	boolean isCycle() {
		boolean[] visited = new boolean[V];
		boolean[] recStack = new boolean[V];
		for (int v = 0; v < V; v++) {
			if (!visited[v]) {
				if (DFS(v, visited, recStack)) {
					return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the number of vertices: ");
		int numVertices = scanner.nextInt();
		Problems1_3_5 g = new Problems1_3_5(numVertices);
		System.out.print("Enter the number of edges: ");
		int e = scanner.nextInt();
		System.out.println("Enter the edges (u, v): ");
		System.out.println("Note: rows is based on the number of edges");
		System.out.println("Example: 1 2");
		System.out.println("         3 4");
		for (int i = 0; i < e; i++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			g.addEdge(u, v);
		}
		scanner.close();
		if (g.numProblem1() > 1) {
			System.out.println("The graph is not connected.");
			System.out.println("Number of connected components: " + g.numProblem1());
		} else {
			System.out.println("The graph is connected.");
		}
		if (g.isCycle()) {
			System.out.println("The graph contains a cycle.");
		} else {
			System.out.println("The graph does not contain a cycle.");
		}
		if (g.isBipartite()) {
			System.out.println("The graph is bipartite.");
		} else {
			System.out.println("The graph is not bipartite.");
		}
	}
}