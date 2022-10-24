package core;

import java.util.Objects;

public class Declaration {

    private DeclarationType declarationType;
    private Asignation asignation;

    public Declaration() {
        this.declarationType = new DeclarationType();
        this.asignation = new Asignation();
    }

    public Declaration(DeclarationType declarationType, Asignation asignation) {
        this.declarationType = declarationType;
        this.asignation = asignation;
    }

    public DeclarationType getDeclarationType() {
        return this.declarationType;
    }

    public void setDeclarationType(DeclarationType declarationType) {
        this.declarationType = declarationType;
    }

    public Asignation getAsignation() {
        return this.asignation;
    }

    public void setAsignation(Asignation asignation) {
        this.asignation = asignation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Declaration)) {
            return false;
        }
        Declaration declaration = (Declaration) o;
        return Objects.equals(declarationType, declaration.declarationType)
                && Objects.equals(asignation, declaration.asignation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(declarationType, asignation);
    }

}
