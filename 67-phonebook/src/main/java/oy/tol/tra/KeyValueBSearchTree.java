package oy.tol.tra;

import java.util.concurrent.atomic.AtomicInteger;

public class KeyValueBSearchTree<K extends Comparable<K>, V> implements Dictionary<K, V> {

    // This is the BST implementation, KeyValueHashTable has the hash table
    // implementation

    private class Node<Key extends Comparable<Key>, Value> {
        Key key;
        Value value;
        Node<K, V> leftChild;
        Node<K, V> rightChild;
        Node<K, V> next;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            leftChild = null;
            rightChild = null;
            next = null;
        }
    }

    private Node<K, V> root = null;
    private int counter = 0;
    private int maxList = 0;
    private int collisions = 0;
    private int overlapped = 0;

    @Override
    public Type getType() {
        return Type.BST;
    }

    @Override
    public int size() {
        return counter;
    }

    /**
     * Prints out the statistics of the tree structure usage.
     * Here you should print out member variable information which tell something
     * about
     * your implementation.
     * <p>
     * For example, if you implement this using a hash table, update member
     * variables of the class
     * (int counters) in add(K) whenever a collision happen. Then print this counter
     * value here.
     * You will then see if you have too many collisions. It will tell you that your
     * hash function
     * is good or bad (too much collisions against data size).
     */
    @Override
    public String getStatus() {
        String status = "Binary search tree filled with " + counter + " elements. \nThere were " + collisions
                + " collisions and the longest linked list was " + maxList + " elements long\nData was overwritter "
                + overlapped + " times.";
        return status;
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Added elements cannot be null");
        }
        if (root == null) {
            root = new Node<>(key, value);
            counter++;
            return true;
        }
        Node<K, V> current = root;
        Node<K, V> parent = null;
        while (current != null) {
            parent = current;
            if (key.hashCode() < current.key.hashCode()) {
                current = current.leftChild;
            } else if (key.hashCode() > current.key.hashCode()) {
                current = current.rightChild;
            } else if (value.equals(current.value)) {
                current.key = key;
                current.value = value;
                overlapped++;
                return true;
            } else {
                int height = 1;
                while (current.next != null) {
                    current = current.next;
                    height++;
                }
                if (height > maxList) {
                    maxList = height;
                }
                current.next = new Node<>(key, value);
                counter++;
                collisions++;
                return true;
            }
        }
        if (key.hashCode() > parent.key.hashCode()) {
            parent.rightChild = new Node<>(key, value);
        } else {
            parent.leftChild = new Node<>(key, value);
        }
        counter++;
        return true;
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        if (root == null) {
            return null;
        }
        Node<K, V> current = root;
        while (current != null && current.key.hashCode() != key.hashCode()) {
            if (key.hashCode() > current.key.hashCode()) {
                current = current.rightChild;
            } else {
                current = current.leftChild;
            }

        }
        if (current == null) {
            return null;
        }
        if (!current.key.equals(key)) {
            while (current.next != null) {
                if (current.next.key.equals(key)) {
                    return current.next.value;
                }
                current = current.next;
            }
        }
        return current.value;
    }

    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
    }

    @Override
    @java.lang.SuppressWarnings({ "unchecked" })
    public Pair<K, V>[] toSortedArray() {
        Pair<K, V>[] array = (Pair<K, V>[]) new Pair[counter];
        AtomicInteger index = new AtomicInteger(-1);
        sortInOrder(root, array, index);
        Algorithms.fastSort(array);
        return array;
    }

    private void sortInOrder(Node<K, V> current, Pair<K, V>[] array, AtomicInteger index) {
        if (current == null) {
            return;
        }
        sortInOrder(current.leftChild, array, index);
        array[index.incrementAndGet()] = new Pair<>(current.key, current.value);
        if (current.next != null) {
            Node<K, V> currentTemp = current;
            while (currentTemp.next != null) {
                array[index.incrementAndGet()] = new Pair<>(currentTemp.next.key, currentTemp.next.value);
                currentTemp = currentTemp.next;
            }
        }
        sortInOrder(current.rightChild, array, index);
    }

    @Override
    public void compress() throws OutOfMemoryError {
    }

}
