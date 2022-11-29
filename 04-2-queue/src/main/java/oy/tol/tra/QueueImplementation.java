package oy.tol.tra;

import javax.smartcardio.CommandAPDU;

public class QueueImplementation<E> implements QueueInterface<E> {
    private static final int DEFAULT_SIZE = 10; 
    private int capacity = DEFAULT_SIZE;
    private int head = 0;
    private int tail = 0;
    private Object[] itemArray;


    public QueueImplementation(int capacity){
        this.capacity = capacity;
        itemArray = new Object[capacity];
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if(element == null){
            throw new NullPointerException("The enqued element cannot be null.");
        }
        if(head == tail && itemArray[head] != null){
            Object[] temp = new Object[capacity * 2];

            for(int n = 0; n <= capacity; n++){
                temp[n] = itemArray[head];
                if(head == capacity - 1){
                    head = 0;
                }
                else{
                    head++;
                }
            }
            itemArray = temp;
            tail = capacity;
            head = 0;
            capacity = capacity * 2;
        }
        itemArray[tail] = element;
        if(tail == capacity - 1){
            tail = 0;
        }
        else{
            tail++;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E dequeue() throws QueueIsEmptyException {
        if(itemArray[head] == null){
            throw new QueueIsEmptyException("The queue is empty.");
        }
        E element = (E)itemArray[head];
        itemArray[head] = null;
        if(head == capacity - 1){
            head = 0;
        }
        else{
            head++;
        }
        return element;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E element() throws QueueIsEmptyException {
        if(isEmpty()){
            throw new QueueIsEmptyException("The queue is empty");
        }
        return ((E)itemArray[head]);
    }

    @Override
    public int size() {
        int size;
        if(head == tail && itemArray[head] != null){
            size = capacity;
        }
        else if(head > tail){
            size = capacity - (head - tail);
        }
        else{
            size = tail - head;
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (itemArray[head] == null);
    }

    @Override
    public void clear() {
        itemArray = new Object[capacity];
        head = 0;
        tail = 0;
    }

    @Override
    public String toString(){
        if(itemArray[head] == null){
            return "[]";
        }
        StringBuilder temp = new StringBuilder("[" + itemArray[head]);
        for(int n = 0, index = head; n < size() - 1; n++){
            if(index == capacity - 1){
                index = 0;
            }
            else{
                index++;
            }
            temp.append(", " + itemArray[index]);
        }
        temp.append("]");
        return temp.toString();
    }
    
}
