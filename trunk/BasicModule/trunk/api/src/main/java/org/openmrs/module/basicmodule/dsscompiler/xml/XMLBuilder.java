package org.openmrs.module.basicmodule.dsscompiler.xml;


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
import org.openmrs.module.basicmodule.dsscompiler.ast.AST;
import org.openmrs.module.basicmodule.dsscompiler.lexer.Symbol;
import org.openmrs.module.basicmodule.dsscompiler.lexer.Token;
import org.openmrs.module.basicmodule.dsscompiler.lexer.Tokens;
import org.w3c.dom.*;

/**
 *
 * @author woeltjen
 */
public class XMLBuilder {
    public static final String KIND_ATTR = "kind";
    public static final String VALUE_ATTR = "value";
    
    private Document document;
    
    public XMLBuilder() throws ParserConfigurationException {
        document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
    }
    
    public void addTree(AST tree) {
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
    
    public void write(OutputStream output) throws Exception {
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(document), new StreamResult(output));
    }
    
    public List<AST> getAST() throws Exception {
        return getAST(document);
    }
    
    private List<AST> getAST(Node parent) throws Exception {
        List<AST> asts = new ArrayList<AST>();
        NodeList list = parent.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            Node node = list.item(i);
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
        }
        return asts;
    }
}
