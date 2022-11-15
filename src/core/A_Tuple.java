package core;

public class A_Tuple {
    private Id id;
    private Number access;

    public A_Tuple(Id id, Number access) {
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
        return "A_Tuple [id=" + id + ", access=" + access + "]";
    }

}
