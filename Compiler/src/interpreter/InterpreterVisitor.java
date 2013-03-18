package interpreter;

import ast.*;
import visitor.ASTVisitor;

/**
 * 
 * @author woeltjen
 */
public class InterpreterVisitor extends ASTVisitor {
    private ExecutionContext context;
    
    // TODO: These should be assigned to concrete implementations, once 
    //       those have been defined.
    private ASTInterpreter<ActualArgsTree> actualArgsInterpreter;
    private ASTInterpreter<AssignTree> assignInterpreter;
    private ASTInterpreter<BlockTree> blockInterpreter;
    private ASTInterpreter<CallTree> callInterpreter;
    private ASTInterpreter<ElsifTree> elsifInterpreter;
    private ASTInterpreter<FieldRefTree> fieldRefInterpreter;
    private ASTInterpreter<ForTree> forInterpreter;
    private ASTInterpreter<FormalsTree> formalsInterpreter;
    private ASTInterpreter<FunctionDeclTree> functionDeclInterpreter;
    private ASTInterpreter<IdTree> idInterpreter;
    private ASTInterpreter<IfTree> ifInterpreter;
    private ASTInterpreter<ListTree> listInterpreter;
    private ASTInterpreter<LiteralTree> literalInterpreter;
    private ASTInterpreter<ObjectDeclTree> objectDeclInterpreter;
    private ASTInterpreter<ObjectTree> objectInterpreter;
    private ASTInterpreter<OpTree> opInterpreter;
    private ASTInterpreter<ProgramTree> programInterpreter;
    private ASTInterpreter<ReturnTree> returnInterpreter;
    private ASTInterpreter<WhileTree> whileInterpreter;
  
    public InterpreterVisitor(ExecutionContext context) {
        this.context = context;
    }
    
    @Override
    public Object visitActualArgsTree(AST t) {
        return actualArgsInterpreter.interpret((ActualArgsTree) t, context, this);
    }

    @Override
    public Object visitAssignTree(AST t) {
        return assignInterpreter.interpret((AssignTree) t, context, this);
    }

    @Override
    public Object visitBlockTree(AST t) {
        return blockInterpreter.interpret((BlockTree) t, context, this);
    }

    @Override
    public Object visitCallTree(AST t) {
        return callInterpreter.interpret((CallTree) t, context, this);
    }

    @Override
    public Object visitElsifTree(AST t) {
        return elsifInterpreter.interpret((ElsifTree) t, context, this);
    }

    @Override
    public Object visitFieldRefTree(AST t) {
        return fieldRefInterpreter.interpret((FieldRefTree) t, context, this);
    }

    @Override
    public Object visitForTree(AST t) {
        return forInterpreter.interpret((ForTree) t, context, this);
    }

    @Override
    public Object visitFormalsTree(AST t) {
        return formalsInterpreter.interpret((FormalsTree) t, context, this);
    }

    @Override
    public Object visitFunctionDeclTree(AST t) {
        return functionDeclInterpreter.interpret((FunctionDeclTree) t, context, this);
    }

    @Override
    public Object visitIdTree(AST t) {
        return idInterpreter.interpret((IdTree) t, context, this);
    }

    @Override
    public Object visitIfTree(AST t) {
        return ifInterpreter.interpret((IfTree) t, context, this);
    }

    @Override
    public Object visitListTree(AST t) {
        return listInterpreter.interpret((ListTree) t, context, this);
    }

    @Override
    public Object visitLiteralTree(AST t) {
        return literalInterpreter.interpret((LiteralTree) t, context, this);
    }

    @Override
    public Object visitObjectDeclTree(AST t) {
        return objectDeclInterpreter.interpret((ObjectDeclTree) t, context, this);
    }

    @Override
    public Object visitObjectTree(AST t) {
        return objectInterpreter.interpret((ObjectTree) t, context, this);
    }

    @Override
    public Object visitOpTree(AST t) {
        return opInterpreter.interpret((OpTree) t, context, this);
    }

    @Override
    public Object visitProgramTree(AST t) {
        return programInterpreter.interpret((ProgramTree) t, context, this);
    }

    @Override
    public Object visitReturnTree(AST t) {
        return returnInterpreter.interpret((ReturnTree) t, context, this);
    }

    @Override
    public Object visitWhileTree(AST t) {
        return whileInterpreter.interpret((WhileTree) t, context, this);
    }
    
}
