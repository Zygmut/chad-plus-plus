package core;

public class Tuple {
    private L_Args tupleContent;

    public Tuple(L_Args tupleContent) {
        this.tupleContent = tupleContent;
    }

    public L_Args getTupleContent() {
        return tupleContent;
    }

    public void setTupleContent(L_Args tupleContent) {
        this.tupleContent = tupleContent;
    }

    @Override
    public String toString() {
        return "Tuple [tupleContent=" + tupleContent + "]";
    }

}
