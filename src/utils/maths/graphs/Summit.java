package utils.maths.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Summit {

    private Object value;
    private int subvalue;
    private List<Link> childs;
    private List<Link> parents;

    /** CONSTRUCTOR  **/

    public Summit(Object value, boolean oriented){
        this.value = value;
        this.childs = new ArrayList<>();
        if(oriented){
            this.parents = new ArrayList<>();
        }
        this.subvalue = -1;

    }

    /** CHILD LINK METHODS  **/

    public void addChildLink(Summit child, int weight){
        Link link = new Link(child, weight);
        childs.add(link);
    }

    public void removeChildLink(Summit child){
        Link removed = foundChild(child);
        if(removed != null){
            childs.remove(removed);
        } else throw new NullPointerException("Link does not exist");
    }

    public void removeAllChild(){
        childs.clear();
    }

    /** PARENT LINK METHODS**/

    public void addParentLink(Summit parent, int weight){
        Link link = new Link(parent, weight);
        parents.add(link);
    }

    public void removeParentLink(Summit parent){
        Link removed = foundParent(parent);
        if(removed != null){
            parents.remove(removed);
        } else throw new NullPointerException("Link does not exist");
    }

    public void removeAllParent(){
        parents.clear();
    }

    /** OTHERS USEFUL METHODS**/

    public boolean hasChild(Summit tested){
        return foundChild(tested) != null;
    }

    public boolean hasParent(Summit tested){
        return foundParent(tested) != null;
    }

    private Link foundChild(Summit searched){
        for(Link link: childs){
            if(link.getLinkedTo() == searched){
                return link;
            }
        }

        return null;
    }

    private Link foundParent(Summit searched){
        for(Link link: parents){
            if(link.getLinkedTo() == searched){
                return link;
            }
        }

        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Summit summit = (Summit) o;
        return value.equals(summit.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /** GETTERS AND SETTERS */

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

    public Link[] getParents(){
        Link[] result = new Link[parents.size()];
        for(int i = 0; i<parents.size(); i++){
            result[i] = parents.get(i);
        }

        return result;
    }

    public int getSubvalue() {
        return subvalue;
    }

    public void setSubvalue(int subvalue) {
        this.subvalue = subvalue;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
