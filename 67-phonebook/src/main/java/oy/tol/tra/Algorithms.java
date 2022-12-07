package oy.tol.tra;

import java.util.function.Predicate;

public class Algorithms {

    public static <T extends Comparable<T>> void sort(T [] array) {
        for(int i = 0; i < array.length; i++){
			for(int j = i + 1; j < array.length; j++){
				if(array[i].compareTo(array[j]) > 0){
					swap(array, i, j);
				}
			}
	    }
    }
    
    public static <T> void reverse(T [] array) {
      for(int i = 0; i < array.length - i; i++){
        swap(array, i, array.length-i-1);
      }
    }

    private static <T> void swap(T [] array, int indexStart, int indexEnd){
        T temp = array[indexStart];
        array[indexStart] = array[indexEnd];
        array[indexEnd] = temp;
    }

    public static <T> int partitionByRule(T [] array, int count, Predicate<T> rule){
      int index = 0;
      while(rule.test(array[index]) == false){
        index++;
      }

      if(index >= count){
        return count;
      }
      
      int nextIndex = index + 1;
      
      nextIndex = index + 1;
      while(nextIndex < count){
        if(rule.test(array[nextIndex]) == false){
          swap(array, index, nextIndex);
          index++;
        }
        nextIndex++;
      }
      return index;
    }

    public static <T extends Comparable<T>> int binarySearch(T aValue, T [] fromArray, int fromIndex, int toIndex) {
      int midIndex = -1;

      while(fromIndex <= toIndex){
        midIndex = (fromIndex + toIndex) / 2;
        if(midIndex < 0){
          midIndex = (fromIndex / 2) + (toIndex / 2);
        }
        if(aValue.compareTo(fromArray[midIndex]) == 0){
          return midIndex;
        }
        else if(aValue.compareTo(fromArray[midIndex]) > 0){
          fromIndex = midIndex + 1;
        }
        else if(aValue.compareTo(fromArray[midIndex]) < 0){
          toIndex = midIndex - 1;
        }
      }
     return -1; 
    }

    public static <T extends Comparable<T>> void fastSort(T [] array){ //This is not stable
      int arraylen = array.length;

      for(int n = (arraylen / 2) -1; n > -1; n--){
        heapify(array, arraylen, n);
      }

      for(int n = arraylen - 1; n > 0; n--){
        swap(array, 0, n);
        heapify(array, n, 0);
      }

    }

    private static <T extends Comparable<T>> void heapify(T [] array, int len, int n){
      int root = n;
      int leftChild = root * 2 + 1;
      int rightChild = root * 2 + 2;

      if(leftChild < len && array[leftChild].compareTo(array[root]) > 0){
        root = leftChild;
      }
      
      if(rightChild < len && array[rightChild].compareTo(array[root]) > 0){
        root = rightChild;
      }

      if(root != n){
        swap(array, root, n);
        heapify(array, len, root);
      }
    }
    
}