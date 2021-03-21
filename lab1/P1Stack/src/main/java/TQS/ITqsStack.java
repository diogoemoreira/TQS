package TQS;

import java.util.LinkedList;

public class ITqsStack<T> {
    LinkedList<T> stack;
    int bound=-1;

    public ITqsStack(){
        stack = new LinkedList<>();
    }

    public void push(T e){
        if(bound!=-1 && stack.size()>=bound){
            throw new IllegalStateException();
        }
        stack.add(e);
    }

    public T pop(){
        return stack.removeLast();
    }

    public T peek(){
        return stack.getLast();
    }

    public int size(){
        return stack.size();
    }

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public void setBound(int n){
        bound=n;
    }

    public int getBound(){
        return bound;
    }
}
