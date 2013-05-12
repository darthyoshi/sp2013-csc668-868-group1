package org.openmrs.module.dssmodule.parser;

import org.openmrs.module.dssmodule.lexer.Tokens;
import org.openmrs.module.dssmodule.lexer.Lexer;
import org.openmrs.module.dssmodule.lexer.Symbol;
import org.openmrs.module.dssmodule.lexer.Token;
import org.openmrs.module.dssmodule.ast.FormalsTree;
import org.openmrs.module.dssmodule.ast.IdTree;
import org.openmrs.module.dssmodule.ast.BlockTree;
import org.openmrs.module.dssmodule.ast.IfTree;
import org.openmrs.module.dssmodule.ast.AssignTree;
import org.openmrs.module.dssmodule.ast.AST;
import org.openmrs.module.dssmodule.ast.ObjectTree;
import org.openmrs.module.dssmodule.ast.OpTree;
import org.openmrs.module.dssmodule.ast.LiteralTree;
import org.openmrs.module.dssmodule.ast.ObjectDeclTree;
import org.openmrs.module.dssmodule.ast.ElsifTree;
import org.openmrs.module.dssmodule.ast.ReturnTree;
import org.openmrs.module.dssmodule.ast.ProgramTree;
import org.openmrs.module.dssmodule.ast.WhileTree;
import org.openmrs.module.dssmodule.ast.FunctionDeclTree;
import org.openmrs.module.dssmodule.ast.ListTree;
import org.openmrs.module.dssmodule.ast.ForTree;
import org.openmrs.module.dssmodule.ast.FieldRefTree;
import org.openmrs.module.dssmodule.ast.CallTree;
import java.util.*;

/**
 * The Parser class performs recursive-descent parsing; as a by-product it will
 * build the <b>Abstract Syntax Tree</b> representation for the source
 * program<br> Following is the Grammar we are using:<br>
 * <pre>
 *
 * PROGRAM -> ‘program’ D* BLOCK ==> program
 * BLOCK -> ‘{‘ S* ‘}’  ==> block
 * D -> 'function' NAME FUNHEAD BLOCK      ==> functionDecl
 * FUNHEAD  -> '(' (NAME list ',')? ')'  ==> formals
 * S -> ‘if’ EE ‘then’ BLOCK ('else' BLOCK)? ==> if 
 *   -> ‘if’ EE ‘then’ BLOCK Elif ==> if 
 * -> ‘while’ EE BLOCK               ==> while
 * -> 'for' NAME in NLIST BLOCK     ==> FOR
 * -> ‘return’ EE                    ==> return
 * -> BLOCK
 * -> IdMod‘:=’ EE                    ==> assign
 * -> NAME '(' (EE list ',')? ')' ==> call
 *
 * Elif -> ‘elsif’ EE 'then' BLOCK Elif	                ==> elsif
 *      -> ‘elsif’ EE 'then' BLOCK ('else' BLOCK)?	==> elsif
 * 
 * EE -> E
 * -> EE '||' E
 * 
 * E -> SE
 * -> SE ‘==’ SE   ==> =
 * -> SE ‘!=’ SE   ==> !=
 * -> SE ‘<’  SE   ==> <
 * -> SE ‘<=’ SE   ==> <=
 *
 * SE  ->  T
 * ->  SE ‘+’ T  ==> +
 * ->  SE ‘-‘ T  ==> -
 * ->  SE ‘|’ T  ==> or
 *
 * T  -> TT
 * -> T ‘*’ F  ==> *
 * -> T ‘/’ F  ==> /
 * -> T ‘&’ F  ==> and
 *
 * TT -> F
 * -> TT**F    ==> **
 *
 * F  -> ‘(‘ EE ‘)’
 * -> IdMod 
 * -> <literal>
 * -> NAME '(' (EE list ',')? ')' ==> call
 * -> Object '(' (NAME list ',')? ')'    ==> ObjectDecl
 * -> new NAME          ==> Object
 * -> LIST
 * IdMod -> NAME
 *       -> NAME '.' NAME    ==> fieldRef
 * 
 *  NLIST  -> NAME
 *         -> LIST
 * 
 * LIST  -> '{' (E list ',')? '}'   ==> list
 *
 * NAME  -> <id>
 *
 *  </pre>
 */
public class Parser {

    private Token currentToken;
    private Lexer lex;
    private EnumSet<Tokens> relationalOps =
            EnumSet.of(Tokens.Equal, Tokens.NotEqual, Tokens.Less, Tokens.LessEqual);
    private EnumSet<Tokens> addingOps =
            EnumSet.of(Tokens.Plus, Tokens.Minus, Tokens.Or);
    private EnumSet<Tokens> multiplyingOps =
            EnumSet.of(Tokens.Multiply, Tokens.Divide, Tokens.And);

    /**
     * Construct a new Parser;
     *
     * @param sourceProgram - source file name
     * @exception Exception - thrown for any problems at startup (e.g. I/O)
     */
    public Parser(String sourceProgram) throws Exception {
        try {
            lex = new Lexer(sourceProgram);
            scan();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("********exception*******" + e.toString());
            throw e;
        };
    }

    public Lexer getLex() {
        return lex;
    }

    /**
     * Execute the parse command
     *
     * @return the AST for the source program
     * @exception Exception - pass on any type of exception raised
     */
    public AST execute() throws Exception {
        try {
            return rProgram();
        } catch (SyntaxError e) {
            e.print();
            throw e;
        }
    }

    /**
     * <
     * pre> PROGRAM -> ‘program’ D* BLOCK ==> program
     * </pre>
     *
     * @return the program tree
     * @exception SyntaxError - thrown for any syntax error
     */
    public AST rProgram() throws SyntaxError {
        // note that rProgram actually returns a ProgramTree; we use the 
        // principle of substitutability to indicate it returns an AST
        AST t = new ProgramTree();
        Token tok = null;
        expect(Tokens.Program);
        while (true) {
            try {
                tok = currentToken;
                t.addKid(rDecl());
            } catch (SyntaxError e) {
                if (tok != currentToken) { // we should not find any part of a decl before complaining; if so, stop!
                    throw new FatalSyntaxError();
                }
                break;
            }
        }
        t.addKid(rBlock());
        return t;
    }

    /**
     * <
     * pre> block -> '{' s* '}' ==> block
     * </pre>
     *
     * @return block tree
     * @exception SyntaxError - thrown for any syntax error e.g. an expected
     * left brace isn't found
     */
    public AST rBlock() throws SyntaxError {
        Token tok = null;
        Token tokk = null;
        expect(Tokens.LeftBrace);
        AST t = new BlockTree();
        while (true) {  // get statements
            try {
                tokk = currentToken;
                t.addKid(rStatement());
            } catch (SyntaxError e) {
                if (tokk != currentToken) { // we should not find any part of a decl before complaining; if so, stop!
                    throw new FatalSyntaxError();
                }
                break;
            }
        }
        expect(Tokens.RightBrace);
        return t;
    }

    /**
     * <
     * pre> d -> 'function' NAME FUNHEAD BLOCK ==> functionDecl
     * </pre>
     *
     * @return either the decl tree or the functionDecl tree
     * @exception SyntaxError - thrown for any syntax error
     */
    public AST rDecl() throws SyntaxError {
        AST t, t1;
        expect(Tokens.Function);
        t = (new FunctionDeclTree()).addKid(rName());
        t.addKid(rFunHead());
        t.addKid(rBlock());
        return t;

    }

    /**
     * <
     * pre> funHead -> '(' (NAME list ',')? ')' ==> formals note a funhead is a
     * list of zero or more decl's separated by commas, all in parens
     * </pre>
     *
     * @return the formals tree describing this list of formals
     * @exception SyntaxError - thrown for any syntax error
     */
    public AST rFunHead() throws SyntaxError {
        AST t = new FormalsTree();
        expect(Tokens.LeftParen);
        if (!isNextTok(Tokens.RightParen)) {
            do {
                t.addKid(rName());
                if (isNextTok(Tokens.Comma)) {
                    scan();
                } else {
                    break;
                }
            } while (true);
        }
        expect(Tokens.RightParen);
        return t;
    }

    /**
     * <pre>
     * S ->  ‘if’ EE ‘then’ BLOCK ( rElif* 'else' BLOCK)? ==> if
     * -> ‘while’ EE BLOCK ==> while
     * -> 'for' NAME in NLIST BLOCK ==> FOR
     * -> ‘return’ EE ==> return
     * -> BLOCK
     * -> IdMod ‘:=’ EE ==> assign
     * -> FieldRef ':=" EE             ==> assign
     * -> name '(' (ee list ',')? ) ==> call
     * </pre>
     *
     * @return the tree corresponding to the statement found
     * @exception SyntaxError - thrown for any syntax error
     */
    public AST rStatement() throws SyntaxError {
        AST t;
        if (isNextTok(Tokens.For)) {  // -> 'for' NAME in NLIST BLOCK ==> FOR
            scan();
            t = new ForTree();
            t.addKid(rName());
            expect(Tokens.In);
            t.addKid(rNList());
            t.addKid(rBlock());
            return t;
        }
        if (isNextTok(Tokens.If)) {   // -> ‘if’ EE ‘then’ BLOCK ('else' BLOCK)? ==> if 
                                      // -> ‘if’ EE ‘then’ BLOCK Elif ==> if 
            scan();
            t = new IfTree();
            t.addKid(rEExpr());
            expect(Tokens.Then);
            t.addKid(rBlock());
            if (isNextTok(Tokens.Else)) {
                scan();
                return t.addKid(rBlock());
            }
            if (isNextTok(Tokens.Elsif)) {
                return t.addKid(rElif());
             }
            return t;
        }
        if (isNextTok(Tokens.While)) {  //-> ‘while’ EE BLOCK ==> while
            scan();
            t = new WhileTree();
            t.addKid(rEExpr());
            t.addKid(rBlock());
            return t;
        }
        if (isNextTok(Tokens.Return)) {   //  -> ‘return’ EE ==> return
            scan();
            t = new ReturnTree();
            t.addKid(rEExpr());
            return t;
        }
        if (isNextTok(Tokens.LeftBrace)) {  //  -> BLOCK
            return rBlock();
        }
        t = IdMod();
        if (isNextTok(Tokens.LeftParen)) {  // -> name '(' (ee list ',')? ) ==> call
            scan();
            if (t.getClass() == FieldRefTree.class) {
                throw new SyntaxError(new Token(0, 0, Symbol.symbol(".", Tokens.Dot)), Tokens.LeftParen);
            }
            t = (new CallTree()).addKid(t);
            if (!isNextTok(Tokens.RightParen)) {
                do {
                    t.addKid(rExpr());
                    if (isNextTok(Tokens.Comma)) {
                        scan();
                    } else {
                        break;
                    }
                } while (true);
            }
            expect(Tokens.RightParen);
            return t;
        }
        t = (new AssignTree()).addKid(t);  //  -> NAME ‘:=’ EE ==> assign
        expect(Tokens.Assign);
        t.addKid(rEExpr());
        return t;
    }

    /**
     * Elif -> ‘elsif’ EE 'then' BLOCK Elif	==> elsif
     *      -> ‘elsif’ EE 'then' BLOCK ('else' BLOCK)?	==> elsif
     */
    public AST rElif() throws SyntaxError {
        AST t;
        expect(Tokens.Elsif);
        t = new ElsifTree().addKid(rEExpr());
        expect(Tokens.Then);
        t.addKid(rBlock());
        if (isNextTok(Tokens.Else)) {
            scan();
            return t.addKid(rBlock());
        }
        if (isNextTok(Tokens.Elsif)) {
            return t.addKid(rElif());
        }
        return t;
    }

    /**
     * <pre>
     * EE -> E
     *    -> EE '||' E
     * </pre>
     *
     * @return the tree corresponding to the expression
     * @exception SyntaxError - thrown for any syntax error
     */
    public AST rEExpr() throws SyntaxError {
        AST t, kid = rExpr();
        while ((t = getConcatTree()) != null) {
            t.addKid(kid);
            t.addKid(rExpr());
            kid = t;
        }
        return kid;
    }

    /**
     * <pre>
     * e -> se
     * -> se '==' se ==> =
     * -> se '!=' se ==> !=
     * -> se '<' se ==> <
     * -> se '<=' se ==> <=
     * </pre>
     *
     * @return the tree corresponding to the expression
     * @exception SyntaxError - thrown for any syntax error
     */
    public AST rExpr() throws SyntaxError {
        AST t, kid = rSimpleExpr();
        t = getRelationTree();
        if (t == null) {
            return kid;
        }
        t.addKid(kid);
        t.addKid(rSimpleExpr());
        return t;
    }

    /**
     * <pre>
     * se -> t
     * -> se '+' t ==> +
     * -> se '-' t ==> -
     * -> se '|' t ==> or
     * This
     * rule indicates we should pick up as many <i>t</i>'s as possible; the
     * <i>t</i>'s will be left associative
     * </pre>
     *
     * @return the tree corresponding to the adding expression
     * @exception SyntaxError - thrown for any syntax error
     */
    public AST rSimpleExpr() throws SyntaxError {
        AST t, kid = rTerm();
        while ((t = getAddOperTree()) != null) {
            t.addKid(kid);
            t.addKid(rTerm());
            kid = t;
        }
        return kid;
    }

    /**
     * <pre>
     * t -> tt
     * -> t '*' tt ==> *
     * -> t '/' tt ==> /
     * -> t '&' tt ==> and
     *
     * This
     * rule indicates we should pick up as many <i>tt</i>'s as possible; the
     * <i>tt</i>'s will be left associative
     * </pre>
     *
     * @return the tree corresponding to the multiplying expression
     * @exception SyntaxError - thrown for any syntax error
     */
    public AST rTerm() throws SyntaxError {
        AST t, kid = rPowerTerm();
        while ((t = getMultOperTree()) != null) {
            t.addKid(kid);
            t.addKid(rFactor());
            kid = t;
        }
        return kid;
    }

    /**
     * tt -> F -> TT**F
     */
    public AST rPowerTerm() throws SyntaxError {
        AST t, kid = rFactor();
        while ((t = getPowerOperTree()) != null) {
            t.addKid(kid);
            t.addKid(rFactor());
            kid = t;
        }
        return kid;
    }

    /**
     * <pre>
     * f -> '(' ee ')'
     * -> name
     * -> <literal>
     * -> name '(' (ee list ',')? ')' ==> call
     * -> Object '(' (NAME list ',')? ')'    ==> ObjectDecl
     * -> new NAME          ==> Object
     * -> NAME '.' NAME     ==> fieldRef
     * -> LIST
     * </pre>
     *
     * @return the tree corresponding to the factor expression
     * @exception SyntaxError - thrown for any syntax error
     */
    public AST rFactor() throws SyntaxError {
        AST t;
        if (isNextTok(Tokens.LeftParen)) { // -> (ee)
            scan();
            t = rEExpr();
            expect(Tokens.RightParen);
            return t;
        }
        if (isLiteralToken()) {  //  -> <literal>
            t = new LiteralTree(currentToken);
            scan();
            return t;
        }
        if (isNextTok(Tokens.Object)) {  // -> Object '(' (NAME list ',')? ')'    ==> ObjectDecl
            t = new ObjectDeclTree();
            scan();
            expect(Tokens.LeftParen);
            t.addKid(rName());
            while (isNextTok(Tokens.Comma)) {
                scan();
                t.addKid(rName());
            }
            expect(Tokens.RightParen);
            return t;
        }
        if (isNextTok(Tokens.New)) { // -> new NAME          ==> Object
            scan();
            t = new ObjectTree();
            return t.addKid(rName());
        }
        if (isNextTok(Tokens.LeftBrace)) {  // -> LIST
            return rList();
        }
        t = IdMod();
        if (isNextTok(Tokens.LeftParen)) {  // -> name '(' (ee list ',')? ) ==> call
            scan();     // -> name '(' (ee list ',')? ) ==> call
            t = (new CallTree()).addKid(t);
            if (!isNextTok(Tokens.RightParen)) {
                if (t.getClass() == FieldRefTree.class) {
                    throw new SyntaxError(new Token(0, 0, Symbol.symbol(".", Tokens.Dot)), Tokens.LeftParen);
                }
                do {
                    t.addKid(rExpr());
                    if (isNextTok(Tokens.Comma)) {
                        scan();
                    } else {
                        break;
                    }
                } while (true);
            }
            expect(Tokens.RightParen);
            return t;
        }
        if (isNextTok(Tokens.Dot)) {  //  > NAME '.' NAME     ==> fieldRef
            t = (new FieldRefTree()).addKid(t);
            scan();
            return t.addKid(rName());
        }
        return t;
    }

    /**
     * IdMod -> NAME -> NAME '.' NAME ==> fieldRef
     *
     * @return
     * @throws SyntaxError
     */
    public AST IdMod() throws SyntaxError {
        AST t = rName();
        if (!isNextTok(Tokens.Dot)) {
            return t;
        }
        scan();
        t = (new FieldRefTree()).addKid(t).addKid(rName());
        return t;
    }

    /**
     *   NLIST  -> NAME
     *          -> LIST
     * @return
     * @throws SyntaxError 
     */
    public AST rNList() throws SyntaxError {
        if (isNextTok(Tokens.LeftBrace)) {
            return rList();
        }
        return rName();
    }
    
    /**
     * LIST -> '{' (EE list ',')? '}' ==> list
     */
    public AST rList() throws SyntaxError {
        AST t;
        expect(Tokens.LeftBrace);
        t = new ListTree().addKid(rEExpr());
        if (!isNextTok(Tokens.RightBrace)) {
            while (isNextTok(Tokens.Comma)) {
                scan();
                t.addKid(rEExpr());
            };
        }
        expect(Tokens.RightBrace);
        return t;
    }

    /**
     * <pre>
     * name -> <id>
     * </pre>
     *
     * @return the id tree
     * @exception SyntaxError - thrown for any syntax error
     */
    public AST rName() throws SyntaxError {
        AST t;
        if (isNextTok(Tokens.Identifier)) {
            t = new IdTree(currentToken);
            scan();
            return t;
        }
        throw new SyntaxError(currentToken, Tokens.Identifier);
    }

    AST getRelationTree() {  // build tree with current token's relation
        Tokens kind = currentToken.getKind();
        if (relationalOps.contains(kind)) {
            AST t = new OpTree(currentToken);
            scan();
            return t;
        } else {
            return null;
        }
    }

    private AST getAddOperTree() {
        Tokens kind = currentToken.getKind();
        if (addingOps.contains(kind)) {
            AST t = new OpTree(currentToken);
            scan();
            return t;
        } else {
            return null;
        }
    }

    private AST getMultOperTree() {
        Tokens kind = currentToken.getKind();
        if (multiplyingOps.contains(kind)) {
            AST t = new OpTree(currentToken);
            scan();
            return t;
        } else {
            return null;
        }
    }

    private AST getPowerOperTree() {
        Tokens kind = currentToken.getKind();
        if (isNextTok(Tokens.Power)) {
            AST t = new OpTree(currentToken);
            scan();
            return t;
        }
        return null;
    }

    private AST getConcatTree() {
        Tokens kind = currentToken.getKind();
        if (isNextTok(Tokens.Concat)) {
            AST t = new OpTree(currentToken);
            scan();
            return t;
        }
        return null;
    }

    private boolean isLiteralToken() {
        if ((currentToken == null)) {
            return false;
        }
        if ((currentToken.getKind() == Tokens.INTeger)
                || (currentToken.getKind() == Tokens.True)
                || (currentToken.getKind() == Tokens.False)
                || (currentToken.getKind() == Tokens.Float)
                || (currentToken.getKind() == Tokens.STRing)) {
            return true;
        }
        return false;
    }

    private boolean isNextTok(Tokens kind) {
        if ((currentToken == null) || (currentToken.getKind() != kind)) {
            return false;
        }
        return true;
    }

    private void expect(Tokens kind) throws SyntaxError {
        if (isNextTok(kind)) {
            scan();
            return;
        }
        throw new SyntaxError(currentToken, kind);
    }

    private void scan() {
        currentToken = lex.nextToken();
        if (currentToken != null) {
            //currentToken.print();   // debug printout
        }
        return;
    }
}

class SyntaxError extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    private Token tokenFound;
    private Tokens kindExpected;

    /**
     * record the syntax error just encountered
     *
     * @param tokenFound is the token just found by the parser
     * @param kindExpected is the token we expected to find based on the current
     * context
     */
    public SyntaxError(Token tokenFound, Tokens kindExpected) {
        this.tokenFound = tokenFound;
        this.kindExpected = kindExpected;
    }

    void print() {
        System.out.println("Expected: " + kindExpected
                + "  Found: " + tokenFound);
        return;
    }
    
    @Override
    public String getMessage()
    {
        return "Expected: " + kindExpected + "  Found: " + tokenFound;
    }
}

class FatalSyntaxError extends SyntaxError {

    public FatalSyntaxError() {
        super(new Token(0, 0, Symbol.symbol("", Tokens.Null)), Tokens.Null);
    }

    void print() {
        System.out.println("UNRECOVERABLE SYNTAX ERROR");
        //System.exit(1);
        return;
    }
    
    @Override
    public String getMessage()
    {
        return "UNRECOVERABLE SYNTAX ERROR";
    }
}
