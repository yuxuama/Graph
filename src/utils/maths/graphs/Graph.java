package utils.maths.graphs;

import utils.maths.graphs.exceptions.missingSummitException;
import utils.maths.graphs.exceptions.wrongSizeException;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private List<Summit> childs;

    public Graph(){
        childs = new ArrayList<>();
    }

    /** ADDERS AND REMOVERS
     * **/

    public void addSummit(Object value){
        Summit s = new Summit(value);
        childs.add(s);
    }

    public void addAllSummit(Object[] values){
        for(Object value: values){
            addSummit(value);
        }
    }

    public void removeSummit(Summit summit){
        removeAllLink(summit);
        childs.remove(summit);
    }

    public void removeSummit(Object value){
        Summit removed = findOne(value);
        removeSummit(removed);
    }

    public void removeAllSummit(Object[] values){
        for(Object o: values){
            removeSummit(o);
        }
    }

    public void addLink(Summit s1, Summit s2, int weight){
        if(!s1.hasChild(s2) && !s2.hasChild(s1)){
            s1.addChildLink(s2, weight);
            s2.addChildLink(s1, weight);
        }
    }

    public void addLink(Object sValue, Object sValue2,  int weight){
        Summit s1 = findOne(sValue);
        Summit s2 = findOne(sValue2);

        addLink(s2, s2, weight);
    }

    public void addLink(Object sValue, Object sValue2){
        addLink(sValue, sValue2, 1);
    }

    public void addAlllink(Object sValue, Object[] sValues, int[] weight){
        for(int i = 0; i<sValues.length; i++){
            addLink(sValue, sValues[i], weight[i]);
        }
    }

    public void removeLink(Object sValue, Object sValue2){
        Summit s1 = findOne(sValue);
        Summit s2 = findOne(sValue2);

        if(s1.hasChild(s2) && s2.hasChild(s1) && !s1.equals(s2)){
            s1.removeChildLink(s2);
            s2.removeChildLink(s1);
        }
    }

    public void removeAllLink(Summit summit){
        for(Summit s: childs){
            if(s.hasChild(summit)){
                s.removeChildLink(summit);
            }
        }

        summit.removeAllChild();
    }

    public void removeAllLink(Object sValue){
        Summit removed = findOne(sValue);

        removeAllLink(removed);
    }

    /** METHODS
     * **/

    public Summit findOne(Object value){
        for(Summit s: childs){
            if(s.getValue().equals(value)){
                return s;
            }
        }

        throw new NullPointerException();
    }

    public boolean hasSummit(Object value){
        for(Summit s: childs){
            if(s.getValue() == value){
                return true;
            }
        }

        return false;
    }

    public void clear(){
        childs.clear();
    }

    public int[][] toAdjacentMatrix(){
        int size = childs.size();
        int[][] matrix = new int[size][size];

        //filling with zeros for coming update
        for(int i = 0; i<size; i++){
            for(int j = 0; j<size; j++){
                matrix[i][j] = 0;
            }
        }

        //processing link
        for(int i = 0; i<size; i++){
            Summit s = childs.get(i);
            Link[] sLink = s.getChilds();
            for(Link l: sLink){
                Summit child = l.getLinkedTo();
                matrix[childs.indexOf(child)][i] = l.getWeight();
            }
        }

        return matrix;
    }

    public void defineLinkByMatrix(int[][] matrix) throws missingSummitException, wrongSizeException {
        int childSize = childs.size();
        if(childSize == 0){
            throw new missingSummitException("Summits are missing, can't define link");
        }
        if(childSize != matrix.length){
            throw new wrongSizeException("Wrong matrix size according to summit number");
        }

        for(int i = 0; i<childSize; i++){
            int[] row = matrix[i];
            for(int j = 0; j<childSize; j++){
                if(row[j] != 0){
                    addLink(childs.get(i), childs.get(j), row[j]);
                }
            }
        }
    }

    /** GETTERS
     * **/

    public Link getLink(Summit summit, Summit summit2){
        for(Link l: summit.getChilds()){
            if(l.getLinkedTo().equals(summit2)){
                return l;
            }
        }

        return null;
    }

    public Link getLink(Object sValue, Object sValue2){
        Summit s1 = findOne(sValue);
        Summit s2 = findOne(sValue2);

        return getLink(s1, s2);
    }

    public int getLinkWeight(Object sValue, Object sValue2){
        Link link = getLink(sValue, sValue2);
        if(link != null){
            return link.getWeight();
        } else{
            return -2;
        }
    }

    public List<Summit> getChilds(){
        return childs;
    }
}
