package utils.maths.algorithm.graphs;


import utils.maths.graphs.*;

import java.util.*;

public class Dijkstra {

    private UnorientedGraph graph;
    private OrientedGraph subgraph;
    private Set<Summit> visited;

    public Dijkstra(UnorientedGraph graph){
        this.graph = graph;
        this.visited = new HashSet<>();
        this.subgraph = new OrientedGraph();

    }

    public void compute(Object start, Object end){
        subgraph.addSummit(start);
        visited.add(subgraph.findOne(start));

        boolean running = true;
        Object current = start;
        while (running){
            System.out.println(current);
            current = minPeriphSummit(current);

            if(current == end){
                running = false;
            }
        }

        List<Object> way = new ArrayList<>();
        way.add(end);
        Summit endSummit = subgraph.findOne(end);
        int lengthRoute = endSummit.getSubvalue();

        while(endSummit.getParents().length != 0){
            way.add(endSummit.getParents()[0]
                    .getLinkedTo()
                    .getValue());
            endSummit = endSummit.getParents()[0].getLinkedTo();
        }

        System.out.println("This is the shortest route from summit " + start + " to summit " + end + " :");
        for(int i = way.size() - 1; i >= 0; i--){
            System.out.print(way.get(i));
            if(i != 0){
                System.out.print(" -> ");
            }else System.out.print("\n");
        }
        System.out.println("The route is " + (lengthRoute + 1)+ " long");
    }

    private void changeState(Object sValue, int subValue, Object parent){
        if(!subgraph.hasSummit(sValue)){
            subgraph.addSummit(sValue);
        }
        Summit created = subgraph.findOne(sValue);

        if(created.getSubvalue() > subValue || created.getSubvalue() == -1){
            created.setSubvalue(subValue);
            created.removeAllParent();
            subgraph.addLink(parent, sValue);
        }
    }

    public Object minPeriphSummit(Object current){
        Summit mainGraphSummit = graph.findOne(current); //Current pos in main graph
        Summit subgraphSummit = subgraph.findOne(current);

        double mini = Double.POSITIVE_INFINITY;    //Var for storage and find the minimum distance
        Object result = current;

        //Testing for alreadyTested
        for (Summit s : subgraph.getChildsList()) {
            if (!visited.contains(s) && s.getSubvalue() < mini) {
                mini = s.getSubvalue();
                result = s.getValue();
            }
        }

        //Testing for the neighbourhood of the current summit
        for(Link link: mainGraphSummit.getChilds()){
            Object lValue = link.getLinkedTo().getValue();
            if(!visited.contains(subgraph.findOne(lValue))){ // Comparing only the values of the summit and not the pointer (This allows to make a link between the two graph)
                                                                    // This line ensure that we don't analyse a summit two times
                int totalDistance = link.getWeight() + subgraphSummit.getSubvalue(); //Distance from the starting point
                if( totalDistance < mini) {
                    mini = totalDistance;
                    result = lValue;
                }
                changeState(lValue, totalDistance, current);
            }
        }

        visited.add(subgraph.findOne(result));

        return result;
    }

    private void displayGraphInLine(Graph graph){
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
}
