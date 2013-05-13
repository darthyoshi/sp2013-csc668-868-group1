package org.openmrs.module.dssmodule;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.openmrs.module.dssmodule.ast.AST;
import org.openmrs.module.dssmodule.lexer.Symbol;
import org.openmrs.module.dssmodule.lexer.Token;
import org.openmrs.module.dssmodule.lexer.Tokens;
import org.w3c.dom.*;

/**
 * Supports bi-directional conversion between XML and AST representations 
 * of DSS1 programs, using DOM as an intermediary.
 * @author woeltjen
 */
public class DSSXMLConvertor {
    public static final String KIND_ATTR = "kind";
    public static final String VALUE_ATTR = "value";
    
    private Document document;
    
    /**
     * Crate a new Convertor, initialized from an existing XML file.
     * @param file an XML file containing a compiled DSS1 program
     * @throws Exception 
     */
    public DSSXMLConvertor(File file) throws Exception {        
        document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
    }
    
    /**
     * Crate a new Convertor, initialized from an existing AST.
     * @param tree the compiled AST which represents the DSS1 program
     * @throws ParserConfigurationException 
     */
    public DSSXMLConvertor(AST tree) throws ParserConfigurationException {
        this();
        addTree(tree);
    }
    
    /**
     * Create a convertor with no program.
     * @throws ParserConfigurationException 
     */
    public DSSXMLConvertor() throws ParserConfigurationException {
        document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    }
    
    private void addTree(AST tree) {
        addTree(tree, document);
    }
    
    private void addTree(AST tree, Node parent) {
        Element treeElement = document.createElement(tree.getClass().getName());
        parent.appendChild(treeElement);
        
        try {
            Method getSymbol = tree.getClass().getMethod("getSymbol");
            Symbol symbol = (Symbol) getSymbol.invoke(tree);
            Attr kind = document.createAttribute(KIND_ATTR);
            kind.setValue(symbol.getKind().name());
            Attr value = document.createAttribute(VALUE_ATTR);
            value.setValue(symbol.toString());
            treeElement.setAttributeNode(kind);
            treeElement.setAttributeNode(value);
        } catch (Exception e) {
            // Don't add those attributes, then
        }
        
        for (AST kid : tree.getKids()) {
            addTree(kid, treeElement);
        }
    }
    
    /**
     * Store this program as an XML file
     * @param outputFile the file to which to store this program
     * @throws Exception 
     */
    public void write(File outputFile) throws Exception {
        OutputStream stream = new FileOutputStream(outputFile);
        write(stream);
        stream.close();
    }
    
    /**
     * Write this program as an XML file to the specified stream
     * @param output
     * @throws Exception 
     */
    public void write(OutputStream output) throws Exception {
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(document), new StreamResult(output));
    }
    
    /**
     * Retrieve the current program in AST form
     * @return an AST representing the current program
     * @throws Exception 
     */
    public List<AST> getAST() throws Exception {
        return getAST(document);
    }
    
    private List<AST> getAST(Node parent) throws Exception {
        List<AST> asts = new ArrayList<AST>();
        NodeList list = parent.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
            try {
                Class<?> astClass = Class.forName(node.getNodeName());
                NamedNodeMap attrs = node.getAttributes();
                AST ast = null;
                try {
                    String kind = attrs.getNamedItem(KIND_ATTR).getNodeValue();
                    String value = attrs.getNamedItem(VALUE_ATTR).getNodeValue();
                    ast = (AST) astClass.getConstructor(Token.class).newInstance(
                            new Token(0,0,Symbol.symbol(value, Tokens.valueOf(kind))));
                } catch (Exception e) {
                    ast = (AST) astClass.newInstance();
                }
                if (ast != null) {
                    List<AST> children = getAST(node);
                    for (AST child : children) {
                        ast.addKid(child);
                    }
                    asts.add(ast);
                }
            } catch (ClassNotFoundException cnfe) {
                System.err.println("XMLBuilder Skipping node due to exception " + 
                        cnfe.getMessage());
            }
        }
        return asts;
    }
}
