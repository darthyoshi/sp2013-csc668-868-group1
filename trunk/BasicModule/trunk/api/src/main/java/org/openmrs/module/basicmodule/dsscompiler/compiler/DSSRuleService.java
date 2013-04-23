package org.openmrs.module.basicmodule.dsscompiler.compiler;

import java.io.*;
import java.util.*;
import org.openmrs.module.basicmodule.dsscompiler.ast.AST;
import org.openmrs.module.basicmodule.dsscompiler.interpreter.Interpreter;
import org.openmrs.module.basicmodule.dsscompiler.intrinsics.AnnotatedDSSLibrary;
import org.openmrs.module.basicmodule.dsscompiler.intrinsics.DSSIdentifier;
import org.openmrs.module.basicmodule.dsscompiler.intrinsics.DSSIntrinsic;
import org.openmrs.module.basicmodule.dsscompiler.parser.Parser;
import org.openmrs.module.basicmodule.dsscompiler.xml.XMLBuilder;
import org.openmrs.util.OpenmrsUtil;

/**
 * Provides an interface for loading, saving, and running DSS rules.
 * @author woeltjen
 */
public class DSSRuleService {
    private static final String RULE_FOLDER = "dssModule";
    
    private static final String INDEX_FILE = "index.txt";
    private static final String SOURCE_SUFFIX = "Source-Dss.txt";
    private static final String XML_SUFFIX = "XML-Dss.txt";
    
    // Implemented as a singleton
    private static DSSRuleService INSTANCE = null;
    
    /**
     * Holds references to source code files, for convenience.
     */
    private Map<String, File> sourceFiles = new HashMap<String, File>();
    
    /**
     * Stores all compiled ASTs in memory.
     */
    private Map<String, AST> rules = new HashMap<String, AST>();

    /**
     * This constructor is private to support singleton pattern. Use 
     * getRuleService() if you need an instance.
     */
    private DSSRuleService() {
        loadRules();
    }

    /**
     * Get an instance of the DSSRuleService
     * @return 
     */
    public static DSSRuleService getRuleService() {
        if (INSTANCE == null) {
            INSTANCE = new DSSRuleService();
        }
        return INSTANCE;
    }
   
    /**
     * Store a rule with the given name and source code into the rule system. 
     * The rule may subsequently be retrieved by this name. If there are 
     * problems with the choice of name or compilation errors in the source 
     * code, an exception will be thrown.
     * @param ruleName the name to store the rule under
     * @param sourceCode DSS1 source code to run for this rule
     * @throws Exception if storage could not be completed
     */
    public void store(String ruleName, String sourceCode) throws Exception {
        // Strip whitespace from the rule name
        ruleName = ruleName.replaceAll("\n", "");        

        // Convert rule name to filename form
        String fileName = toFileName(ruleName);
        
        // Choose a file to save to
        File target;
        if (sourceFiles.containsKey(ruleName)) {
            target = sourceFiles.get(ruleName);
        } else { // Create file
            target = getFile(fileName + SOURCE_SUFFIX);
            if (target.exists()) { // This should be a new file
                throw new Exception("Naming collision for rule " + ruleName);
            }
        }
        
        // Save the source code to the file
        Writer w = new FileWriter(target);
        w.write(sourceCode);
        w.close();
        
        // Compile the source code
        Parser p = new Parser(target.getAbsolutePath());
        AST ast = p.execute();
        
        // Save the source code for later
        rules.put(ruleName, ast);
        sourceFiles.put(ruleName, target);

        // And, finally, convert to XML and save
        new XMLBuilder(ast).write(getFile(fileName + XML_SUFFIX));

        // Store rule name to an index (since rule->filename loses whitespace)
        File indexFile = getFile(INDEX_FILE);
        w = new FileWriter(indexFile, true);
        w.write(ruleName + "\n");
        w.close();        
    } 
    
    
    /**
     * Load existing source code for a rule.
     * @param ruleName
     * @return
     * @throws IOException 
     */
    public String load(String ruleName) throws IOException {
        if (!sourceFiles.containsKey(ruleName)) {
            return "";
        }
        
        BufferedReader reader = 
                new BufferedReader(new FileReader(sourceFiles.get(ruleName)));
        StringBuilder sourceCode = new StringBuilder();
        
        String line = null;
        while ( (line = reader.readLine()) != null) {
            sourceCode.append(line);
        }
        
        reader.close();
        
        return sourceCode.toString();
    }
    
    /**
     * Get all currently defined rules.
     * @return 
     */
    public Collection<String> listRules() {
        return rules.keySet(); 
    }
    
    /**
     * Run all known rules for the given patient
     * @param patientId the patient's numeric id, for encounter look ups
     * @return all alerts (mapped from target name -> list of alert messages)
     */
    public Map<String, List<String>> runRules(int patientId) {
        // Create a target for alerts to be stored to
        DSSAlertMap alerts = new DSSAlertMap();

        // Run all rules; alerts go into AlertMap
        for (AST rule : rules.values()) {
            Interpreter i = new Interpreter(DSSProgram.INTRINSICS);
            i.install(alerts);
            i.defineConstant("patientId", patientId);
            i.interpret(rule);
        }
        
        // Return all alerts
        return alerts.results();
    }
    
    private File getFile(String name) {
        File dataDir = 
                OpenmrsUtil.getDirectoryInApplicationDataDirectory(RULE_FOLDER);
        
        String absolutePath = dataDir.getAbsolutePath() + File.separator +
                name;
        
        return new File(absolutePath);
    }
    
    private String toFileName(String ruleName) {
        return ruleName.replaceAll("\\w", "");
    }

    private void loadRules() {
        // Assemble a list of expected rules
        File indexFile = getFile(INDEX_FILE);
        List<String> ruleNames = new ArrayList<String>();
        try {
            BufferedReader reader = 
                    new BufferedReader (new FileReader(indexFile));
            String ruleName;
            while ( (ruleName = reader.readLine()) != null) {
                ruleNames.add(ruleName.replaceAll("\n", ""));
            }
        } catch (Exception e) {
            // Okay, the index may not exist yet
        }
        
        // Now load all rules from XML, and get files for source codes
        for (String ruleName : ruleNames) {
            String fileName = toFileName(ruleName);
            File srcFile = getFile(fileName + SOURCE_SUFFIX);
            File xmlFile = getFile(fileName + XML_SUFFIX);
            try {
                sourceFiles.put(ruleName, srcFile);                
                rules.put(ruleName, new XMLBuilder(xmlFile).getAST().get(0));
            } catch (Exception e) {
                System.err.println("Expected but did not find source & xml "
                        + "files for " + ruleName);
            }
        }
    }    
    
    private class DSSAlertMap extends AnnotatedDSSLibrary {
        private Map<String, List<String>> alerts = 
                new HashMap<String,List<String>>();
        
        @DSSIntrinsic
        public void alert (@DSSIdentifier String target, String alertText) {
            if (!alerts.containsKey(target)) {
                alerts.put(target, new ArrayList<String>());
            }
            alerts.get(target).add(alertText);
        }
        
        public Map<String, List<String>> results() {
            return alerts;
        }
    }
}
