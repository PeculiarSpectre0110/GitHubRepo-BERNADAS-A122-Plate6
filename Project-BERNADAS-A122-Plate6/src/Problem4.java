import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem4 {
	private int V;
	private List<List<Integer>> adj;

	Problem4(int v) {
		V = v;
		adj = new ArrayList<>();
		for (int i = 0; i < v; i++) {
			adj.add(new ArrayList<>());
		}
	}

	void addEdge(int u, int v) {
		adj.get(u - 1).add(v - 1);
		adj.get(v - 1).add(u - 1); // for undirected graph
	}

	int getDegree(int v) {
		return adj.get(v - 1).size();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the number of vertices: ");
		int numVertices = scanner.nextInt();
		Problem4 graph = new Problem4(numVertices);
		System.out.print("Enter the number of edges: ");
		int e = scanner.nextInt();
		System.out.println("Enter the edges (u, v):");
		for (int i = 0; i < e; i++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			graph.addEdge(u, v);
		}
		System.out.println("Enter the vertex to get its degree: ");
		int vertex = scanner.nextInt();
		System.out.println("Degree of vertex " + vertex + ": " + graph.getDegree(vertex));
		scanner.close();
	}
}