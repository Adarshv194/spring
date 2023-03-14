package io.adarsh.springdatajpaexp.repos.mycodeschool.sortingAlgorithms;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

// will use the recursion technique where we will keep dividing the array from the mid into two partitions till there is only one element left in the array
// the following the recursion chain we will keep sort the array and merge them.
public class MergeSort {

  public static void main(String[] args) {
    /// TODO: using recursion keep dividing the array from mid into two arrays left and right till there is only on element left in the array.
    /// then call the method to merge those two left and right arrays.
    /// TODO: write a method to merge two sorted arrays so that the merged array will also be a sorted array.
    int []inputs = new int[]{2, 4, 1, 6, 8, 5, 3, 7};
//    List<int[]> ints = Arrays.asList(inputs);
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

  private static void mergeSortedArraysUsingForLoop(int []inputs, int []leftInputs, int []rightInputs) {
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

  public static void convertStringArrayIntoStringSeperatedBySpace(String input) {

    // code to modify a local variable inside the stream scope
    var stringWrapper = new Object() {
      String spaceSeperatedString = new String();

      public String getSpaceSeperatedString() {
        return spaceSeperatedString;
      }

      public void setSpaceSeperatedString(String value) {
        this.spaceSeperatedString += value;
        this.spaceSeperatedString += " ";
      }
    };
    String[] inputs = input.split(" ");
    List<String> inputList = Arrays.asList(inputs);
    inputList.forEach(value -> stringWrapper.setSpaceSeperatedString(value));

    String convertedString = stringWrapper.getSpaceSeperatedString();
    System.out.println(convertedString);
  }

  public static void convertingIntArrayIntoListOfInteger() {
//    Stream<Object> stream = Arrays.stream(new Object[]{});
    IntStream intStream = Arrays.stream(new int[]{1, 2}); // stream of int
    Stream<Integer> integerStream = intStream.boxed(); // stream of Integer
    List<Integer> integerList = integerStream.collect(Collectors.toList());
  }

  public static void convert2DArrayIntoListOfList() {
    // for String

    String [][]stringArray = new String[][]{
            {"hello", "everyone"},
            {"my", "name"},
            {"is", "Adarsh"},
            {"Verma"}
    };
    Stream<String[]> stringArrayStream = Arrays.stream(stringArray); // stream of String[]

    // conversion to Stream<List<String>>
    Stream<List<String>> stringListStream = stringArrayStream.map(Arrays::asList); // stream of List<String>
    List<List<String>> listOfListOfString = stringListStream.collect(Collectors.toList());

    listOfListOfString.forEach(listOfInteger -> {
      listOfInteger.forEach(System.out::print);
      System.out.println("");
    });

    // conversion to Stream<String>
    Stream<String> stringStream = stringArrayStream.flatMap(array -> Arrays.stream(array));
    List<String> stringList = stringStream.collect(Collectors.toList());

    // for int and other types
    int [][]intArray = new int[][]{{1, 2}, {3, 4}, {5, 6}};
    Stream<int[]> intArrayStream = Arrays.stream(intArray);
    // conversion to Stream<IntStream>
    Stream<IntStream> intStreamStream = intArrayStream.map(array -> Arrays.stream(array));
    Stream<List<Integer>> listStream = intStreamStream.map(value -> value.boxed().collect(Collectors.toList()));
    List<List<Integer>> listOfListOfInteger = listStream.collect(Collectors.toList());

    listOfListOfInteger.forEach(listOfInteger -> {
      listOfInteger.forEach(System.out::print);
      System.out.println("");
    });

    // conversion to Stream<Integer>
    IntStream intStreams = intArrayStream.flatMapToInt(value -> Arrays.stream(value));
    Stream<Integer> integerStream = intStreams.boxed();
    List<Integer> integerList = integerStream.collect(Collectors.toList());

  }

}
