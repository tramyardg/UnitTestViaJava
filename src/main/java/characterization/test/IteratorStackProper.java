package characterization.test;

import java.util.Iterator;

public class IteratorStackProper<E> implements Iterator<E> {

    private StackProper<E> stack;
    
    public IteratorStackProper(StackProper<E> stack) {
	this.stack = stack;
    }
    
    @Override
    public boolean hasNext() {
	return !stack.isEmpty();
    }

    @Override
    public E next() {
	// when the iterator moves to the next element it pops
	return stack.pop();
    }

}
