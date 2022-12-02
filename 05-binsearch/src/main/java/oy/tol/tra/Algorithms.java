package oy.tol.tra;
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
        T temp;
        temp = array[indexStart];
        array[indexStart] = array[indexEnd];
        array[indexEnd] = temp;
    }

    public static <T extends Comparable<T>> int binarySearch(T aValue, T [] fromArray, int fromIndex, int toIndex) {
      int midIndex = -1;

      while(fromIndex <= toIndex){
        midIndex = toIndex - (toIndex - fromIndex) / 2;
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
    
}