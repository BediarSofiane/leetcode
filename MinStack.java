import java.util.ArrayList;
import java.util.List;

public class MinStack {
    List<Integer> list = new ArrayList<>();
    List<Integer> mins = new ArrayList<>();

    public MinStack() {
        this.mins.add(Integer.MAX_VALUE);
    }
    
    public void push(int val) {
        int previousMin = mins.get(list.size());
        this.mins.add(Math.min(previousMin, val));
        this.list.add(val);
    }
    
    //No Check for empty list because garanteed by exercice that the call will only be made on non empty stack
    public void pop() {
        if(!this.list.isEmpty()){
            this.mins.remove(list.size());
            this.list.remove(list.size() - 1);
        }
    }
    
    //No Check for empty list because garanteed by exercice that the call will only be made on non empty stack
    public int top() {
        return this.list.get(this.list.size() - 1);
    }
    //No Check for empty list because garanteed by exercice that the call will only be made on non empty stack
    public int getMin() {
        return this.mins.get(list.size());
    }
}


/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */