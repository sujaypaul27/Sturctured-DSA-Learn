public class ImplementStackUsingArray {
    private int[] arr;
    private int top;
    private int capacity;

    public ImplementStackUsingArray() {
        this(1000);
    }

    public ImplementStackUsingArray(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.top = -1;
    }

    public void push(int x) {
        if (top >= capacity - 1) {
            return;
        }
        arr[++top] = x;
    }

    public int pop() {
        if (isEmpty()) {
            return -1;
        }
        return arr[top--];
    }

    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }
}
