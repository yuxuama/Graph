package utils.maths.graphs;

public class UnorientedGraph extends Graph{

    public UnorientedGraph(){
        super();
    }

    @Override
    public void addLink(Summit s1, Summit s2, int weight) {
        if(!s1.hasChild(s2) && !s2.hasChild(s1)){
            s1.addChildLink(s2, weight);
            s2.addChildLink(s1, weight);
        }
    }

    @Override
    public void removeLink(Object sValue, Object sValue2) {
        Summit s1 = findOne(sValue);
        Summit s2 = findOne(sValue2);

        if(s1.hasChild(s2) && s2.hasChild(s1) && !s1.equals(s2)){
            s1.removeChildLink(s2);
            s2.removeChildLink(s1);
        }
    }

    @Override
    public void removeAllLink(Summit summit) {
        for(Summit s: childs){
            if(s.hasChild(summit)){
                s.removeChildLink(summit);
            }
        }

        summit.removeAllChild();
    }

    /** GETTERS
    * */

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
}
