package io.adarsh.springdatajpaexp.mycodeschool.sortingAlgorithms;

public class BubbleSort {

  public static void main(String[] args) {
    int []inputs = new int[]{2, 7, 4, 1, 5, 3};
    performBubbleSort(inputs);
    for (int value: inputs) {
      System.out.print(value + " ,");
    }
  }

  public static void performBubbleSort(int []inputs) {
    for (int i=0; i<inputs.length - 1; i++) {
      boolean flag = true;
      for (int j=0; j<inputs.length - i - 1; j++) {
        if (inputs[j] > inputs[j+1]) {
          int temp = inputs[j];
          inputs[j] = inputs[j+1];
          inputs[j+1] = temp;
          flag = false;
        }
      }
      if (flag)
        break;
    }
  }

}
