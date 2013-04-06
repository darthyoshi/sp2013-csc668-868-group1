package org.openmrs.module.basicmodule.dsscompiler.interpreter.instrinsics;

import org.openmrs.module.basicmodule.dsscompiler.interpreter.*;

/**
 *
 * @author woeltjen
 */
public class IsLibrary implements DSSLibrary {

    public void install(ExecutionContext context) {
        Evaluator e = context.getEvaluator();
        //context.set("isString", new Is(DSSString.class, context.getEvaluator()));
        //context.set("isBoolean", new Is(DSSBoolean.class, context.getEvaluator()));
    }
    
    private class Is extends DSSFunction {
        private Class<? extends DSSValue> isClass;
        private Evaluator evaluator;

        public Is(Class<? extends DSSValue> isClass, Evaluator evaluator) {
            this.isClass = isClass;
            this.evaluator = evaluator;
        }

        @Override
        public DSSValue call(DSSValue... args) {
            return evaluator.toDSSValue((Boolean) isClass.isAssignableFrom(args[0].getClass()));
        }
    }
}
