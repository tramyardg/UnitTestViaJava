package characterization.test;

import java.util.Iterator;
import java.util.Stack;

@SuppressWarnings("serial")
public class StackProper<E> extends Stack<E> {

    @Override
    public synchronized Iterator<E> iterator() {
        // old behaving iterator
        // return super.iterator()
        return new IteratorStackProper<E>(this);
    }
}
