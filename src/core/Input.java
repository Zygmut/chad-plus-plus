package core;

public class Input {
    private int type; // 0 int, 1 bool

    public Input(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Input [type=" + type + "]";
    }

}
