package org.openmrs.module.basicmodule.dsscompiler.ast;

import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

public class ForTree extends AST {

    public ForTree() {
    }

 
    public Object accept(ASTVisitor v) {
        return v.visitForTree(this);
    }

}