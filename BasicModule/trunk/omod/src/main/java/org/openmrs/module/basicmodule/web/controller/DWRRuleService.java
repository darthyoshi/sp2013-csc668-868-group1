package org.openmrs.module.basicmodule.web.controller;

import java.util.Collection;
import org.openmrs.module.basicmodule.dsscompiler.compiler.DSSRuleService;

/**
 *
 * @author woeltjen
 */
public class DWRRuleService {
    public static Collection<String> runRules(int patientId, String source) {
        return DSSRuleService.getRuleService().runRules(patientId, source);
    }
}
