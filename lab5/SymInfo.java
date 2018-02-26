import java.util.*;

/**
 * The SymInfo class defines a symbol-table entry. 
 * Each SymInfo contains a type (a Type).
 */
public class SymInfo {
    private Type type;
    
    public SymInfo(Type type) {
        this.type = type;
    }
    
    public Type getType() {
        return type;
    }
    
    public String toString() {
        return type.toString();
    }
}

/**
 * The FnInfo class is a subclass of the SymInfo class just for functions.
 * The returnType field holds the return type and there are fields to hold
 * information about the parameters.
 */
class FnInfo extends SymInfo {
    private Type type;
    private int numparams;
    private List<Type> L;
    public FnInfo(Type type, int numparams) {
        super(new FnType());
        this.type=type;
        this.numparams=numparams;
        this.L = new LinkedList<Type>();
    }

    public void addFormals(List<Type> L) {
        this.L=L;
    }
    
    public Type getReturnType() {
        return this.type;
    }

    public int getNumParams() {
        return this.numparams;
    }

    public List<Type> getParamTypes() {
        return this.L;
    }

    public String toString() {
        String str = "";
        boolean first = true;
        for (Type mytype : L) {
            if (first) {
                first = false;
            }
            else {
                str += ",";
            }
            str += mytype.toString();
        }

        str += "->" + this.type.toString();
        return str;
    }
}

/**
 * The StructInfo class is a subclass of the SymInfo class just for variables 
 * declared to be a struct type. 
 */
class StructInfo extends SymInfo {

    private IdNode id;
    public StructInfo(IdNode id) {
        super(new StructType(id));
        this.id=id;
    }

    public IdNode getStructType() {
        return this.id;
    }    
}

/**
 * The StructDefInfo class is a subclass of the SymInfo class just for the 
 * definition of a struct type. 
 * Each StructDefInfo contains a symbol table to hold information about its 
 * fields.
 */
class StructDefInfo extends SymInfo {
    
    private SymTable table;
    public StructDefInfo(SymTable table) {
       super(new StructDefType());
       this.table=table;
    }

    public SymTable getSymTable() {
       return this.table;
    }
}
