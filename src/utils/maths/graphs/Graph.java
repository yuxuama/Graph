package utils.maths.graphs;

import utils.maths.graphs.exceptions.missingSummitException;
import utils.maths.graphs.exceptions.wrongSizeException;

import java.util.ArrayList;
import java.util.List;

public abstract class Graph {

    protected List<Summit> childs;

    /** CONSTRUCTOR **/

    public Graph(){
        childs = new ArrayList<>();
    }

    /**SUMMIT MANAGEMENT**/

    public abstract void addSummit(Object value);

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

    /**LINK MANAGEMENT**/

    public abstract void addLink(Summit s1, Summit s2, int weight);

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

    public abstract void removeLink(Object sValue, Object sValue2);

    public abstract void removeAllLink(Summit summit);

    public void removeAllLink(Object sValue){
        Summit removed = findOne(sValue);

        removeAllLink(removed);
    }

    /** OTHERS METHODS **/

    public Summit findOne(Object value){
        for(Summit s: childs){
            if(s.getValue().equals(value)){
                return s;
            }
        }

        return null;
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
                matrix[i][childs.indexOf(child)] = l.getWeight();
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

    public boolean hasSummit(Summit summit){
        for(Summit s: childs){
            if(s == summit){
                return true;
            }
        }

        return false;
    }

    public boolean hasSummit(Object value){
        return hasSummit(findOne(value));
    }

    public int size(){
        return childs.size();
    }

    public void clear(){
        childs.clear();
    }

    /** GETTERS **/

    public Object[] getChilds(){
        Object[] childs = new Object[this.childs.size()];
        for(int i = 0; i<this.childs.size(); i++){
            childs[i] = this.childs.get(i).getValue();
        }
        return childs;
    }

    public List<Summit> getChildsList(){
        return childs;
    }
}
