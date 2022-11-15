package core;

public class FnArg {
    private TypeVar type;
    private Id id;

    public FnArg(TypeVar type, Id id) {
        this.type = type;
        this.id = id;
    }

    public TypeVar getType() {
        return type;
    }

    public void setType(TypeVar type) {
        this.type = type;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Arg [type=" + type + ", id=" + id + "]";
    }

}
