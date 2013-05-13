package org.openmrs.module.dssmodule.intrinsics;

/**
 * @author Kay Choi
 */

import java.util.HashMap;
import java.util.Map;
import org.openmrs.module.dssmodule.state.DSSFunction;
import org.openmrs.module.dssmodule.state.ExecutionContext;

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
