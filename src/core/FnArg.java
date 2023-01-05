package core;

import intermediate_code.ThreeAddressCode;

public class FnArg extends BaseNode {
    private TypeVar type;
    private Id id;

    public FnArg(TypeVar type, Id id, int line, int column) {
        super(line, column);
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
        return "Arg [type=" + type + ", id=" + id + " line=" + line + " column=" + column + "]";

    }

    @Override
    public void generate3dc(ThreeAddressCode codigoTresDir) {
        codigoTresDir.putVar(id.getValue(), type);
    }

}
