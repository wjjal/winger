package minispantree;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	List<Edge> edges;

	public List<Edge> getEdges() {
		return edges;
	}

	Graph() {
		edges = new ArrayList<Edge>();
	}

	class Edge {
		int point_b;
		int point_e;
		int cost;

		Edge(int pb, int pe, int value) {
			point_b = pb;
			point_e = pe;
			cost = value;
		}
	}

	void add(int[][] edge) {
		for(int i = 0;i<edge.length;i++)
		edges.add(new Edge(edge[i][0],edge[i][1],edge[i][2]));
	}
}
