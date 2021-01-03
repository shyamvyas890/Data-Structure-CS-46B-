package trees;


import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

@SuppressWarnings("serial")
public class BinaryTree<E> implements Serializable {

	/* <listing chapter="6" number="1"> */
	/** Class to encapsulate a tree node. */
	
	/* </listing> */
	// Data Field
	/** The root of the binary tree */
	Node<E> root;

	/** Construct an empty BinaryTree */
	public BinaryTree() {
		root = null;
	}

	/**
	 * Construct a BinaryTree with a specified root. Should only be used by
	 * subclasses.
	 * 
	 * @param root
	 *            The node that is the root of the tree.
	 */
	BinaryTree(Node<E> root) {
		this.root = root;
	}

	/**
	 * Constructs a new binary tree with data in its root,leftTree as its left
	 * subtree and rightTree as its right subtree.
	 */
	public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
		root = new Node<E>(data);
		if (leftTree != null) {
			root.left = leftTree.root;
		} else {
			root.left = null;
		}
		if (rightTree != null) {
			root.right = rightTree.root;
		} else {
			root.right = null;
		}
	}

	/**
	 * Return the left subtree.
	 * 
	 * @return The left subtree or null if either the root or the left subtree
	 *         is null
	 */
	public BinaryTree<E> getLeftSubtree() {
		if (root != null && root.left != null) {
			return new BinaryTree<E>(root.left);
		} else {
			return null;
		}
	}

	/**
	 * Return the right sub-tree
	 * 
	 * @return the right sub-tree or null if either the root or the right
	 *         subtree is null.
	 */
	public BinaryTree<E> getRightSubtree() {
		if (root != null && root.right != null) {
			return new BinaryTree<E>(root.right);
		} else {
			return null;
		}
	}

	/**
	 * Return the data field of the root
	 * 
	 * @return the data field of the root or null if the root is null
	 */
	public E getData() {
		if (root != null) {
			return root.data;
		} else {
			return null;
		}
	}

	/**
	 * Determine whether this tree is a leaf.
	 * 
	 * @return true if the root has no children
	 */
	public boolean isLeaf() {
		return (root == null || (root.left == null && root.right == null));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(root, 1, sb);
		return sb.toString();
	}

	/**
	 * Perform a preorder traversal.
	 * 
	 * @param node
	 *            The local root
	 * @param depth
	 *            The depth
	 * @param sb
	 *            The string buffer to save the output
	 */
	public void toString(Node<E> node, int depth, StringBuilder sb) {
		for (int i = 1; i < depth; i++) {
			sb.append("  ");
		}
		if (node == null) {
			sb.append("null\n");
		} else {
			sb.append(node.toString());
			sb.append("\n");
			toString(node.left, depth + 1, sb);
			toString(node.right, depth + 1, sb);
		}
	}

	/* <listing chapter="6" number="2"> */
	/**
	 * Method to read a binary tree.
	 * 
	 * @pre The input consists of a preorder traversal of the binary tree. The
	 *      line "null" indicates a null tree.
	 * @param bR
	 *            The input file
	 * @return The binary tree
	 * @throws IOException
	 *             If there is an input error
	 */
	public static BinaryTree<String> readBinaryTree(Scanner scan) {
        // Read a line and trim leading and trailing spaces.
        String data = scan.nextLine().trim();
        if (data.equals("null")) {
            return null;
        } else {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<>(data, leftTree, rightTree);
        }
    }
	public String preOrderByStrings(Node<E> node, StringBuilder sb) {
		if(node==null) {
			sb.append(" null");
		}
		else {
			sb.append(" ");
			sb.append(node.toString());
			preOrderByStrings(node.left,sb);
			preOrderByStrings(node.right,sb);
		}
		return sb.toString();
	}
	public String postOrderByStrings() {
		return postOrderByStrings(root,new StringBuilder(""));
	}
	public String postOrderByStrings(Node<E> node, StringBuilder sb) {
		if(node==null) {
			sb.append("null");
			sb.append(" ");
			return "";
		}
		else {
			postOrderByStrings(node.left,sb);
			postOrderByStrings(node.right,sb);
			sb.append(node.data);
			sb.append(" ");
		}
		return sb.toString();
	}
	public String preOrderByStrings() {
		return preOrderByStrings(root,new StringBuilder(""));
	}

	/* </listing> */

	// Insert solution to programming exercise 1, section 3, chapter 6 here
	/**
	 * Method to return the preorder traversal of the binary tree as a sequence
	 * of strings each separated by a space.
	 * 
	 * @return A preorder traversal as a string
	 */
	

	// Insert solution to programming exercise 2, section 3, chapter 6 here

	// Insert solution to programming exercise 3, section 3, chapter 6 here
	/**
	 * A method to display the inorder traversal of a binary tree placeing a
	 * left parenthesis before each subtree and a right parenthesis after each
	 * subtree. For example the expression tree shown in Figure 6.12 would be
	 * represented as (((x) + (y)) * ((a) / (b))).
	 * 
	 * @return An inorder string representation of the tree
	 */
	public String inOrderToString() {
		StringBuilder stb = new StringBuilder();
		inOrderToString(root, stb);
		return stb.toString();
	}
	public String inOrderToString(Node<E> node, StringBuilder sb) {
		if(node==null) {
			return "";
		}
		else {
			sb.append("(");
			inOrderToString(node.left,sb);
			sb.append(node.data);
			inOrderToString(node.right,sb);
			sb.append(")");
		}
		return sb.toString();
	}

	
	
	public static void main(String[] args) {
		String a= "*\n" + 
				" +\n" + 
				" x\n" + 
				" null\n" + 
				" null\n" + 
				" y\n" + 
				" null\n" + 
				" null\n" + 
				" /\n" + 
				" a\n" + 
				" null\n" + 
				" null\n" + 
				" b\n" + 
				" null\n" + 
				" null";
		Scanner b= new Scanner(a);
		BinaryTree<String> x= BinaryTree.readBinaryTree(b);
		System.out.println(x.inOrderToString());
		
		
	}
}