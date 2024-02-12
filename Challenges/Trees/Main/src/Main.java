import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {
        // Creating nodes
        Node root = new Node("root");
        Node node1 = new Node("node1");
        Node node2 = new Node("node2");
        Node leaf1 = new Node("leaf1");
        Node leaf2 = new Node("leaf2");

        // Constructing the tree
        root.setLeftChild(node1);
        root.setRightChild(node2);
        node1.setLeftChild(leaf1);
        node1.setRightChild(leaf2);

        // Utilizing the methods
        int numberOfNodes = Node.getNumberOfNodes(root);
        int numberOfLeafNodes = Node.countLeafNodes(root);

        // Displaying the results
        System.out.println(root);
        System.out.println("Total number of nodes in the tree: " + numberOfNodes);
        System.out.println("Total number of leaf nodes in the tree: " + numberOfLeafNodes);
        System.out.println("\n");

        // Queue
        System.out.println("Printing the Tree as a Queue:");
        System.out.println(root.toFIFO());

        // Stack
        System.out.println("Printing the Tree as a Stack:");
        System.out.println(root.toLIFO());
    }
}