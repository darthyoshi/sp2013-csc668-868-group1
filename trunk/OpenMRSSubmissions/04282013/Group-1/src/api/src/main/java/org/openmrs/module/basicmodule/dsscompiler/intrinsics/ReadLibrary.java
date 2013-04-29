package org.openmrs.module.basicmodule.dsscompiler.intrinsics;

/**
 * @author Kay Choi
 */

import java.util.HashMap;
import java.util.Map;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSFunction;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.DSSLibrary;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.ExecutionContext;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.intrinsics.read.*;

public class ReadLibrary implements DSSLibrary {
    private static final Map<String, DSSFunction> MAP =
            new HashMap<String, DSSFunction>();

    public Map<String, DSSFunction> getFunctions(ExecutionContext context) {
        // Initialize lazily
        if (MAP.isEmpty()) {
            MAP.put("read", new DSSRead());
            MAP.put("readInitialEncounter", new DSSReadInitialEncounter());
            MAP.put("readLatestEncounter", new DSSReadLatestEncounter());
        }
        return MAP;
    }
}
