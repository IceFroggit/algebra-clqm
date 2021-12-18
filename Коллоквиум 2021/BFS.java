import com.mathsystem.entity.graph.Edge;
import com.mathsystem.entity.graph.Graph;
import com.mathsystem.graphapi.AbstractEdge;
import com.mathsystem.graphapi.AbstractGraph;
import com.mathsystem.graphapi.Vertex;
import com.mathsystem.graphapi.directed.DirectedGraph;
import com.mathsystem.graphapi.undirected.UndirectedGraph;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.*;

public class BFS {
    int s;
    AbstractGraph abstractGraph;
    int res[] = null;
    int[] dst = null;
    public BFS(AbstractGraph abstractGraph) {
        this.s = s;
        this.abstractGraph = abstractGraph;
        this.res = new int [abstractGraph.getVertexCount()];
        this.dst = new int [abstractGraph.getVertexCount()];
    }
    void BFS(int s) {
        int V = abstractGraph.getVertexCount();
        boolean visited[] = new boolean[V];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        visited[s] = true;
        List<Vertex> verticess = abstractGraph.getVertices();
        LinkedList[] adj = new LinkedList[V];
        for (int i = 0; i < V; ++i) {
            adj[i] = new LinkedList();
        }
        //создание списка смежности
        for (int i = 0; i < V; ++i) {
            int num = verticess.get(i).getEdgeList().size();
            for (int j = 0; j < num; j++) {
                if (verticess.get(i).getIndex() != verticess.get(i).getEdgeList().get(j).getW().getIndex())
                    adj[i].add(verticess.get(i).getEdgeList().get(j).getW().getIndex());
                else {
                    adj[i].add(verticess.get(i).getEdgeList().get(j).getV().getIndex());

                }
            }
        }
        queue.add(s);
        int k = 0;
        int[] dst = new int[abstractGraph.getVertexCount()];
        dst[0] = 0;
        while (queue.size() != 0) {
            s = queue.poll();
           // System.out.print(s + " ");
            res[k] = s;
            k++;
            Iterator<Integer> j = adj[s].listIterator();
            while (j.hasNext()) {
                int n = j.next();
                if (!visited[n]) {
                    visited[n] = true;
                    dst[n] = dst[s] + 1;
                    queue.add(n);
                }
            }
        }
        this.dst = dst;
    }
    int[] GetDist(){
        return dst;
    }
}




