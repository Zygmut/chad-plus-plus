package core;

public class A_Tuple extends BaseNode {
    private Id id;
    private Number access;

    public A_Tuple(Id id, Number access, int line, int column) {
        super(line, column);
        this.id = id;
        this.access = access;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Number getAccess() {
        return access;
    }

    public void setAccess(Number access) {
        this.access = access;
    }

    @Override
    public String toString() {
        return "A_Tuple [id=" + id + ", access=" + access + " line=" + line + " column=" + column + "]";
    }

}
