package srbn.DataStructures.Graph;

import srbn.DataStructures.Lists.GenericList.GenericOrderedList;

public class Route {

    private int timeCar;
    private int timeWalk;
    private int gasUsage;
    private int personalWearing;
    private int distance;
    private GenericOrderedList<Traffic> traffic;

    public Route() {
    }

    public Route(int timeCar, int timeWalk, int gasUsage, int personalWearing, int distance) {
        this.timeCar = timeCar;
        this.timeWalk = timeWalk;
        this.gasUsage = gasUsage;
        this.personalWearing = personalWearing;
        this.distance = distance;
    }

    //getters n setters
    public int getTimeCar() {
        return timeCar;
    }

    public void setTimeCar(int timeCar) {
        this.timeCar = timeCar;
    }

    public int getTimeWalk() {
        return timeWalk;
    }

    public void setTimeWalk(int timeWalk) {
        this.timeWalk = timeWalk;
    }

    public int getGasUsage() {
        return gasUsage;
    }

    public void setGasUsage(int gasUsage) {
        this.gasUsage = gasUsage;
    }

    public int getPersonalWearing() {
        return personalWearing;
    }

    public void setPersonalWearing(int personalWearing) {
        this.personalWearing = personalWearing;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public GenericOrderedList<Traffic> getTraffic() {
        return traffic;
    }

    public void addTraffic(Traffic traffic) {

        if (this.traffic == null) {
            this.traffic = new GenericOrderedList<>();
        }
        this.traffic.add(traffic);
    }
}
