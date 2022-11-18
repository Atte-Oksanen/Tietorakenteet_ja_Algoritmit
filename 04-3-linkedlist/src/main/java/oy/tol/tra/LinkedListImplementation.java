package oy.tol.tra;

public class LinkedListImplementation<E> implements LinkedListInterface<E>  {

   private class Node<T> {
      T element;
      Node<T> next;

      Node(T data) {
         element = data;
         next = null;
      }

      @Override
      public String toString() {
         return element.toString();
      }
   }

   private Node<E> head = null;
   private int size = 0;

   @Override
   public void add(E element) throws NullPointerException, LinkedListAllocationException {
      if(element == null){
         throw new NullPointerException("The element cannot be null.");
      }
      if(head == null){
         head = new Node<E>(element);
      }
      else{
         Node<E> current = head;
         while(current.next != null){
            current = current.next;
         }

         current.next = new Node<E>(element);
      }
      size++;
   }

   @Override
   public void add(int index, E element) throws NullPointerException, LinkedListAllocationException, IndexOutOfBoundsException {
      if(element == null){
         throw new NullPointerException("The element cannot be null.");
      }
      if(index > size || index < 0){
         throw new IndexOutOfBoundsException("The provided index is out of bounds.");
      }

      if(index == 0 && head == null){
         head = new Node<E>(element);
         size++;
      }
      else if(index == 0){
         Node<E> tempHead = new Node<E>(element);
         tempHead.next = head;
         head = tempHead;
      }
      else{
         Node<E> current = head;

         for(int n = 1; n < index; n++){
            current = current.next;
         }
         Node<E> temp = current.next;
         current.next = new Node<E>(element);
         current.next.next = temp;
         size++;
      }

   }
   
   @Override
   public boolean remove(E element) throws NullPointerException {
      if(element == null){
         throw new NullPointerException("The element cannot be null");
      }
      if(head == null){
         return false;
      }
      if(head.element.equals(element)){
         head = head.next;
         size--;
         return true;
      }

      Node<E> current = head;
      while(current.next != null){
         if(current.next.element.equals(element)){
            current.next = current.next.next;
            size--;
            return true;
         }
         current = current.next;
      }
      return false;
   }
   
   @Override
   public E remove(int index) throws IndexOutOfBoundsException {
      if(index > size - 1 || index < 0){
         throw new IndexOutOfBoundsException("The provided index is out of bounds.");
      }
      if(head == null){
         throw new IndexOutOfBoundsException("The list is empty");
      }
      Node<E> temp;
      if(index == 0){
         temp = head;
         head = head.next;
         size--;
      }
      else{
         Node<E> current = head;
         for(int n = 0; n != index - 1 && current != null ; n++){
            current = current.next;
         }
         temp = current.next;
         current.next = current.next.next;
         size--;
      }
      return temp.element;
      
   }
   
   @Override
   public E get(int index) throws IndexOutOfBoundsException {
      if(index > size - 1 || index < 0){
         throw new IndexOutOfBoundsException("The provided index is out of bounds.");
      }
      if(head == null){
         throw new IndexOutOfBoundsException("The list is empty.");
      }
      else{
         Node<E> current = head;
         for(int n = 0; current != null && n != index; n++){
            current = current.next;
         }
         return current.element;
      }
   }

   @Override
   public int indexOf(E element) throws NullPointerException {
      if(element == null){
         throw new NullPointerException("The element cannot be null");
      }
      if(head == null){
         return -1;
      }
      if(head.element.equals(element)){
         return 0;
      }

      Node<E> current = head;
      for(int n = 1; current.next != null; n++){
         if(current.next.element.equals(element)){
            return n;
         }
         current = current.next;
      }
      return -1;
   }

   @Override
   public int size() {
      return size;
   }

   @Override
   public void clear() {
      head = null;
      size = 0;
   }

   @Override
   public void reverse() {
      Node<E> prev = null, current = head, next = null;

      while(current != null){
         next = current.next;
         current.next = prev;
         prev = current;
         current = next;
      }
      head = prev;
   }

   @Override
   public String toString(){
      if(head == null){
         return "[]";
      }
      String temp = "[" + head.element;
      try{
         Node<E> current = head;
         int count = 0;
         while(current.next != null){
            temp = temp + ", " + current.next;
            current = current.next;
            count++;
            System.out.println(count +" " +current.element);
         }
      } catch(NullPointerException e){}
      temp = temp + "]";
      return temp;
   }
   
}
