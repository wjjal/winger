package minispantree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import minispantree.Graph.Edge;

public class Kruskal {
	class Connection {
		Set<Integer> con;

		Connection(int pb, int pe) {
			con = new HashSet<Integer>();
			con.add(pb);
			con.add(pe);
		}
	}

	void miniSpanTree_KRUSKAL(Graph g, int vexnum) {

		List<Connection> con = new ArrayList<Connection>();
		List<Edge> edges = g.getEdges();
		List<Edge> result = new ArrayList<Edge>();
		while (result.size() != (vexnum-1)) {
			Edge temp = getLowcostEdge(edges);
			int pos = -1;
			boolean res = true;
			boolean push = true;
			boolean del = false;
			for (int i = 0; i < con.size(); i++) {
				Connection points = con.get(i);
				if (points.con.contains(temp.point_b)
						&& points.con.contains(temp.point_e)) {
					push = false;
					res = false;
					break;
				} else if (points.con.contains(temp.point_b)
						|| points.con.contains(temp.point_e)) {
					push = false;
					if (pos != -1) {
						con.get(pos).con.addAll(points.con);
						pos = i;
						del = true;
					} else {
						points.con.add(temp.point_b);
						points.con.add(temp.point_e);
						pos = i;
					}
				}
			}
			if(push)
				con.add(new Connection(temp.point_b,temp.point_e));
			if (del)
				con.remove(pos);
			if (res) {
				result.add(temp);
				System.out.print("(" + temp.point_b + "," + temp.point_e + ")");
			}
		}
	}

	private Edge getLowcostEdge(List<Edge> edges) {
		int min = Integer.MAX_VALUE;
		Edge e = null;
		for (Edge temp : edges) {
			if (temp.cost < min) {
				min = temp.cost;
				e = temp;
			}
		}
		edges.remove(e);
		return e;
	}
}
