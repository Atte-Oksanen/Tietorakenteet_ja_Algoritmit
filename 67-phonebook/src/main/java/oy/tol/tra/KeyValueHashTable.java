package oy.tol.tra;

import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

public class KeyValueHashTable<K extends Comparable<K>, V> implements Dictionary<K, V> {
    private Pair<K, V>[] hashTable = null;
    int counter = 0;
    int reallocations = 0;
    int collisions = 0;
    int overWrites = 0;
    int collisionChain = 0;

    public KeyValueHashTable(int capacity) throws OutOfMemoryError {
        ensureCapacity(capacity);
    }

    public KeyValueHashTable() throws OutOfMemoryError {
        ensureCapacity(20);
    }

    @Override
    public Type getType() {
        return Type.HASHTABLE;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
        if (hashTable == null) {
            hashTable = (Pair<K, V>[]) new Pair[size];
        }
        if (hashTable.length < size) {
            hashTable = (Pair<K, V>[]) new Pair[size];
            counter = 0;
        }
    }

    @Override
    public int size() {
        return counter;
    }

    /**
     * Prints out the statistics of the hash table.
     * Here you should print out member variable information which tell something
     * about your implementation.
     * <p>
     * For example, if you implement this using a hash table, update member
     * variables of the class (int counters) in add() whenever a collision
     * happen. Then print this counter value here.
     * You will then see if you have too many collisions. It will tell you that your
     * hash function is not good.
     */
    @Override
    public String getStatus() {
        String status = "Table filled with " + counter + " elements. \nThe table was reallocated " + reallocations + " times. There were " + collisions
                + " collisions in the final table and the longest collision chain was " + collisionChain
                + " elements long\nData was overwritten "
                + overWrites + " times.";
        status += String.format("\nTable fill rate is %.2f%%%n", (counter / (double) hashTable.length) * 100.0);
        return status;
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        boolean collided = false;
        if(key == null || value == null){
            throw new IllegalArgumentException("Cannot add null to table.");
        }
        int collisionCount = 0;
        boolean added = false;
        if (counter > hashTable.length * 0.8) {
            reallocate();
            reallocations++;
        }

        int index = getIndex(key, collisionCount, hashTable.length);

        do {
            if (hashTable[index] == null) {
                hashTable[index] = new Pair<>(key, value);
                added = true;
                counter++;
            } else if (!hashTable[index].getKey().equals(key)) {
                index = getIndex(key, ++collisionCount, hashTable.length);
                if(!collided){
                    collisions++;
                    collided = true;
                }
            } else {
                hashTable[index].setvalue(value);
                counter++;
                added = true;
                overWrites++;
            }
        } while (!added);
        if (collisionCount > collisionChain) {
            collisionChain = collisionCount;
        }
        return true;
    }

    @java.lang.SuppressWarnings({ "unchecked" })
    private void reallocate() {
        Pair<K, V>[] oldTable = hashTable;
        hashTable = (Pair<K, V>[]) new Pair[Algorithms.primeFinder((int) (hashTable.length * 1.3))];
        counter = 0;
        collisionChain = 0;
        collisions = 0;
        int index = Algorithms.partitionByRule(oldTable, oldTable.length, element -> element == null);
        for (int n = 0; n < index; n++) {
                add(oldTable[n].getKey(), oldTable[n].getValue());
        }

    }

    private int getIndex(K key, int collisionCount, int length) {
        return (((key.hashCode() + collisionCount) & 0x7fffffff) % length);
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        if(key == null){
            throw new IllegalArgumentException("Cannot search for null.");
        }
        int collisionCount = 0;
        int index = getIndex(key, collisionCount, hashTable.length);
        V found = null;
        while (found == null) {
            if(collisionCount >= hashTable.length){
                return null;
            }
            if (hashTable[index] == null) {
                return null;
            }
            if (hashTable[index].getKey().equals(key)) {
                found = hashTable[index].getValue();
            } else {
                index = getIndex(key, ++collisionCount, hashTable.length);
            }
        }
        return found;

    }

    @Override
    @java.lang.SuppressWarnings({ "unchecked" })
    public Pair<K, V>[] toSortedArray() {
        Pair<K, V>[] toReturn = (Pair<K, V>[]) new Pair[counter];
        int index = 0;
        for (int n = 0; n < hashTable.length; n++) {
            if (hashTable[n] != null) {
                toReturn[index++] = hashTable[n];
            }
        }
        Algorithms.fastSort(toReturn);
        return toReturn;
    }

    @Override
    @java.lang.SuppressWarnings({ "unchecked" })
    public void compress() throws OutOfMemoryError {
        Pair<K, V>[] oldTable = hashTable;
        hashTable = (Pair<K, V>[]) new Pair[counter];
        int index = Algorithms.partitionByRule(oldTable, oldTable.length, element -> element == null);
        for (int n = 0; n < index; n++) {
                addWihtoutReallocation(oldTable[n].getKey(), oldTable[n].getValue());
        }
    }

    private void addWihtoutReallocation(K key, V value){
        int collisionCount = 0;
        boolean added = false;
        int index = getIndex(key, collisionCount, hashTable.length);

        do {
            if (hashTable[index] == null) {
                hashTable[index] = new Pair<>(key, value);
                added = true;
            } else if (!hashTable[index].getKey().equals(key)) {
                index = getIndex(key, ++collisionCount, hashTable.length);
            } else {
                hashTable[index].setvalue(value);
                added = true;
            }
        } while (!added);

    }

}
