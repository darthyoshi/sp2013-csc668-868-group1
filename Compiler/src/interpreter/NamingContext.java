package interpreter;

/**
 *
 * @author woeltjen
 */
public interface NamingContext {
    public DSSValue get(String name);
    public void     set(String name, DSSValue value);
    public String[] names();
}
