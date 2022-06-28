package io.adarsh.springdatajpaexp.mycodeschool.sortingAlgorithms;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// will find the pivot element and then find its position in the input array,
// so that all the elements to the left of the pivot are smaller and all the elements to the right of the pivot are greater then pivot
public class QuickSort {

  public static void main(String[] args) {
    /// TODO: find the pivot element and find its position in the array,
    // so that in the input array all the element to the left of the pivot are smaller and
    // all the elements to the right of the pivot are greater than the pivot.
    int []inputs = new int[]{0, 2, 1, 6, 8, 5, 3, 4};
    int startIndex = 0;
    int endIndex = inputs.length - 1;
    quickSort(inputs, 0, endIndex);
    Arrays.stream(inputs).boxed().collect(Collectors.toList()).forEach(value -> System.out.print(value + " ,"));
  }

  private static void quickSort(int []inputs, int startIndex, int endIndex) {
    if (startIndex >= endIndex) return;

    int pivot = findAndPlacePivot(inputs, startIndex, endIndex);
    quickSort(inputs, 0, pivot - 1);
    quickSort(inputs, pivot + 1, endIndex);
  }

  private static int findAndPlacePivot(int []inputs, int startIndex, int endIndex) {
    int pivotValue = inputs[endIndex];
    int pivot = startIndex;

    for (int i=startIndex; i<endIndex; i++) {
      if (inputs[i] < pivotValue) {
        int temp = inputs[pivot];
        inputs[pivot] = inputs[i];
        inputs[i] = temp;
        pivot++;
      }
    }

    int temp = inputs[endIndex];
    inputs[endIndex] = inputs[pivot];
    inputs[pivot] = temp;

    return pivot;
  }

  private static int findAndPlacePivotByPushing(int []inputs, int startIndex, int endIndex) {
      int pivotValue = inputs[endIndex];
      int pivot = endIndex;

      for (int i=endIndex - 1; i >=0; i--) {
        if (inputs[i] > pivotValue) {
          int temp = inputs[i];
          for (int j=i; j<pivot; j++) {
            inputs[j] = inputs[j+1];
          }
          inputs[pivot] = temp;
          pivot = pivot - 1;
        }
      }

      return pivot;
  }

}
