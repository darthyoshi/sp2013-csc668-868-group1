package org.openmrs.module.basicmodule.dsscompiler.compiler;

import org.openmrs.module.basicmodule.dsscompiler.ast.*;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSLibrary;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.Interpreter;
import org.openmrs.module.basicmodule.dsscompiler.intrinsics.DSSDateLibrary;
import org.openmrs.module.basicmodule.dsscompiler.intrinsics.DSSLengthAndWithinLibrary;
import org.openmrs.module.basicmodule.dsscompiler.intrinsics.IsLibrary;
import org.openmrs.module.basicmodule.dsscompiler.intrinsics.ReadLibrary;
import org.openmrs.module.basicmodule.dsscompiler.parser.Parser;

/**
 *  The DSSProgram class contains the main program for compiling
 *  and executing a DSS1 program
*/
public class DSSProgram {
    public static final DSSLibrary[] INTRINSICS = {
            new IsLibrary(),
            new ReadLibrary(),
	    new DSSDateLibrary(),
            new DSSLengthAndWithinLibrary()
            // Other libraries go here!
    };


/**
 * The DSSProgram class reads and compiles a source program
*/

    String sourceFile;

    public DSSProgram(String sourceFile) {
    	this.sourceFile = sourceFile;
    }

    public void compileAndExecute() throws Exception{
        try {
            Parser parser = new Parser(sourceFile);
            AST t = parser.execute();
            //PrintVisitor pv = new PrintVisitor();
            //t.accept(pv);
            new Interpreter(INTRINSICS).interpret(t);
        }catch (Exception e) {
            System.out.println("********exception*******"+e.toString());
            throw e;
         };
    }

    public static void main(String args[]) {
        try {
            String file = "/home/woeltjen/School/csc868/is.dss";
            if (args.length > 0) {
                file = args[0];
            }
            (new DSSProgram(file)).compileAndExecute();
        } catch (Exception ex) {}
    }
}
