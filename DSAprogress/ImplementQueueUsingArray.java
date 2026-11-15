public class ImplementQueueUsingArray {
    private int[] arr;
    private int front;
    private int rear;
    private int count;
    private int capacity;

    public ImplementQueueUsingArray() {
        this(1000);
    }

    public ImplementQueueUsingArray(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.front = 0;
        this.rear = 0;
        this.count = 0;
    }

    public void push(int x) {
        if (count == capacity) {
            return;
        }
        arr[rear % capacity] = x;
        rear++;
        count++;
    }

    public int pop() {
        if (count == 0) {
            return -1;
        }
        int val = arr[front % capacity];
        front++;
        count--;
        return val;
    }

    public int peek() {
        if (count == 0) {
            return -1;
        }
        return arr[front % capacity];
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }
}
