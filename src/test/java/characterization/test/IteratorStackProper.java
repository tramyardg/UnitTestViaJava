package characterization.test;

import java.util.Iterator;

public class IteratorStackProper<E> implements Iterator<E> {

    private TestStackProper<E> stack;
    
    public IteratorStackProper(TestStackProper<E> stack) {
	this.stack = stack;
    }
    
    @Override
    public boolean hasNext() {
	return !stack.isEmpty();
    }

    @Override
    public E next() {
	return stack.pop();
    }

}
