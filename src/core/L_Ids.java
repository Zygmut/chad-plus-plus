package core;

public class L_Ids extends BaseNode {

    private L_Ids nextId;
    private String id;

    public L_Ids() {
        super(0, 0);
        this.nextId = null;
        this.id = null;
    }

    public L_Ids(String id, int line, int column) {
        super(line, column);
        this.nextId = null;
        this.id = id;
    }

    public L_Ids(String id, L_Ids nextId, int line, int column) {
        super(line, column);
        this.nextId = nextId;
        this.id = id;
    }

    public L_Ids nextId() {
        return nextId;
    }

    public void setNextId(L_Ids nextId) {
        this.nextId = nextId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "L_Ids [id=" + id + ", nextId=" + nextId + " line=" + line + " column=" + column + "]";

    }
}
