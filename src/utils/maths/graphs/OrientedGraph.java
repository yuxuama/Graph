package utils.maths.graphs;

public class OrientedGraph extends Graph {

    public OrientedGraph(){
        super();
    }

    /**SUMMIT MANAGEMENT**/

    @Override
    public void addSummit(Object value) {
        Summit s = new Summit(value, true);
        childs.add(s);
    }

    /**LINK MANAGEMENT**/

    @Override
    public void addLink(Summit s1, Summit s2, int weight) {
        /*Adding link considering s1 is parent and s2 child
        the so-created link will have the specified weight
        * */

        if(!s1.hasChild(s2) && !s2.hasParent(s1)){
            s1.addChildLink(s2, weight);
            s2.addParentLink(s1, weight);
        }

    }

    @Override
    public void removeLink(Object sValue, Object sValue2) {
        /*Removing link: considering sValue as parent value and sValue2 as child value
        * */
        Summit s1 = findOne(sValue);
        Summit s2 = findOne(sValue2);

        if(s1.hasChild(s2) && s2.hasParent(s1)){
            s1.removeChildLink(s2);
            s2.removeParentLink(s1);
        }
    }

    @Override
    public void removeAllLink(Summit summit) {
        /*Remove all parent and child link from the specified summit
        * */

        for(Summit s: childs){
            if(s.hasChild(summit)){
                s.removeChildLink(summit);
            } if(s.hasParent(summit)){
                s.removeParentLink(summit);
            }
        }
        summit.removeAllChild();
        summit.removeAllParent();
    }

    /** GETTERS
     *
     * parent links**/

    public Link getParentLink(Summit parent, Summit child){
        for(Link l: child.getParents()){
            if(l.getLinkedTo() == parent){
                return l;
            }
        }

        return null;
    }

    public Link getParentLink(Object pValue, Object cValue){
        Summit parent = findOne(pValue);
        Summit child = findOne(cValue);

        return getParentLink(parent, child);
    }

    public int getParentLinkWeight(Object pValue, Object cValue){
        Link link = getParentLink(pValue, cValue);
        if(link != null){
            return link.getWeight();
        }else{
            return -2;
        }
    }

    /** child links**/

    public Link getChildLink(Summit parent, Summit child){
        for(Link link: parent.getChilds()){
            if(link.getLinkedTo() == child){
                return link;
            }
        }

        return null;
    }

    public Link getChildLink(Object pValue, Object cValue){
        Summit parent = findOne(pValue);
        Summit child = findOne(cValue);

        return getChildLink(parent, child);
    }

    public int getChildLinkWeight(Object pValue, Object cValue){
        Link link = getChildLink(pValue, cValue);
        if(link != null){
            return link.getWeight();
        }else{
            return -2;
        }
    }
}
