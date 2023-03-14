package io.adarsh.springdatajpaexp.repos.mycodeschool.sortingAlgorithms;

public class SelectionSort {

  public static void main(String[] args) {
    int []inputs = {2, 7, 4, 1, 5, 3};
//    System.out.println("Inputs sorted using extra memory selection sort algorithm");
//    performSelectionSortExtraMemory(inputs);
    System.out.println("Inputs sorted using in - memory selection sort algorithm");
    performSelectionSortInMemory(inputs);
  }

  public static void performSelectionSortInMemory(int []inputs) {
    for (int i=0; i<inputs.length - 1; i++) {
      int min = i;
      for (int j=i+1; j<inputs.length; j++) {
        if (inputs[j] < inputs[min]) {
          min = j;
        }
      }
      int temp = inputs[i];
      inputs[i] = inputs[min];
      inputs[min] = temp;
    }

    for (int i=0; i<inputs.length; i++) {
      System.out.print(inputs[i] + ", ");
    }

  }

  // this doesn't used the in-placed memory (where extra memory used is constant in respective to the numbers of inputs)
  // In this approach the auxiliary temporary array will be equal to the number of input array size.
  public static void performSelectionSortExtraMemory(int []inputs) {
    int inputSize = inputs.length;
    int []tempArray = new int[inputSize];
    int counter = 0;

    while(counter < inputSize) {
      int min = 0;
      for (int j = 1; j<inputSize; j++) {
        if (inputs[j] < inputs[min]) {
          min = j;
        }
      }
      tempArray[counter] = inputs[min];
      inputs[min] = Integer.MAX_VALUE;
      counter++;
    }

    for (int i=0; i<inputSize; i++) {
      inputs[i] = tempArray[i];
      System.out.print(inputs[i] + ", ");
    }
  }

}
