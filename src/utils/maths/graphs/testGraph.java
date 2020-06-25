package utils.maths.graphs;


import utils.maths.graphs.exceptions.missingSummitException;
import utils.maths.graphs.exceptions.wrongSizeException;

public class testGraph {

    private Graph graph;

    public testGraph(Graph graph){
        this.graph = graph;
    }

    public void displayGraphMatrix(){
        int[][] graphMatrix = graph.toAdjacentMatrix();

        for(int[] rows: graphMatrix){
            for(int numbers: rows){
                System.out.print(numbers);
                System.out.print("  ");
            }
            System.out.print("\n");
        }
    }

    public void displayGraphInLine(){
        for(Summit s: graph.getChilds()){
            System.out.print(s);
            System.out.print(" -> ");
            for(Link l: s.getChilds()){
                System.out.print(l.getLinkedTo());
                System.out.print(", ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) throws wrongSizeException, missingSummitException {
        Graph test = new Graph();
        testGraph testg = new testGraph(test);

        String[] names = new String[] {"Rouen", "Paris", "Bordeaux", "Lyon", "Brest"};
        int[][] adjacentMatrix = new int[][] {
                {0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0}
        };

        test.addAllSummit(names);
        test.defineLinkByMatrix(adjacentMatrix);

        System.out.println(test.getLinkWeight("Rouen", "Paris"));

        testg.displayGraphInLine();
        testg.displayGraphMatrix();
    }
}
