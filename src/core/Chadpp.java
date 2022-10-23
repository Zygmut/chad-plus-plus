package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Chadpp
 */
public class Chadpp {

    private List<Function> listOfFunctions;
    private Main main;

    public Chadpp() {
        this.listOfFunctions = new ArrayList<>();
        this.main = new Main();
    }

    public List<Function> getListOfFunctions() {
        return this.listOfFunctions;
    }

    public void setListOfFunctions(List<Function> listOfFunctions) {
        this.listOfFunctions = listOfFunctions;
    }

    public Main getMain() {
        return this.main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Chadpp)) {
            return false;
        }
        Chadpp chadpp = (Chadpp) o;
        return Objects.equals(listOfFunctions, chadpp.listOfFunctions) && Objects.equals(main, chadpp.main);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfFunctions, main);
    }

    @Override
    public String toString() {
        return "{" +
                " listOfFunctions='" + getListOfFunctions() + "'" +
                ", main='" + getMain() + "'" +
                "}";
    }

}