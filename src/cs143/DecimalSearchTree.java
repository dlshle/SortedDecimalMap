package cs143;

public interface DecimalSearchTree<E> extends Iterable<E> {
    
    public boolean contains(int key);
    
    public E get(int key);

    public boolean insert(E e);

    public boolean remove(int key);

    public boolean isEmpty();
    
}
