package core;

public class SyntaxTree<T> {
    private T root;

    public SyntaxTree(T root) {
        this.root = root;
    }

    public SyntaxTree() {
        this.root = null;
    }

    public T getRoot() {
        return root;
    }

    public void setRoot(T root) {
        this.root = root;
    }

    public void free() {
        this.root = null;
    }

    @Override
    public String toString() {
        return "SyntaxTree [root=" + root + "]";
    }

}
