package binarytest;

/**
 * Class BinaryNode is a node in a binary tree.
 * @author Kay Choi
 */
public class BinaryNode {
    private BinaryNode leftTree;
    private BinaryNode rightTree;
    private String label;

    /**
     * Class constructor. Initializes the BinaryNode and applies a label to it.
     * @param label the value of the node
     */
    BinaryNode(String label) {
        this.label = label;
    }

    /**
     * Adds a subtree as the left child tree of the BinaryNode.
     * @param kid the root node of the binary tree to be added
     */
    void addLeftKid(BinaryNode kid) {
        leftTree = kid;
    }

    /**
     * Adds a subtree as the right child tree of the BinaryNode.
     * @param kid the root node of the binary tree to be added
     */
    void addRightKid(BinaryNode kid) {
        rightTree = kid;
    }

    /**
     * Retrieves the left subtree of the BinaryNode.
     * @return the root node of the left child tree
     */
    BinaryNode getLeft() {
        return leftTree;
    }

    /**
     * Retrieves the right subtree of the BinaryNode.
     * @return the root node of the right child tree
     */
    BinaryNode getRight() {
        return rightTree;
    }

    /**
     * Retrieves the label of the BinaryNode.
     * @return the label of the node
     */
    String getLabel() {
        return label;
    }
}
