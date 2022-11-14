package core;

public class CallFn {
    private Id id;
    private L_Args args;

    public CallFn(Id id, L_Args args) {
        this.id = id;
        this.args = args;
    }

    public CallFn(Id id) {
        this.id = id;
        this.args = null;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public L_Args getArgs() {
        return args;
    }

    public void setArgs(L_Args args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "CallFn [id=" + id + ", args=" + args + "]";
    }

}
