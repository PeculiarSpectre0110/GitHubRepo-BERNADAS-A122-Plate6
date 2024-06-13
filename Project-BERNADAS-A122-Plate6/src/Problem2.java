import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem2 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the number of vertices: ");
		int numVertices = scanner.nextInt();
		int[][] adjacencyMatrix = new int[numVertices][numVertices];

		System.out.println("Enter the adjacency matrix:");
		for (int i = 0; i < numVertices; i++) {
			for (int j = 0; j < numVertices; j++) {
				adjacencyMatrix[i][j] = scanner.nextInt();
			}
		}

		Map<String, Integer> edgeCounts = new HashMap<>();
		for (int i = 0; i < numVertices; i++) {
			for (int j = i + 1; j < numVertices; j++) {
				if (adjacencyMatrix[i][j] > 0) {
					String edge = (i + 1) + "-" + (j + 1);
					edgeCounts.put(edge, adjacencyMatrix[i][j]);
				}
			}
		}

		System.out.println("Edges and their counts:");
		for (Map.Entry<String, Integer> entry : edgeCounts.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}

	}

}