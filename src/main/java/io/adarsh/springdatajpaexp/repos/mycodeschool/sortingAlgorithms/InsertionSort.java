package io.adarsh.springdatajpaexp.repos.mycodeschool.sortingAlgorithms;

public class InsertionSort {

  public static void main(String[] args) {
    int []inputs = new int[]{7, 2, 4, 1, 5, 3};
    performInsertionSortForLoop(inputs);
    for (int value: inputs) {
      System.out.print(value + " ,");
    }
  }

  public static void performInsertionSortWhileLoop(int []inputs) {
    for (int i=1; i<inputs.length; i++) {
      int value = inputs[i];
      int hole = i;
      while (hole > 0 && inputs[hole - 1] > value) {
        inputs[hole] = inputs[hole - 1];
        hole--;
      }
      inputs[hole] = value;
    }
  }

  public static void performInsertionSortForLoop(int []inputs) {
    for (int i=1; i<inputs.length; i++) {
      int value = inputs[i];
      int hole;
      for (hole = i; hole > 0 && value < inputs[hole - 1]; hole--) {
        inputs[hole] = inputs[hole - 1];
      }
//      for (hole = i; hole > 0; hole--) {
//        if (value < inputs[hole - 1]) {
//          inputs[hole] = inputs[hole - 1];
//        } else {
//          break;
//        }
//      }
      inputs[hole] = value;
    }
  }

//  public static void performInsertionSort(int []inputs) {
//    for (int i=1; i<inputs.length; i++) {
//      int value = inputs[i];
//      int hole = i;
//
//      for (int j=i-1; j>=0; j--) {
//        if (value < inputs[j]) {
//          inputs[j+1] = inputs[j];
//          if (j == 0) {
//            inputs[j] = value;
//          }
//        } else {
//          if (j+1 == hole) {}
//          else {
//            inputs[j+1] = value;
//            break;
//          }
//        }
//      }
//    }
//  }

}
