import java.util.Stack;

public class MinStack {
    private Stack<Long> stack;
    private long min;

    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(0L);
            min = val;
        } else {
            stack.push((long) val - min);
            if (val < min) {
                min = val;
            }
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        long diff = stack.pop();
        if (diff < 0) {
            min = min - diff;
        }
    }

    public int top() {
        long diff = stack.peek();
        if (diff > 0) {
            return (int) (min + diff);
        }
        return (int) min;
    }

    public int getMin() {
        return (int) min;
    }
}
