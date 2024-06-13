import java.util.*;

public class Problem8 {

    static class Graph {
        int V;
        List<List<Integer>> adj;

        Graph(int v) {
            V = v;
            adj = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v) {
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean isNotIsomorphic(Graph g, int[] mapping) {
            for (int i = 0; i < V; i++) {
                List<Integer> adj1 = adj.get(i);
                List<Integer> adj2 = g.adj.get(mapping[i]);
                if (adj1.size()!= adj2.size()) {
                    return true;
                }
                for (int j = 0; j < adj1.size(); j++) {
                    if (!contains(adj2, mapping[adj1.get(j)])) {
                        return true;
                    }
                }
            }
            return false;
        }

        boolean contains(List<Integer> list, int val) {
            for (int i : list) {
                if (i == val) {
                    return true;
                }
            }
            return false;
        }
    }

    static boolean areNotIsomorphic(Graph g1, Graph g2) {
        if (g1.V!= g2.V) {
            return true;
        }
        int[] mapping = new int[g1.V];
        return areNotIsomorphicUtil(g1, g2, mapping, 0);
    }

    static boolean areNotIsomorphicUtil(Graph g1, Graph g2, int[] mapping, int index) {
        if (index == g1.V) {
            return g1.isNotIsomorphic(g2, mapping);
        }
        for (int i = 0; i < g2.V; i++) {
            if (isValid(g1, g2, mapping, index, i)) {
                mapping[index] = i;
                if (!areNotIsomorphicUtil(g1, g2, mapping, index + 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isValid(Graph g1, Graph g2, int[] mapping, int index, int val) {
        for (int i = 0; i < index; i++) {
            if (mapping[i] == val) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of vertices in graph 1: ");
        int v1 = scanner.nextInt();
        Graph g1 = new Graph(v1);
        System.out.print("Enter the number of edges in graph 1: ");
        int e1 = scanner.nextInt();
        System.out.println("Enter the edges in graph 1 (u, v):");
        for (int i = 0; i < e1; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            g1.addEdge(u - 1, v - 1);
        }

        System.out.print("Enter the number of vertices in graph 2: ");
        int v2 = scanner.nextInt();
        Graph g2 = new Graph(v2);
        System.out.print("Enter the number of edges in graph 2: ");
        int e2 = scanner.nextInt();
        System.out.println("Enter the edges in graph 2 (u, v):");
        for (int i = 0; i < e2; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            g2.addEdge(u - 1, v - 1);
        }

        if (areNotIsomorphic(g1, g2)) {
            System.out.println("The graphs are not isomorphic.");
        } else {
            System.out.println("The graphs are isomorphic.");
        }
        scanner.close();
    }
}