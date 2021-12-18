import com.mathsystem.entity.graph.Color;
import com.mathsystem.graphapi.AbstractEdge;
import com.mathsystem.graphapi.AbstractGraph;
import com.mathsystem.graphapi.Vertex;
import com.mathsystem.graphapi.undirected.UndirectedGraph;
import com.mathsystem.plugin.GraphProperty;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

 public class GraphCenter implements GraphProperty {
    @Override
    public boolean execute(AbstractGraph undirectedGraph) {
        int N = undirectedGraph.getVertexCount();
        int d[][] = new int [N][N]; // Дистанции в графе
        int e[] = new int [N]; // Эксцентриситет вершин
        int rad = 10000000; // Радиус графа
        int diam; // Диаметр графа
        int c[] = new int[N];
        boolean res[]= new boolean[N];

        List<Vertex> verticess = undirectedGraph.getVertices();
        LinkedList<Integer> redVertex = new LinkedList<Integer>();
        for (int i = 0; i < N; i++) {
            if (verticess.get(i).getColor() == Color.red) {
                redVertex.add(i);
            }
        }
        BFS bfs = new BFS(undirectedGraph);
        for (int k = 0; k < N; k++) {
            bfs.BFS(k);
            d[k] = bfs.dst;

        }
// Нахождение эксцентриситета
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                e[i] = Math.max(e[i], d[i][j]);
            }
        }
// Нахождение диаметра и радиуса
        for (int i = 0; i < N; i++) {
            rad = Math.min(rad, e[i]);
        }
        for (int i = 0; i < N; i++) {
            if (e[i] == rad) {
                c[i]=i;
            }
            else
                c[i] = -1;
        }
        //for (int i = 0; i < N; i++) {
           // if ( c[i] != -1)
              // System.out.print(c[i]+" ");
       // }

        boolean flag1 = true;
        boolean flag = false;
        for (int i = 0;redVertex.size() != 0 ;i++) {
            flag = false;
            int l  = redVertex.poll();
            for(int j = 0;j < c.length;j++){
                if (c[j] != -1){
                    if(c[j] == l){
                        flag = true;
                    }
                }
            }
            if (flag == false){
                break;
            }
        }

        return flag;
    }
}