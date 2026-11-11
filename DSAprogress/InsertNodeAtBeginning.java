public class InsertNodeAtBeginning {
    public static class Node {
        int data;
        Node next;

        Node() {
            this.data = 0;
            this.next = null;
        }

        Node(int data) {
            this.data = data;
            this.next = null;
        }

        Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public Node insertAtHead(Node head, int newValue) {
        Node newNode = new Node(newValue, head);
        return newNode;
    }
}
