package interpreter.stub;

import ast.AST;
import interpreter.DSSFunction;
import interpreter.DSSValue;
import interpreter.Evaluator;
import interpreter.InterpreterVisitor;
import interpreter.stub.StubEvaluator.StubInteger;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import parser.Parser;
import visitor.PrintVisitor;

/**
 *
 * @author woeltjen
 */
public class StubVisualInterpreter {
    private StubContext context = new StubContext();
    private InterpreterVisitor visitor = new InterpreterVisitor(context);
    private StubScreen screen = new StubScreen();
    
    public StubVisualInterpreter() {
        context.setFunction("alert", print);
        context.setFunction("wait", wait);
        context.setFunction("paint", paint);
        context.setFunction("swap", swap);
        context.setFunction("poll", joy);
        
        context.setFunction("echo", echo);
    }
    
    public void interpret (AST ast) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                screen.setSize(200, 200);
                screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                screen.setVisible(true);
            }            
        });
        screen.swap();
        ast.accept(new PrintVisitor());
        ast.accept(visitor);
    }
    
    public static void main(String[] args) {
        try {
            String dss = args.length > 1 ? args[0] : "breakout.dss";
            AST ast = new Parser(dss).execute();
            //ast.accept(new PrintVisitor());
            new StubVisualInterpreter().interpret(ast);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private DSSFunction print = new DSSFunction() {
        public DSSValue call(DSSValue... args) {
            for (DSSValue v : args) {
                System.out.println(v.toString());
            }
            return null;
        }        
    };
    
    private DSSFunction swap = new DSSFunction() {
        public DSSValue call(DSSValue... args) {
            screen.swap();
            return null;
        }        
    };
    
    private DSSFunction paint = new DSSFunction() {
        public DSSValue call(DSSValue... args) {
            Evaluator eval = context.getEvaluator();
            int x = eval.castTo(Long.class, args[0]).intValue();
            int y = eval.castTo(Long.class, args[1]).intValue();
            int w = eval.castTo(Long.class, args[2]).intValue();
            int h = eval.castTo(Long.class, args[3]).intValue();
            screen.draw(x, y, w, h);
            return null;
        }        
    };
    
    private DSSFunction wait = new DSSFunction() {

        public DSSValue call(DSSValue... args) {
            Evaluator eval = context.getEvaluator();
            int t = eval.castTo(Long.class, args[0]).intValue();
            try {
                Thread.sleep(t);
            } catch (Exception e) {
                // Whatever, we're just trying things out
            }
            return null;
        }
        
    };
    
    private DSSFunction joy = new DSSFunction() {

        public DSSValue call(DSSValue... args) {
            int dir = 0;
            if (screen.pressed(KeyEvent.VK_LEFT)) {
                dir -= 1;
            }
            if (screen.pressed(KeyEvent.VK_RIGHT)) {
                dir += 1;
            }
            return new StubInteger(dir);
        }
        
    };
    
    private DSSFunction echo = new DSSFunction() {

        @Override
        public DSSValue call(DSSValue... args) {
            for (DSSValue arg : args) {
                System.out.println(arg);
            }
            return null;
        }

        @Override
        public boolean passAsIdentifier(int argumentIndex) {
            return true;
        }
        
        
        
    };
}
