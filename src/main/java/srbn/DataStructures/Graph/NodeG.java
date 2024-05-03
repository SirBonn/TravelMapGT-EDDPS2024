package srbn.DataStructures.Graph;

public class NodeG {

    private String alias;
    private int id;

    public NodeG(){
    }

    public NodeG(String alias, int id) {
        this.alias = alias;
        this.id = id;
    }

    //getters n setters
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
