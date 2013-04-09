package org.openmrs.module.basicmodule.dsscompiler.compiler;

import org.openmrs.module.basicmodule.dsscompiler.ast.*;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.Interpreter;
import org.openmrs.module.basicmodule.dsscompiler.parser.Parser;
import org.openmrs.module.basicmodule.dsscompiler.visitor.*;

/**
 *  The DSSProgram class contains the main program for compiling
 *  and executing a DSS1 program
*/
public class DSSProgram {

/**
 * The DSSProgram class reads and compiles a source program
*/
	
    String sourceFile;
	
    public DSSProgram(String sourceFile) {
    	this.sourceFile = sourceFile;
    }
    
    public void compileAndExecute() {
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
        (new DSSProgram(args[0])).compileAndExecute();
    }
}
