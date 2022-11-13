package oy.tol.tra;

import java.rmi.server.ObjID;

/**
 * An implementation of the StackInterface.
 * <p>
 * 
 * Note that you need to implement construtor(s) for your concrete StackImplementation, which
 * allocates the internal Object array for the Stack:
 * - a default constructor, calling the StackImplementation(int size) with value of 10.
 * - StackImplementation(int size), which allocates an array of Object's with size.
 *  -- remember to maintain the capacity and/or currentIndex when the stack is manipulated.
 */
public class StackImplementation<E> implements StackInterface<E> {
   private static final int DEFAULT_SIZE = 10;
   private int capacity = DEFAULT_SIZE;
   private int currentIndex = 0;
   private Object [] itemArray;
   // Do not use constant values in code, e.g. 10. Instead, define a constant for that. For example:
   // private static final int MY_CONSTANT_VARIABLE = 10;


   /**
    * Allocates a stack with a default capacity.
    * @throws StackAllocationException
    */
   public StackImplementation() throws StackAllocationException {
      itemArray = new Object[DEFAULT_SIZE];
   }

   /** 
    * - if the size is less than 2, throw StackAllocationException
    * - if the allocation of the array throws with Java exception,
    *   throw StackAllocationException.
    * @param capacity The capacity of the stack.
    * @throws StackAllocationException If cannot allocate room for the internal array.
    */
   public StackImplementation(int capacity) throws StackAllocationException {
      if(capacity < 2){
         throw new StackAllocationException("Capacity must be more than 2.");
      }
      this.capacity = capacity;
      itemArray = new Object[capacity];
   }

   @Override
   public int capacity() {
      return capacity;
   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
      if(element == null){
         throw new NullPointerException("Pushed element cannnot be null.");
      }
      if(currentIndex == capacity){
         capacity = capacity * 2;
         Object[] temp = new Object[capacity];
         for(int n = 0; n < currentIndex; n++){
            temp[n] = itemArray[n];
         }
         itemArray = temp;
      }
      itemArray[currentIndex] = element;
      currentIndex++;
   }

   @SuppressWarnings("unchecked")
   @Override
   public E pop() throws StackIsEmptyException {
      if(currentIndex == 0){
         throw new StackIsEmptyException("The stack is empty.");
      }
      currentIndex--;
      E element =(E) itemArray[currentIndex];
      itemArray[currentIndex] = null;
      return element; 
   }

   @SuppressWarnings("unchecked")
   @Override
   public E peek() throws StackIsEmptyException {
      if(currentIndex == 0){
         throw new StackIsEmptyException("The stack is empty.");
      }
      return ((E) itemArray[currentIndex - 1]); 
   }

   @Override
   public int size() {
      return currentIndex;
   }

   @Override
   public void clear() {
      while(currentIndex > 0){
         itemArray[currentIndex - 1] = null;
         currentIndex--;
      }
   }

   @Override
   public boolean isEmpty() {
      return (currentIndex == 0);
   }

   @Override
   public String toString() {
      if(itemArray[0] == null){
         return "[]";
      }
      String temp = "[" + itemArray[0];
      for(int n = 1; n < currentIndex; n++){
         if(itemArray[n] != null){
            temp = temp + ", " + itemArray[n];
         }
      }
      temp = temp + "]";
      return temp;
   }
}
