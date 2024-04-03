package Graph;

import java.util.*;

public class Graph {

    /*
        用adjacency matrix来实现
        default edge weight is 1
    */
    private int[][] edges;
    private ArrayList<GrapthNode> vertices;
    private int numberEdges;
    private boolean[] visited;

    // how many vertices in the graph
    public Graph(int n){
        this.edges = new int[n][n];
        vertices = new ArrayList<>(n);
        numberEdges = 0;
    }
    
    // get all vertices
    public int getVertices(){
        return this.vertices.size();
    }

    public void printVertices(){
        for (GrapthNode node: vertices
             ) {
            System.out.println(node);
        }
    }

    // get all edges
    public int getEdges(){
        return this.numberEdges;
    }

    // update the array list
    public void addVertex(GrapthNode node){
        vertices.add(node);
    }

    public GrapthNode deleteEdgeByNode(GrapthNode node){
        int index = node.getNum() - 1;
        // use index to remove, otherwise not valid
        if (index == -1) {
            return null;
        }
        node.setDeleted(true);
        // delete edges
        for (int i = 0; i < getVertices(); i++) {
            if (edges[index][i] != 0){
                edges[index][i] = 0;
                edges[i][index] = 0;
                numberEdges--;
            }
        }
//        System.out.println("vertex left: " + getVertices());
//        int[][] newEdges = new int[newSize][newSize];
//        for (int i = 0; i < getVertices(); i++) {
//            for (int j = 0; j < getVertices(); j++) {
//                if (!vertices.get(j).isDeleted() || edges[i][j] == 1) {
//                    newEdges[i][j] = edges[i][j];
//                }
//            }
//        }
//        vertices.remove(index);
        return node;
    }

    // matrix need to update the corresponding edges
    public void addEdge(int v1, int v2){
        edges[v1][v2] = 1;
        edges[v2][v1] = 1;
        numberEdges++;
    }

    public void deleteEdge(int v1, int v2){
        edges[v1][v2] = 0;
        edges[v2][v1] = 0;
        numberEdges--;
    }

    //DFS
    public void dfs(int src){
        visited = new boolean[getVertices()];
        dfsHelper(src, this.visited);
    }

    /**
     *
     * @param src the first index of the searching
     * @param visited a boole an array to track all nodes if visited, default are false
     */
    public void dfsHelper(int src, boolean[] visited){
        if (visited[src]){
            return;
        }
        else {
            visited[src] = true;
        }
        System.out.println("Node visited: " + vertices.get(src).getName());
        for (int i = 0; i < edges.length; i++) { // check every neighbor
            if (!(edges[src][i] == 0)){
                dfsHelper(i, visited);
            }
        }

    }

    //DFS Stack
    public void dfsStack(int src){
        visited = new boolean[getVertices()];
        dfsStackHelper(src, visited);
    }

    public void dfsStackHelper(int src, boolean[] visited){
        Stack<Integer> stack = new Stack<>();
        stack.push(src);
        while (!stack.isEmpty()){
            int curr = stack.pop();
            if (!visited[curr]){
                visited[curr] = true;
                System.out.println("Node visited: " + vertices.get(curr).getName());
                for (int i = 0; i < getVertices(); i++) {
                    if (edges[curr][i] != 0 && !visited[i]){
                        stack.push(i);
                    }
                }
            }
        }
    }

    // BFS
    public boolean bfs(int src){
        visited = new boolean[getVertices()];
        return bfsHelper(src, visited);
    }

    /**
     *
     * @param src the first index of the searching
     * @param visited a boole an array to track all nodes if visited, default are false
     */
    public boolean bfsHelper(int src, boolean[] visited){
        // Test Bipartite
        boolean ifTrue = true;
        int[] color = new int[getVertices()];
        // no color
        Arrays.fill(color, -1);
        // 1 is red, 0 is blue

        Queue<Integer> queue = new LinkedList<>();
        queue.add(src);
        visited[src] = true;
        // color to red
        color[src] = 1;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            System.out.println("Node visited: " + vertices.get(curr).getName());
            System.out.println("Node color: " + color[curr]);
            // check all edges (neighbors)
            for (int i = 0; i < getVertices(); i++) {
                if (edges[curr][i] != 0 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;

                }
            }
            // check bipartite
            if (ifTrue) {
                for (int i = 0; i < getVertices(); i++) {
                    if (edges[curr][i] == 1) {
                        if (color[i] == -1) {
                            // color it contrast to the current node's color
                            color[i] = 1 - color[curr];
                        } else if (color[i] == color[curr]) {
                            ifTrue = false;
                            break;
                        }
                    }
                }
            }
        }
        return ifTrue;
    }
    
    // show the adjacency matrix
    public void show(String sentence){
        System.out.println(sentence);
        System.out.print("  ");

        for (GrapthNode vertex : vertices) {
            System.out.print(vertex.getName() + " ");
        }
        System.out.println();
        for(int i =0; i < getVertices(); i++){
            System.out.print(vertices.get(i).getName() + " ");
            for (int j = 0; j < getVertices(); j++) {
                if (!vertices.get(i).isDeleted() || edges[i][j] == 1) {
                    System.out.print(edges[i][j] + " ");
                }
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        Graph myGraph = new Graph(5);
        myGraph.addVertex(new GrapthNode("A", 1));
        myGraph.addVertex(new GrapthNode("B", 2));
        myGraph.addVertex(new GrapthNode("C", 3));
        myGraph.addVertex(new GrapthNode("D", 4));
        myGraph.addVertex(new GrapthNode("E", 5));

        myGraph.addEdge(0, 1);
        myGraph.addEdge(0, 2);
        myGraph.addEdge(1, 2);
        myGraph.addEdge(1, 3);
        myGraph.addEdge(1, 4);
        myGraph.show("Initial graph");

//        myGraph.deleteEdge(1, 0);
        myGraph.deleteEdge(1, 2);
//        myGraph.deleteEdge(4, 1);
//
        System.out.println();
        myGraph.show("After deleting edges");
        System.out.println();
//
//        GrapthNode node = new GrapthNode("E", 5);
//        GrapthNode node1 = new GrapthNode("B", 2);
//
//
//        myGraph.deleteEdgeByNode(node);
//        myGraph.show("After deleting node " + node.getName());
//        System.out.println();
//
//        myGraph.deleteEdgeByNode(node1);
//        myGraph.show("After deleting node: " + node1.getName());

//        myGraph.show("After deleting nodes");

//        myGraph.deleteNode(node1);
//        System.out.println("Edges left: ");
//        System.out.println(myGraph.getEdges());
//        myGraph.show("After deleting nodes");
//        System.out.println("DFS using recursion");
//        myGraph.dfs(0);
//        System.out.println();
//        myGraph.dfs(3);
//        System.out.println();
//        myGraph.dfs(4);
//
//        System.out.println();
//
//        System.out.println("DFS using stack");
//        myGraph.dfsStack(0);
//        System.out.println();
//        myGraph.dfsStack(3);
//        System.out.println();
//        myGraph.dfsStack(4);

        System.out.println();
        System.out.println("BFS using queue");
        boolean iftrue1 = myGraph.bfs(0);
        System.out.println("If it's bipartite: " + iftrue1);
        boolean iftrue2 = myGraph.bfs(3);
        System.out.println("If it's bipartite: " + iftrue2);
        boolean iftrue3 = myGraph.bfs(4);
        System.out.println("If it's bipartite: " + iftrue3);
    }
}
