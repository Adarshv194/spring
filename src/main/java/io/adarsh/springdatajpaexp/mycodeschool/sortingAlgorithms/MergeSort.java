package io.adarsh.springdatajpaexp.mycodeschool.sortingAlgorithms;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// will use the recursion technique where we will keep dividing the array from the mid into two partitions till there is only one element left in the array
// the following the recursion chain we will keep sort the array and merge them.
public class MergeSort {

  public static void main(String[] args) {
    /// TODO: using recursion keep dividing the array from mid into two arrays left and right till there is only on element left in the array.
    /// then call the method to merge those two left and right arrays.
    /// TODO: write a method to merge two sorted arrays so that the merged array will also be a sorted array.
    int []inputs = new int[]{2, 4, 1, 6, 8, 5, 3, 7};
    mergeSort(inputs);
    List<Integer> output = Arrays.stream(inputs).boxed().collect(Collectors.toList());
    output.forEach(value -> System.out.print(value + " ,"));

  }

  private static void mergeSort(int []inputs) {
    int inputsLength = inputs.length;
    if (inputsLength > 1) {
      int mid = inputsLength / 2;
      int []leftInputs = getLeftInputs(inputs, mid);
      int []rightInputs = getRightInputs(inputs, mid, inputsLength);
      mergeSort(leftInputs);
      mergeSort(rightInputs);
      mergeSortedArraysWhileLoop(inputs, leftInputs, rightInputs);
    }
  }

  private static int[] getLeftInputs(int []inputs, int mid) {
    int []leftInputs = new int[mid];
    for (int i=0; i<mid; i++) {
      leftInputs[i] = inputs[i];
    }
    return leftInputs;
  }

  private static int[] getRightInputs(int []inputs, int mid, int inputsLength) {
    int []rightInputs = new int[inputsLength - mid];
    for (int i=mid; i<inputsLength; i++) {
      rightInputs[i-mid] = inputs[i];
    }
    return rightInputs;
  }

  private static void mergeSortedArraysWhileLoop(int []inputs, int []leftInputs, int []rightInputs) {
    int i = 0; // will iterate over leftInputs
    int j = 0; // will iterate over rightInputs
    int k = 0; // will iterate over inputs

    while (i < leftInputs.length && j < rightInputs.length && k < inputs.length) {
      if (leftInputs[i] < rightInputs[j]) {
        inputs[k] = leftInputs[i];
        i++;
      } else {
        inputs[k] = rightInputs[j];
        j++;
      }
      k++;
    }

    while (i < leftInputs.length && k < inputs.length) {
      inputs[k] = leftInputs[i];
      k++;
      i++;
    }

    while (j < rightInputs.length && k < inputs.length) {
      inputs[k] = rightInputs[j];
      k++;
      j++;
    }
  }

  private static void mergeSortedArraysForLoop(int []inputs, int []leftInputs, int []rightInputs) {
    int i; // will iterate over leftInputs
    int j; // will iterate over rightInputs
    int k; // will iterate over inputs

    for (i=0, j=0, k=0;
        i < leftInputs.length && j < rightInputs.length && k < inputs.length;
        i++, k++, j++) {
      if (leftInputs[i] < rightInputs[j]) {
        inputs[k] = leftInputs[i];
        j--;
      } else {
        inputs[k] = rightInputs[j];
        i--;
      }
    }

    while (i < leftInputs.length && k < inputs.length) {
      inputs[k] = leftInputs[i];
      k++;
      i++;
    }

    while (j < rightInputs.length && k < inputs.length) {
      inputs[k] = rightInputs[j];
      k++;
      j++;
    }
  }

}
