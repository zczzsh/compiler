import java.io.*;
import java.util.*;

// **********************************************************************
// The ASTnode class defines the nodes of the abstract-syntax tree that
// represents a C-- program.
//
// Internal nodes of the tree contain pointers to children, organized
// either in a list (for nodes that may have a variable number of 
// children) or as a fixed set of fields.
//
// The nodes for literals and ids contain line and character number
// information; for string literals and identifiers, they also contain a
// string; for integer literals, they also contain an integer value.
//
// Here are all the different kinds of AST nodes and what kinds of children
// they have.  All of these kinds of AST nodes are subclasses of "ASTnode".
// Indentation indicates further subclassing:
//
//     Subclass            Kids
//     --------            ----
//     ProgramNode         DeclListNode
//     DeclListNode        linked list of DeclNode
//     DeclNode:
//       VarDeclNode       TypeNode, IdNode, int
//       FnDeclNode        TypeNode, IdNode, FormalsListNode, FnBodyNode
//       FormalDeclNode    TypeNode, IdNode
//       StructDeclNode    IdNode, DeclListNode
//
//     FormalsListNode     linked list of FormalDeclNode
//     FnBodyNode          DeclListNode, StmtListNode
//     StmtListNode        linked list of StmtNode
//     ExpListNode         linked list of ExpNode
//
//     TypeNode:
//       IntNode           -- none --
//       BoolNode          -- none --
//       VoidNode          -- none --
//       StructNode        IdNode
//
//     StmtNode:
//       AssignStmtNode      AssignNode
//       PostIncStmtNode     ExpNode
//       PostDecStmtNode     ExpNode
//       ReadStmtNode        ExpNode
//       WriteStmtNode       ExpNode
//       IfStmtNode          ExpNode, DeclListNode, StmtListNode
//       IfElseStmtNode      ExpNode, DeclListNode, StmtListNode,
//                                    DeclListNode, StmtListNode
//       WhileStmtNode       ExpNode, DeclListNode, StmtListNode
//       CallStmtNode        CallExpNode
//       ReturnStmtNode      ExpNode
//
//     ExpNode:
//       IntLitNode          -- none --
//       StrLitNode          -- none --
//       TrueNode            -- none --
//       FalseNode           -- none --
//       IdNode              -- none --
//       DotAccessNode       ExpNode, IdNode
//       AssignNode          ExpNode, ExpNode
//       CallExpNode         IdNode, ExpListNode
//       UnaryExpNode        ExpNode
//         UnaryMinusNode
//         NotNode
//       BinaryExpNode       ExpNode ExpNode
//         PlusNode     
//         MinusNode
//         TimesNode
//         DivideNode
//         AndNode
//         OrNode
//         EqualsNode
//         NotEqualsNode
//         LessNode
//         GreaterNode
//         LessEqNode
//         GreaterEqNode
//
// Here are the different kinds of AST nodes again, organized according to
// whether they are leaves, internal nodes with linked lists of kids, or
// internal nodes with a fixed number of kids:
//
// (1) Leaf nodes:
//        IntNode,   BoolNode,  VoidNode,  IntLitNode,  StrLitNode,
//        TrueNode,  FalseNode, IdNode
//
// (2) Internal nodes with (possibly empty) linked lists of children:
//        DeclListNode, FormalsListNode, StmtListNode, ExpListNode
//
// (3) Internal nodes with fixed numbers of kids:
//        ProgramNode,     VarDeclNode,     FnDeclNode,     FormalDeclNode,
//        StructDeclNode,  FnBodyNode,      StructNode,     AssignStmtNode,
//        PostIncStmtNode, PostDecStmtNode, ReadStmtNode,   WriteStmtNode   
//        IfStmtNode,      IfElseStmtNode,  WhileStmtNode,  CallStmtNode
//        ReturnStmtNode,  DotAccessNode,   AssignExpNode,  CallExpNode,
//        UnaryExpNode,    BinaryExpNode,   UnaryMinusNode, NotNode,
//        PlusNode,        MinusNode,       TimesNode,      DivideNode,
//        AndNode,         OrNode,          EqualsNode,     NotEqualsNode,
//        LessNode,        GreaterNode,     LessEqNode,     GreaterEqNode
//
// **********************************************************************

// **********************************************************************
// ASTnode class (base class for all other kinds of nodes)
// **********************************************************************

abstract class ASTnode {
    
    // every subclass must provide an unparse operation
    public void unparse(PrintWriter p, int indent) {
    }

    // this method can be used by the unparse methods to do indenting
    protected void doIndent(PrintWriter p, int indent) {
        for (int k=0; k<indent; k++) p.print(" ");
    }
}

// **********************************************************************
// ProgramNode,  DeclListNode, FormalsListNode, FnBodyNode,
// StmtListNode, ExpListNode
// **********************************************************************

class ProgramNode extends ASTnode {
    // TO COMPLETE
    public DeclListNode node;
    public ProgramNode(DeclListNode node){
       this.node=node;
    }
    public void unparse(PrintWriter p, int indent){
        this.node.unparse(p, indent);
    }
}

class DeclListNode extends ASTnode {
    // TO COMPLETE
    public LinkedList<DeclNode> Link;
    public DeclListNode(LinkedList<DeclNode> link)
    {
        this.Link=link;
    }
    public void unparse(PrintWriter p, int indent){
        Iterator<DeclNode> it=this.Link.iterator();
        while(it.hasNext())
        {
            it.next().unparse(p, indent);
        }
    }
}

class FormalsListNode extends ASTnode {
    // TO COMPLETE
    public LinkedList<FormalDeclNode> Link;

    public FormalsListNode(LinkedList<FormalDeclNode> Link)
    {
        this.Link=Link;
    }

    public void unparse(PrintWriter p, int indent){
        for(Iterator<FormalDeclNode> iter = this.Link.iterator(); iter.hasNext();){
            iter.next().unparse(p, indent);
            if(iter.hasNext()){
                p.print(", ");
            }
        }
    }
}

class FnBodyNode extends ASTnode {
    // TO COMPLETE
    public DeclListNode dnode;
    public StmtListNode snode;

    public FnBodyNode(DeclListNode dnode,StmtListNode snode)
    {
        this.dnode=dnode;
        this.snode=snode;
    }

    public void unparse(PrintWriter p, int indent){
        p.print("{");
        p.print("\n");
        this.dnode.unparse(p, indent+4);
        this.snode.unparse(p, indent+4);
        p.print("}");
    }
}

class StmtListNode extends ASTnode {
    // TO COMPLETE
    public LinkedList<StmtNode> Link;

    public StmtListNode(LinkedList<StmtNode> Link)
    {
        this.Link=Link;
    }
    public void unparse(PrintWriter p, int indent){
        for(Iterator<StmtNode> iter = this.Link.iterator(); iter.hasNext();)
        iter.next().unparse(p, indent);
    }
}

class ExpListNode extends ASTnode {
    // TO COMPLETE
    public LinkedList<ExpNode> Link;

    public ExpListNode(LinkedList<ExpNode> Link)
    {
        this.Link=Link;
    }

    public void unparse(PrintWriter p, int indent){
        for(Iterator<ExpNode> iter = this.Link.iterator(); iter.hasNext();){
            iter.next().unparse(p, indent);
            if(iter.hasNext()){
                p.print(", ");
            }
        }
    }
}

// **********************************************************************
// DeclNode and its subclasses
// **********************************************************************

abstract class DeclNode extends ASTnode {
}

class VarDeclNode extends DeclNode {
    // TO COMPLETE
    public TypeNode type;
    public IdNode id;
    public int flag;
    ///// DO NOT CHANGE THIS PART /////
    private int mySize;  // use value NOT_STRUCT if this is not a struct type
    public static int NOT_STRUCT = -1;

    public VarDeclNode(TypeNode type, IdNode id, int flag)
    {
        this.type=type;
        this.id=id;
        this.flag=flag;
    }

    public void unparse(PrintWriter p, int indent){
        doIndent(p, indent);
        this.type.unparse(p, indent);
        p.print(" ");
        this.id.unparse(p, indent);
        p.print(";");
        p.print("\n");
    }
}

class FnDeclNode extends DeclNode {
    // TO COMPLETE
    public TypeNode type;
    public IdNode id;
    public FormalsListNode formal;
    public FnBodyNode fnbody;

    public FnDeclNode(TypeNode t,IdNode i, FormalsListNode f, FnBodyNode fn)
    {
        this.type=t;
        this.id=i;
        this.formal=f;
        this.fnbody=fn;
    }

    public void unparse(PrintWriter p, int indent){
        this.type.unparse(p, indent);
        p.print(" ");
        this.id.unparse(p, indent);
        p.print("(");
        this.formal.unparse(p, indent);
        p.print(")");
        p.print(" ");
        this.fnbody.unparse(p, indent);
        p.print("\n");
    }
}

class FormalDeclNode extends DeclNode {
    // TO COMPLETE
    public TypeNode type;
    public IdNode id;

    public FormalDeclNode(TypeNode t, IdNode i)
    {
        this.type=t;
        this.id=i;
    }

    public void unparse(PrintWriter p, int indent){
        this.type.unparse(p, indent);
        p.print(" ");
        this.id.unparse(p, indent);
    }

}

class StructDeclNode extends DeclNode {
    // TO COMPLETE
    public IdNode id;
    public DeclListNode decl;
    
    public StructDeclNode(IdNode i, DeclListNode d)
    {
        this.id=i;
        this.decl=d;
    }

    public void unparse(PrintWriter p, int indent){
        doIndent(p, indent);
        p.print("struct");
        p.print(" ");
        this.id.unparse(p, indent);
        p.print(" ");
        p.print("{");
        p.print("\n");
        this.decl.unparse(p, indent+4);
        doIndent(p, indent);
        p.print("};");
        p.print("\n");

    }
}

// **********************************************************************
// TypeNode and its Subclasses
// **********************************************************************

abstract class TypeNode extends ASTnode {
}

class IntNode extends TypeNode {
    // TO COMPLETE
    public IntNode(){}

    public void unparse(PrintWriter p, int indent){
        p.print("int");
    }
}

class BoolNode extends TypeNode {
    // TO COMPLETE
    public BoolNode(){}
    public void unparse(PrintWriter p, int indent){
        p.print("bool");
    }
}

class VoidNode extends TypeNode {
    // TO COMPLETE
    public VoidNode(){}
    public void unparse(PrintWriter p, int indent){
        p.print("void");
    }
}

class StructNode extends TypeNode {
    // TO COMPLETE
    public IdNode id;
    public StructNode(IdNode id)
    {
        this.id=id;
    }
    public void unparse(PrintWriter p, int indent){
        p.print("struct");
        p.print(" ");
        this.id.unparse(p, indent);
    }
}

// **********************************************************************
// StmtNode and its subclasses
// **********************************************************************

abstract class StmtNode extends ASTnode {
}

class AssignStmtNode extends StmtNode {
    // TO COMPLETE
    public AssignNode assign;

    public AssignStmtNode(AssignNode a)
    {
        this.assign=a;
    }

    public void unparse(PrintWriter p, int indent){
        doIndent(p, indent);
        this.assign.unparse(p, indent);
        p.print(";\n");
    }
}

class PostIncStmtNode extends StmtNode {
    // TO COMPLETE
    public ExpNode exp;
    public PostIncStmtNode(ExpNode e)
    {
        this.exp=e;
    }
    
    public void unparse(PrintWriter p, int indent){
        doIndent(p, indent);
        this.exp.unparse(p, indent);
        p.print("++");
        p.print(";\n");
    }

}

class PostDecStmtNode extends StmtNode {
    // TO COMPLETE
    public ExpNode exp;
    public PostDecStmtNode(ExpNode e)
    {
        this.exp=e;
    }
    
    public void unparse(PrintWriter p, int indent){
        doIndent(p, indent);
        this.exp.unparse(p, indent);
        p.print("--");
        p.print(";\n");
    }

}

class ReadStmtNode extends StmtNode {
    // TO COMPLETE
    public ExpNode exp;

    public ReadStmtNode(ExpNode e)
    {
        this.exp=e;
    }
 
    public void unparse(PrintWriter p, int indent){
        doIndent(p, indent);
        p.print("cin >> ");
        this.exp.unparse(p, indent);
        p.print(";\n");
    }

}

class WriteStmtNode extends StmtNode {
    // TO COMPLETE
    public ExpNode exp;

    public WriteStmtNode(ExpNode e)
    {
        this.exp=e;
    }

    public void unparse(PrintWriter p, int indent){
        doIndent(p, indent);
        p.print("cout << ");
        this.exp.unparse(p, indent);
        p.print(";\n");
    }
}

class IfStmtNode extends StmtNode {
    // TO COMPLETE
    public ExpNode exp;
    public DeclListNode decl;
    public StmtListNode stmt;

    public IfStmtNode(ExpNode e, DeclListNode d, StmtListNode s)
    {
        this.exp=e;
        this.decl=d;
        this.stmt=s;
    }

    public void unparse(PrintWriter p, int indent){
        doIndent(p, indent);
        p.print("if (");
        this.exp.unparse(p, indent);
        p.print(") {\n");
        this.decl.unparse(p, indent+4);
        this.stmt.unparse(p, indent+4);
        doIndent(p, indent);
        p.print("} \n");
    }
}

class IfElseStmtNode extends StmtNode {
    // TO COMPLETE
    public ExpNode exp;
    public DeclListNode decl1;
    public DeclListNode decl2;
    public StmtListNode stmt1;
    public StmtListNode stmt2;

    public IfElseStmtNode(ExpNode e, DeclListNode d1, StmtListNode s1, DeclListNode d2, StmtListNode s2)
    {
        this.exp=e;
        this.decl1=d1;
        this.decl2=d2;
        this.stmt1=s1;
        this.stmt2=s2;
    }

    public void unparse(PrintWriter p, int indent){
        doIndent(p, indent);
        p.print("if (");
        this.exp.unparse(p, indent);
        p.print(") {\n");
        this.decl1.unparse(p, indent+4);
        this.stmt1.unparse(p, indent+4);
        doIndent(p, indent);
        p.print("} \n");
        p.print("else { \n");
        this.decl2.unparse(p, indent+4);
        this.stmt2.unparse(p, indent+4);
        doIndent(p, indent);
        p.print("} \n");
    }
}

class WhileStmtNode extends StmtNode {
    // TO COMPLETE
    public ExpNode exp;
    public DeclListNode decl;
    public StmtListNode stmt;

    public WhileStmtNode(ExpNode e, DeclListNode d, StmtListNode s)
    {
        this.exp=e;
        this.decl=d;
        this.stmt=s;
    }

    public void unparse(PrintWriter p, int indent){
        doIndent(p, indent);
        p.print("while (");
        this.exp.unparse(p, indent);
        p.print(") {\n");
        this.decl.unparse(p, indent+4);
        this.stmt.unparse(p, indent+4);
        doIndent(p, indent);
        p.print("} \n");
    }
}

class CallStmtNode extends StmtNode {
    // TO COMPLETE
    public CallExpNode call;

    public CallStmtNode (CallExpNode c)
    {
        this.call=c;
    }

    public void unparse(PrintWriter p, int indent){
        doIndent(p, indent);
        this.call.unparse(p, indent);
        p.print(";\n");
    }
}

class ReturnStmtNode extends StmtNode {
    // TO COMPLETE
    public ExpNode exp;

    public ReturnStmtNode(ExpNode e)
    {
        this.exp=e;
    }

    public void unparse(PrintWriter p, int indent){
        doIndent(p, indent);
        p.print("return ");
        if(exp!=null)
        {
            this.exp.unparse(p, indent);
        }
        p.print(";\n");
    }
}

// **********************************************************************
// ExpNode and its subclasses
// **********************************************************************

abstract class ExpNode extends ASTnode {
}

class IntLitNode extends ExpNode {
    // TO COMPLETE
    public int linenum, charnum, intVal;

    public IntLitNode(int linenum,int charnum,int intVal)
    {
        this.linenum=linenum;
        this.charnum=charnum;
        this.intVal=intVal;
    }

    public void unparse(PrintWriter p, int indent){
        p.print(this.intVal);
    }
}

class StringLitNode extends ExpNode {
    // TO COMPLETE
    public int linenum, charnum;
    public String strVal;
    
    public StringLitNode(int linenum, int charnum, String strVal)
    {
        this.linenum=linenum;
        this.charnum=charnum;
        this.strVal=strVal;
    }

    public void unparse(PrintWriter p, int indent){
        p.print(this.strVal);
    }
}

class TrueNode extends ExpNode {
    // TO COMPLETE
    public int linenum, charnum;

    public TrueNode (int linenum, int charnum)
    {
        this.linenum=linenum;
        this.charnum=charnum;
    }

    public void unparse(PrintWriter p, int indent){
        p.print("true");
    }
}

class FalseNode extends ExpNode {
    // TO COMPLETE
    public int linenum, charnum;
    
    public FalseNode (int linenum, int charnum)
    {
        this.linenum=linenum;
        this.charnum=charnum;
    }
    
    public void unparse(PrintWriter p, int indent){
            p.print("false");
    }
}

class IdNode extends ExpNode {
    // TO COMPLETE
    public int linenum;
    public int charnum;
    public String idVal;

    public IdNode(int linenum, int charnum, String idVal)
    {
        this.linenum=linenum;
        this.charnum=charnum;
        this.idVal=idVal;
    }

    public void unparse(PrintWriter p, int indent){
        p.print(this.idVal);
    }
}

class DotAccessExpNode extends ExpNode {
    // TO COMPLETE
    public IdNode id;
    public ExpNode exp;

    public DotAccessExpNode (ExpNode e, IdNode i)
    {
        this.exp=e;
        this.id=i;
    }

    public void unparse(PrintWriter p, int indent){
        this.exp.unparse(p, indent);
        p.print(".");
        this.id.unparse(p, indent);
    }
}

class AssignNode extends ExpNode {
    // TO COMPLETE
    public ExpNode exp1,exp2;
    
    public AssignNode (ExpNode e1, ExpNode e2)
    {
        this.exp1=e1;
        this.exp2=e2;
    }

    public void unparse(PrintWriter p, int indent){
        this.exp1.unparse(p, indent);
        p.print(" = ");
        this.exp2.unparse(p, indent);
    }
}

class CallExpNode extends ExpNode {
    // TO COMPLETE
    public IdNode id;
    public ExpListNode expl;

    public CallExpNode (IdNode i, ExpListNode expl)
    {
        this.id=i;
        this.expl=expl;
    }

    public void unparse(PrintWriter p, int indent){
        this.id.unparse(p, indent);
        p.print("(");
        this.expl.unparse(p, indent);
        p.print(")");
    }
}

abstract class UnaryExpNode extends ExpNode {
    // TO COMPLETE
    public ExpNode exp;
    public UnaryExpNode(ExpNode exp){
        this.exp = exp;
    }
}

abstract class BinaryExpNode extends ExpNode {
    // TO COMPLETE
    public ExpNode exp1, exp2;
    public BinaryExpNode(ExpNode exp1, ExpNode exp2){
        this.exp1 = exp1;
        this.exp2 = exp2;
    }
}

// **********************************************************************
// Subclasses of UnaryExpNode
// **********************************************************************

class UnaryMinusNode extends UnaryExpNode {
    // TO COMPLETE
    public UnaryMinusNode(ExpNode exp){
        super(exp);
    }
    public void unparse(PrintWriter p, int indent){
        p.print("-");
        this.exp.unparse(p, indent);
    }
}

class NotNode extends UnaryExpNode {
    // TO COMPLETE
    public NotNode(ExpNode exp){
        super(exp);
    }
    public void unparse(PrintWriter p, int indent){
        p.print("!");
        this.exp.unparse(p, indent);
    } 
}

// **********************************************************************
// Subclasses of BinaryExpNode
// **********************************************************************

class PlusNode extends BinaryExpNode {
    // TO COMPLETE
    public PlusNode(ExpNode exp1,  ExpNode exp2){
        super(exp1, exp2);
    }
    public void unparse(PrintWriter p, int indent){
        this.exp1.unparse(p, indent);
        p.print(" + ");
        this.exp2.unparse(p, indent);
    }
}

class MinusNode extends BinaryExpNode {
    // TO COMPLETE
    public MinusNode(ExpNode exp1,  ExpNode exp2){
        super(exp1, exp2);
    }
    public void unparse(PrintWriter p, int indent){
        this.exp1.unparse(p, indent);
        p.print(" - ");
        this.exp2.unparse(p, indent);
    }
}

class TimesNode extends BinaryExpNode {
    // TO COMPLETE
    public TimesNode(ExpNode exp1,  ExpNode exp2){
        super(exp1, exp2);
    }
    public void unparse(PrintWriter p, int indent){
        this.exp1.unparse(p, indent);
        p.print(" * ");
        this.exp2.unparse(p, indent);
    }
}

class DivideNode extends BinaryExpNode {
    // TO COMPLETE
    public DivideNode(ExpNode exp1,  ExpNode exp2){
        super(exp1, exp2);
    }
    public void unparse(PrintWriter p, int indent){
        this.exp1.unparse(p, indent);
        p.print(" / ");
        this.exp2.unparse(p, indent);
    }
}

class AndNode extends BinaryExpNode {
    // TO COMPLETE
    public AndNode(ExpNode exp1,  ExpNode exp2){
        super(exp1, exp2);
    }
    public void unparse(PrintWriter p, int indent){
        p.print("(");
        this.exp1.unparse(p, indent);
        p.print(" && ");
        this.exp2.unparse(p, indent);
        p.print(")");
    }
}

class OrNode extends BinaryExpNode {
    // TO COMPLETE
    public OrNode(ExpNode exp1,  ExpNode exp2){
        super(exp1, exp2);
    }
    public void unparse(PrintWriter p, int indent){
        p.print("(");
        this.exp1.unparse(p, indent);
        p.print(" || ");
        this.exp2.unparse(p, indent);
        p.print(")");
    }
}

class EqualsNode extends BinaryExpNode {
    // TO COMPLETE
    public EqualsNode(ExpNode exp1,  ExpNode exp2){
        super(exp1, exp2);
    }
    public void unparse(PrintWriter p, int indent){
        p.print("(");
        this.exp1.unparse(p, indent);
        p.print(" == ");
        this.exp2.unparse(p, indent);
        p.print(")");
    }
}

class NotEqualsNode extends BinaryExpNode {
    // TO COMPLETE
    public NotEqualsNode(ExpNode exp1,  ExpNode exp2){
        super(exp1, exp2);
    }
    public void unparse(PrintWriter p, int indent){
        p.print("(");
        this.exp1.unparse(p, indent);
        p.print(" != ");
        this.exp2.unparse(p, indent);
        p.print(")");
    }
}

class LessNode extends BinaryExpNode {
    // TO COMPLETE
    public LessNode(ExpNode exp1,  ExpNode exp2){
        super(exp1, exp2);
    }
    public void unparse(PrintWriter p, int indent){
        p.print("(");
        this.exp1.unparse(p, indent);
        p.print(" < ");
        this.exp2.unparse(p, indent);
        p.print(")");
    }
}

class GreaterNode extends BinaryExpNode {
    // TO COMPLETE
    public GreaterNode(ExpNode exp1,  ExpNode exp2){
        super(exp1, exp2);
    }
    public void unparse(PrintWriter p, int indent){
        p.print("(");
        this.exp1.unparse(p, indent);
        p.print(" > ");
        this.exp2.unparse(p, indent);
        p.print(")");
    }
}

class LessEqNode extends BinaryExpNode {
    // TO COMPLETE
    public LessEqNode(ExpNode exp1,  ExpNode exp2){
        super(exp1, exp2);
    }
    public void unparse(PrintWriter p, int indent){
        p.print("(");
        this.exp1.unparse(p, indent);
        p.print(" <= ");
        this.exp2.unparse(p, indent);
        p.print(")");
    }
}

class GreaterEqNode extends BinaryExpNode {
    // TO COMPLETE
    public GreaterEqNode(ExpNode exp1,  ExpNode exp2){
        super(exp1, exp2);
    }
    public void unparse(PrintWriter p, int indent){
        p.print("(");
        this.exp1.unparse(p, indent);
        p.print(" >= ");
        this.exp2.unparse(p, indent);
        p.print(")");
    }
}
