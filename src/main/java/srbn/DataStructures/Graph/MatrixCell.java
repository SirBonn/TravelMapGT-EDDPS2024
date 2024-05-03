package srbn.DataStructures.Graph;

public class MatrixCell {

    private NodeG origin;
    private NodeG destination;
    private Route route;
    private boolean visited = false;

    public MatrixCell() {

    }

    public MatrixCell(NodeG origin, NodeG destination) {
        this.origin = origin;
        this.destination = destination;
        this.route = null;
    }

    public MatrixCell(MatrixCell cell){
        this.origin = cell.getOrigin();
        this.destination = cell.getDestination();
        this.route = cell.getRoute();
        this.visited = false;
    }

    public void setupCell(NodeG origin, NodeG destination, Route route) {
        this.origin = origin;
        this.destination = destination;
        this.route = route;
    }

    public MatrixCell(NodeG origin, NodeG destination, int timeCar, int timeWalk, int gasUsage, int personalWearing, int distance) {
        this.origin = origin;
        this.destination = destination;
        this.route = new Route(timeCar, timeWalk, gasUsage, personalWearing, distance);
    }

    public NodeG getOrigin() {
        return origin;
    }

    public void setOrigin(NodeG origin) {
        this.origin = origin;
    }

    public NodeG getDestination() {
        return destination;
    }

    public void setDestination(NodeG destination) {
        this.destination = destination;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public void setRoute(int timeCar, int timeWalk, int gasUsage, int personalWearing, int distance) {
        this.route = new Route(timeCar, timeWalk, gasUsage, personalWearing, distance);
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }


}

