package org.openmrs.module.dssmodule.flowcontrol;

import org.openmrs.module.dssmodule.state.ExecutionContext;
import org.openmrs.module.dssmodule.ast.AST;
import org.openmrs.module.dssmodule.visitor.ASTVisitor;


/**
 * Describes the common interface used to handle interpretation of specific 
 * node types within a compiled AST.
 * @author woeltjen
 */
public interface ASTInterpreter<T extends AST> {
    
    /**
     * Interpret a given node in the tree. Note that the object returned may 
     * vary based on node type, but is commonly the result of interpretation 
     * (i.e. the value of an expression)
     * @param tree the tree to interpret
     * @param context the execution (stores variable meanings, for ex)
     * @param visitor the visitor which may interpret children
     * @return 
     */
    public Object interpret(T tree, ExecutionContext context, ASTVisitor visitor);
}
