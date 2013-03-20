package interpreter;

import interpreter.node.*;
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
    private ASTInterpreter<AssignTree> assignInterpreter = new AssignInterpreter();
    private ASTInterpreter<BlockTree> blockInterpreter = new BlockInterpreter();
    private ASTInterpreter<CallTree> callInterpreter = new CallInterpreter();
    private ASTInterpreter<AST> elsifInterpreter = new IfInterpreter();
    private ASTInterpreter<FieldRefTree> fieldRefInterpreter = new FieldRefInterpreter();
    private ASTInterpreter<ForTree> forInterpreter = new ForInterpreter();
    private ASTInterpreter<FormalsTree> formalsInterpreter = new FormalsInterpreter();
    private ASTInterpreter<FunctionDeclTree> functionDeclInterpreter = new FunctionDeclInterpreter();
    private ASTInterpreter<IdTree> idInterpreter = new IdInterpreter();
    private ASTInterpreter<AST> ifInterpreter = new IfInterpreter();
    private ASTInterpreter<ListTree> listInterpreter = new ListInterpreter();
    private ASTInterpreter<LiteralTree> literalInterpreter = new LiteralInterpreter();
    private ASTInterpreter<ObjectDeclTree> objectDeclInterpreter = new ObjectDeclInterpreter();
    private ASTInterpreter<ObjectTree> objectInterpreter = new ObjectInterpreter();
    private ASTInterpreter<OpTree> opInterpreter = new OpInterpreter();
    private ASTInterpreter<ProgramTree> programInterpreter = new ProgramInterpreter();
    private ASTInterpreter<ReturnTree> returnInterpreter = new ReturnInterpreter();
    private ASTInterpreter<WhileTree> whileInterpreter = new WhileInterpreter();
  
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
