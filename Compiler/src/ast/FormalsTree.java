package ast;

import visitor.ASTVisitor;

public class FormalsTree  extends AST {

    public FormalsTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitFormalsTree(this);
    }
}
