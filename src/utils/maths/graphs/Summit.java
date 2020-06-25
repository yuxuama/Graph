package utils.maths.graphs;

import java.util.ArrayList;
import java.util.List;

public class Summit {

    private Object value;
    private List<Link> childs;

    /** Constructor
    **/

    public Summit(Object value){
        this.value = value;
        this.childs = new ArrayList<>();
    }

    /** METHODS
     * */

    public void addChildLink(Summit child, int weight){
        Link link = new Link(child, weight);
        childs.add(link);
    }

    public void removeChildLink(Summit child){
        Link removed = foundChild(child);
        if(removed != null){
            childs.remove(removed);
        } else {
            throw new NullPointerException("Link does not exist");
        }
    }

    public void removeAllChild(){
        childs.clear();
    }

    public boolean hasChild(Summit tested){
        return foundChild(tested) != null;
    }

    private Link foundChild(Summit searched){
        for(Link link: childs){
            if(link.getLinkedTo() == searched){
                return link;
            }
        }

        return null;
    }

   /** GETTERS AND SETTERS
   * */

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Link[] getChilds() {
        Link[] result = new Link[childs.size()];
        for(int i = 0; i < childs.size(); i++){
            result[i] = childs.get(i);
        }

        return result;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
