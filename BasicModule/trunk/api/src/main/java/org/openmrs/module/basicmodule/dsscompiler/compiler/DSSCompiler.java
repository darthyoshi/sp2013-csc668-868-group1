package org.openmrs.module.basicmodule.dsscompiler.compiler;

import org.openmrs.module.basicmodule.dsscompiler.ast.*;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.Interpreter;
import org.openmrs.module.basicmodule.dsscompiler.parser.Parser;
import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

/**
 *  The Compiler class contains the main program for compiling
 *  a source program to bytecodes
*/
public class DSSCompiler {

/**
 * The Compiler class reads and compiles a source program
*/
	
	String sourceFile;
	
    public DSSCompiler(String sourceFile) {
    	this.sourceFile = sourceFile;
    }
    
    public void compileProgram() {
        try {
            Parser parser = new Parser(sourceFile);
            AST t = parser.execute();
            //PrintVisitor pv = new PrintVisitor();
            //t.accept(pv);
            new Interpreter().interpret(t);
        }catch (Exception e) {
            System.out.println("********exception*******"+e.toString());
         };
    }
    
    public static void main(String args[]) {
        if (args.length == 0) {
            System.out.println("***Incorrect usage, try: java compiler.Compiler <file>");
            System.exit(1);
        }
        (new DSSCompiler(args[0])).compileProgram();
    }
}
