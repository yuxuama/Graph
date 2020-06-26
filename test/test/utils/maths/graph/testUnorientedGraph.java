package test.utils.maths.graph;


import org.junit.Assert;
import org.junit.Test;
import utils.maths.graphs.Graph;
import utils.maths.graphs.UnorientedGraph;
import utils.maths.graphs.Link;
import utils.maths.graphs.Summit;
import utils.maths.graphs.exceptions.missingSummitException;
import utils.maths.graphs.exceptions.wrongSizeException;

public class testUnorientedGraph {

    public testUnorientedGraph(){}

    public void displayGraphMatrix(Graph graph){
        int[][] graphMatrix = graph.toAdjacentMatrix();

        for(int[] rows: graphMatrix){
            for(int numbers: rows){
                System.out.print(numbers);
                System.out.print("  ");
            }
            System.out.print("\n");
        }
    }

    public void displayGraphInLine(Graph graph){
        for(Summit s: graph.getChildsList()){
            System.out.print(s);
            System.out.print(" -> ");
            for(Link l: s.getChilds()){
                System.out.print(l.getLinkedTo());
                System.out.print(", ");
            }
            System.out.print("\n");
        }
    }

    @Test
    public void test() throws wrongSizeException, missingSummitException {
        UnorientedGraph test = new UnorientedGraph();
        testUnorientedGraph testg = new testUnorientedGraph();

        String[] names = new String[] {"Rouen", "Paris", "Bordeaux", "Lyon", "Brest"};
        int[][] adjacentMatrix = new int[][] {
                {0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0}
        };

        test.addAllSummit(names);
        test.defineLinkByMatrix(adjacentMatrix);

        //Definition tests

        Assert.assertArrayEquals(test.getChilds(), names);
        Assert.assertEquals(test.getLinkWeight("Rouen", "Paris"), 1);

        //Remove test

        test.removeLink("Rouen", "Paris");
        Assert.assertNull(test.getLink("Rouen", "Paris"));

        test.removeSummit("Rouen");
        Assert.assertNull(test.findOne("Rouen"));

        testg.displayGraphInLine(test);
        testg.displayGraphMatrix(test);
    }
}
