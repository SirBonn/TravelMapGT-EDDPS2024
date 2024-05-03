package srbn.DataStructures.Graph;


public class Traffic {

    int initHour;
    int endHour;
    int trafficProbability;

    public Traffic(int initHour, int endHour, int trafficProbability) {
        this.initHour = initHour;
        this.endHour = endHour;
        this.trafficProbability = trafficProbability;
    }

    public int getInitHour() {
        return initHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public int getTrafficProbability() {
        return trafficProbability;
    }

    public void setInitHour(int initHour) {
        this.initHour = initHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public void setTrafficProbability(int trafficProbability) {
        this.trafficProbability = trafficProbability;
    }


}
