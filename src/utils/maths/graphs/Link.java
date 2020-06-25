package utils.maths.graphs;

public class Link {

    private Summit linkedTo;
    private int weight;

    public Link(Summit summit, int weight){
        this.linkedTo = summit;
        this.weight = weight;
    }

    public Link(Summit summit){
        this.linkedTo = summit;
    }

    public Summit getLinkedTo() {
        return linkedTo;
    }

    public void setLinkedTo(Summit linkedTo) {
        this.linkedTo = linkedTo;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
