package cs143;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class SortedDecimalMap<E extends DecimalSortable>
        implements DecimalSearchTree<E> {

    //private fields
    private DecimalNode root;
    private int digitCount;

    /**
     * Constructor.
     *
     * @param digitCount the largest number of digits a sorting key will contain
     * in this sorted decimal map
     */
    public SortedDecimalMap(int digitCount) {
        root = new DecimalNode();
        this.digitCount = digitCount;
    }

    @Override
    public boolean contains(int key) {
        if (key < 0) {
            return false;
        }

        String theKey = String.valueOf(key);
        if (theKey.length() > this.digitCount) {
            return false;
        }

        if (theKey.length() < this.digitCount) {
            int diff = this.digitCount - theKey.length();
            while (diff > 0) {
                theKey = "0" + theKey;
                diff--;
            }
        }

        DecimalNode temp = root;

        int counter = 0;
        do {
            if (temp.children[(int) (theKey.charAt(counter) - '0')] == null) {
                return false;
            } else {
                temp = temp.children[(int) (theKey.charAt(counter) - '0')];
                counter++;
            }
        } while (counter < theKey.length());

        if (temp.value == null) {
            return false;
        }

        return true;
    }

    @Override
    public E get(int key) {
        if (key < 0) {
            return null;
        }

        String theKey = String.valueOf(key);

        if (theKey.length() > this.digitCount) {
            return null;
        } else if (theKey.length() < this.digitCount) {
            int diff = this.digitCount - theKey.length();
            while (diff > 0) {
                theKey = "0" + theKey;
                diff--;
            }
        }

        DecimalNode temp = root;

        int counter = 0;
        do {
            if (temp.children[(int) (theKey.charAt(counter) - '0')] == null) {
                return null;
            } else {
                temp = temp.children[(int) (theKey.charAt(counter) - '0')];
            }

            counter++;
        } while (counter < theKey.length());

        if (temp.value == null) {
            return null;
        }

        return (E) temp.value;
    }

    @Override
    public boolean insert(E e) {
        if (e.getKey() < 0) {
            return false;
        }

        String key = String.valueOf(e.getKey());

        if (key.length() > this.digitCount) {
            return false;
        } else if (key.length() < this.digitCount) {
            int diff = this.digitCount - key.length();
            while (diff > 0) {
                key = "0" + key;
                diff--;
            }
        }

        DecimalNode temp = root;

        int counter = 0;
        do {
            int number = (int) (key.charAt(counter) - '0');
            if (temp.children[number] == null) {
                temp.makeChild(number);
                temp = temp.children[number];
            } else {
                temp = temp.children[number];
            }
            counter++;
        } while (counter < key.length());

        //assign value to the node if the node has no value(no duplicate)
        if (temp.value != null) {
            return false;
        }

        temp.value = e;

        return true;
    }

    @Override
    public boolean remove(int key) {
        if (key < 0) {
            return false;
        }

        //find the node
        String theKey = String.valueOf(key);

        if (theKey.length() > this.digitCount) {
            return false;
        } else if (theKey.length() < this.digitCount) {
            int diff = this.digitCount - theKey.length();
            while (diff > 0) {
                theKey = "0" + theKey;
                diff--;
            }
        }

        DecimalNode temp = root;

        int counter = 0;
        do {
            if (temp.children[(int) (theKey.charAt(counter) - '0')] == null) {
                return false;
            } else {
                temp = temp.children[(int) (theKey.charAt(counter) - '0')];
                counter++;
            }
        } while (counter < theKey.length());

        temp.value = null;

        return true;
    }

    /**
     * Reports if the tree is empty or not.
     *
     * @return true if the tree is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return isEmpty(root, true);
    }

    /**
     * Private recursive method to determine if the tree is empty.
     *
     * @param node the current node the recursion is on
     * @return false if a value is found in the tree, true if recursion is
     * complete and no values were found
     */
    private boolean isEmpty(DecimalNode node, boolean empty) {
        if (node.value != null) {
            return false;
        } else {
            for (int i = 0; i < 10; i++) {
                if (node.children[i] != null) {
                    empty = empty && isEmpty(node.children[i], empty);
                }
            }
        }
        return empty;
    }

    /**
     * Provides access to a type-specific iterator.
     *
     * @return a new iterator object
     */
    @Override
    public Iterator<E> iterator() {
        return new DecimalIterator();
    }

    /**
     * An inner class that defines a type-specific iterator. Uses a queue
     * internally to manage iterating through the tree.
     */
    private class DecimalIterator implements Iterator<E> {

        //private fields
        private Queue<E> queue;
        private E lastRemoved;

        /**
         * Default constructor.
         */
        public DecimalIterator() {
            fillQueue();
        }

        /**
         * A private method used to fill the queue.
         */
        private void fillQueue() {
            queue = new LinkedList<>();
            fillQueue(root);
        }

        /**
         * A private recursive method to fill the queue with the value of each
         * node in sorted order.
         *
         * @param node the current node in the recursive process
         */
        private void fillQueue(DecimalNode node) {
            if (node.value != null) {
                queue.add((E) node.value);
            }

            for (int i = 0; i <= 9; i++) {
                if (node.children[i] != null) {
                    fillQueue(node.children[i]);
                }
            }
        }

        /**
         * Determines if there is a next value in the map.
         *
         * @return true if there is a next value, false if not
         */
        @Override
        public boolean hasNext() {
            if (queue.isEmpty()) {
                return false;
            }
            return true;
        }

        /**
         * Provides access to the next value in the map, in sorted order.
         *
         * @return the next value
         */
        @Override
        public E next() {
            lastRemoved = queue.poll();
            return lastRemoved;
        }

        /**
         * Removes the last reported value from the map.
         */
        @Override
        public void remove() {
            SortedDecimalMap.this.remove(lastRemoved.getKey());
        }
    }

    /**
     * An inner class that defines the decimal tree node.
     */
    private class DecimalNode<E> {

        /**
         * the value stored in this node - will be null for all nodes except the
         * lowest-level leaf nodes
         */
        public E value;

        /**
         * the array used to store the children of this node
         */
        public DecimalNode[] children;

        /**
         * Default constructor.
         */
        public DecimalNode() {
            children = new DecimalNode[10];
        }

        /**
         * A convenience method to create a new node at the given index of the
         * current node.
         *
         * @param index the index of the children array where the new node
         * should be stored
         */
        public void makeChild(int index) {
            children[index] = new DecimalNode();
        }
    }

}
