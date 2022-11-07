package oy.tol.tra;

import java.util.function.Predicate;

public class Algorithms {
  public static class ModeSearchResult<T>{ //Nested class
    public T theMode;
    public int count = 0;
  }

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

    public static <T extends Comparable<T>> ModeSearchResult<T> findMode(T [] array){
      ModeSearchResult<T> result = new ModeSearchResult<>();
      int arrayLen = 0;
      try {
        arrayLen = array.length;
      } catch (Exception e) {
        result.count = -1;
        result.theMode = null;
        return result;
      }
      if(arrayLen < 2){
        result.count = -1;
        result.theMode = null;
        return result;
      }

      sort(array);
      
      for(int n = 0, temp = 1; n < arrayLen - 1; n++){
        if(array[n].compareTo(array[n + 1]) == 0){
          temp++;
        }
        if(temp > result.count){
          result.count = temp;
          result.theMode = array[n];
        }
      }
    return result;
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
}