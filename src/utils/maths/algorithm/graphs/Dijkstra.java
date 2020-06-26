package utils.maths.algorithm.graphs;


import utils.maths.graphs.Link;
import utils.maths.graphs.OrientedGraph;
import utils.maths.graphs.Summit;
import utils.maths.graphs.UnorientedGraph;

import java.util.*;

public class Dijkstra {

    private UnorientedGraph graph;
    private OrientedGraph subgraph;
    private Set<Summit> alreadyTested;
    private Set<Summit> visited;

    public Dijkstra(UnorientedGraph graph){
        this.graph = graph;
        this.alreadyTested = new HashSet<>();
        this.visited = new HashSet<>();
        this.subgraph = new OrientedGraph();

    }

    public void compute(Object start, Object end){
        subgraph.addSummit(start);

        boolean running = true;
        Object current = start;
        while (running){
            System.out.println(current);
            current = minPeriphSummit(current);

            if(current == end){
                running = false;
            }
        }

        System.out.println("Route found !");

        Summit summit = subgraph.findOne(current);
        List<Object> way = new ArrayList<>();

        while(summit.getValue() != start){
            System.out.println(summit);
            System.out.println(Arrays.toString(summit.getParents()));
            way.add(summit.getValue());
            break;
        }

        /*for (int i = way.size() - 1; i >= 0 ; i--) {
            System.out.print(way.get(i));
            System.out.print("  ->  ");
        }*/

        System.out.println(Arrays.toString(subgraph.getChilds()));
        System.out.println(Arrays.deepToString(subgraph.toAdjacentMatrix()));
    }

    private void updateState(Object miniSummitValue,  Object parentSummit){
        Summit miniSummit = graph.findOne(miniSummitValue);
        if(alreadyTested.contains(miniSummit)){
            Object parent = findParent(miniSummit);
            subgraph.addSummit(miniSummitValue);
            subgraph.addLink(parent, miniSummitValue);
            alreadyTested.remove(miniSummit);
        } else {
            subgraph.addSummit(miniSummitValue);
            subgraph.addLink(miniSummitValue, parentSummit);
        }
    }

    private Object findParent(Summit miniSummit) {
        for (Link link: miniSummit.getChilds()) {
            Object sValue = link.getLinkedTo();
            if(subgraph.hasSummit(sValue)){
                return sValue;
            }
        }

        return null;
    }

    public Object minPeriphSummit(Object current){
        Summit mainGraphSummit = graph.findOne(current); //Current pos in main graph

        double mini = Double.POSITIVE_INFINITY;    //Var for storage and find the minimum distance
        Object result = current;

        //Testing for alreadyTested
        for (Summit s : alreadyTested) {
            if (s.getSubvalue() < mini) {
                mini = s.getSubvalue();
                result = s.getValue();
            }
        }

        //Testing for the neighbourhood of the current summit
        for(Link link: mainGraphSummit.getChilds()){
            if(!subgraph.hasSummit(link.getLinkedTo().getValue())){ // Comparing only the values of the summit and not the pointer (This allows to make a link between the two graph)
                                                                    // This line ensure that we don't analyse a summit two times
                int totalDistance = link.getWeight() + mainGraphSummit.getSubvalue(); //Distance from the starting point
                if( totalDistance < mini){
                    mini = totalDistance;
                    result = link.getLinkedTo().getValue();
                } else {
                    Summit s = link.getLinkedTo();
                    s.setSubvalue(totalDistance);
                    alreadyTested.add(s);
                }
            }
        }

        updateState(result, current);

        return result;
    }


    //for the test
    public Set<Summit> getAlreadyTested() {
        return alreadyTested;
    }
}
