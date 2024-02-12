import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Node {
    private Node leftChild;
    private Node rightChild;
    private final String value;

    private Node parent = null;

    public Node(String valueArg) {
        this.value = valueArg;
    }

    public String getValue() { return this.value; }

    public Node getLeftChild() {
        return this.leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setParent(Node parentArg) {
        this.parent = parentArg;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
        this.leftChild.setParent(this);
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
        this.rightChild.setParent(this);
    }

    public String toString() {
        String returnString = "Value: ";
        returnString += this.value + "\n";
        if(hasLeft()) {
            returnString += getLeftChild();
        }
        if(hasRight()) {
            returnString += getRightChild();
        }
        return returnString;
    }

    public String toFIFO() {
        Queue<Node> queue = new LinkedList<>();
        String returnString = "\n";
        queue.add(this);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            returnString += "Value: " + current.value + "\n";
            if (current.leftChild != null) queue.add(current.leftChild);
            if (current.rightChild != null) queue.add(current.rightChild);
        }
        return returnString;
    }

    public String toLIFO() {
        Stack<Node> stack = new Stack<>();
        String returnString = "\n";
        stack.push(this);
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            returnString += "Value: " + current.value + "\n";
            if (current.rightChild != null) stack.push(current.rightChild); // Right child is pushed first
            if (current.leftChild != null) stack.push(current.leftChild); // so that the left child is processed first
        }
        return returnString;
    }


    public boolean hasLeft() {
        return (leftChild != null);
    }

    public boolean hasRight() {
        return (rightChild != null);
    }

    public static int getNumberOfNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + getNumberOfNodes(node.getLeftChild()) + getNumberOfNodes(node.getRightChild());
    }

    public static int countLeafNodes(Node node) {
        if (node == null) {
            return 0;
        }
        if (!node.hasLeft() && !node.hasRight()) {
            return 1;
        }
        return countLeafNodes(node.getLeftChild()) + countLeafNodes(node.getRightChild());
    }
}
