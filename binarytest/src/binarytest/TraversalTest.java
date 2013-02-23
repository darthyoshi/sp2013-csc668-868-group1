package binarytest;

import java.util.*;
/**
 * Class TraversalTest tests an algorithm for traversing a binary tree. Currently
 * only in-order traversals have been implemented.
 * @author Kay Choi
 */
public class TraversalTest {

    /**
     * Main method. Instantiates and populates a binary tree, and traverses it.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BinaryNode[] node = new BinaryNode[9];
        for(int i = 0; i < 9 ; i++) {
            node[i] = new BinaryNode(Integer.toString(i+1));
        }
        node[0].addLeftKid(node[1]);
        node[0].addRightKid(node[5]);
        node[1].addLeftKid(node[2]);
        node[1].addRightKid(node[3]);
        node[3].addLeftKid(node[4]);
        node[5].addLeftKid(node[6]);
        node[6].addLeftKid(node[7]);
        node[6].addRightKid(node[8]);

        TraversalTest test = new TraversalTest();

        test.inOrderTraversal(node[0]);
    }

    /**
     * Traverses a binary tree in order (root -> left subtree -> right subtree)
     * from a specified node.
     * @param node the node in the tree to start traversal from
     */
    void inOrderTraversal (BinaryNode node) {
        Stack<BinaryNode> stack = new Stack<>();
        BinaryNode cur = node;
        BinaryNode prev;
        Boolean backTrack = false;

        do{
            System.out.println(cur.getLabel());

            //traverse into left subtree if available
            if(cur.getLeft() != null) {
                stack.push(cur);
                cur = cur.getLeft();
            }

            //traverse into right subtree if available
            else if(cur.getRight() != null) {
                stack.push(cur);
                cur = cur.getRight();
            }

            //backtack to previous level in tree
            else {
                do{
                    prev = cur;
                    cur = stack.peek();

                    //if backtracking from left subtree, attempt to traverse into right subtree
                    if(prev.equals(cur.getLeft()) && cur.getRight() != null) {
                        prev = cur;
                        cur = cur.getRight();
                    }

                    //backtrack to previous level in tree
                    else {
                        prev = stack.pop();
                        if(!stack.isEmpty()) {
                            cur = stack.pop();
                        }
                        else {
                            cur = null;
                        }
                    }

                    //update break condition - traversal has entered a subtree
                    if(cur != null) {
                        backTrack = cur.getLeft() == prev || cur.getRight() == prev;
                    }
                }while(backTrack && cur != null);
            }
        }while(cur != null);
    }
}
