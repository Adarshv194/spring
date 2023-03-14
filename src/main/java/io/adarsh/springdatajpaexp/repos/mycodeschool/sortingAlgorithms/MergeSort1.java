package io.adarsh.springdatajpaexp.repos.mycodeschool.sortingAlgorithms;

public class MergeSort1 {

  public static void main(String[] args) {
    int []inputs = new int[]{2, 4, 1, 6, 8, 5, 3, 7};
//    inputs = mergeSort(inputs, 0, inputs.length - 1);
    mergeSort(inputs);
    for (int value: inputs) {
      System.out.print(value + " ,");
    }
  }

//  private static int[] mergeSort(int []inputs, int startIndex, int endIndex) {
//    if (inputs.length > 1) {
//      int mid = (endIndex + startIndex) / 2;
//      startIndex = mid / 2 - 1;
//      int []tempLeftArray = getTempArray(inputs, startIndex, mid);
//      int []tempRightArray = getTempArray(inputs, mid + 1, endIndex);
//      int[] leftArray = mergeSort(tempLeftArray, startIndex, mid);
//      int[] rightArray = mergeSort(tempRightArray, mid + 1, endIndex);
//      return performMergeSortWhileLoop(inputs, leftArray, rightArray);
//    } else {
//      return inputs;
//    }
//  }

  private static void mergeSort(int []inputs) {
    if (inputs.length > 1) {
      int mid = inputs.length / 2;
      int []leftArray = getLeftArray(inputs, mid);
      int []rightArray = getRightArray(inputs, mid);
      mergeSort(leftArray);
      mergeSort(rightArray);
      performMergeSortWhileLoop(inputs, leftArray, rightArray);
    }
  }

  private static int[] getLeftArray(int []inputs, int mid) {
    int []leftArray = new int[mid];
      for (int i=0; i<mid; i++)
      leftArray[i] = inputs[i];
    return leftArray;
  }

  private static int[] getRightArray(int []inputs, int mid) {
    int []rightArray = new int[inputs.length - mid];
    for (int i=mid; i<inputs.length; i++)
      rightArray[i-mid] = inputs[i];
    return rightArray;
  }

  private static int[] performMergeSortWhileLoop(int []inputs, int []leftArray, int []rightArray) {
    int i=0;
    int j=0;
    int k=0;

    while (i < leftArray.length && j < rightArray.length) {
      if (leftArray[i] < rightArray[j]) {
        inputs[k] = leftArray[i];
        i++;
      } else {
        inputs[k] = rightArray[j];
        j++;
      }
      k++;
    }

    while (i < leftArray.length) {
      inputs[k] = leftArray[i];
      i++;
      k++;
    }

    while (j < rightArray.length) {
      inputs[k] = rightArray[j];
      j++;
      k++;
    }

    return inputs;

//    while (i < leftArray.length || j < rightArray.length) {
//      if (i < leftArray.length) {
//        inputs[k] = leftArray[i];
//        i++;
//      } else {
//        inputs[k] = rightArray[j];
//        j++;
//      }
//      k++;
//    }
  }

  private static void performMergeSortForLoop(int []inputs, int []leftArray, int []rightArray) {
    int k=0;
    int i=0;
    int j;

    for (j=0; j<rightArray.length && i < leftArray.length; j++) {
      if (leftArray[i] < rightArray[j]) {
        inputs[k] = leftArray[i];
        i++;
        j--;
      } else {
        inputs[k] = rightArray[j];
      }
      k++;
    }

    while (j < rightArray.length) {
      inputs[k] = rightArray[j];
      j++;
      k++;
    }

    while (i < leftArray.length) {
      inputs[k] = leftArray[i];
      k++;
      i++;
    }



//    for (int i=0; i<leftArray.length; i++) {
//      for (int j=0; j<rightArray.length; j++) {
//        if (i >= leftArray.length) {
//          while (j < rightArray.length) {
//            inputs[k] = rightArray[j];
//            j++;
//            k++;
//          }
//        }
//        else if (leftArray[i] < rightArray[j]) {
//          inputs[k] = leftArray[i];
//          k++;
//          i++;
//          j--;
//        } else {
//          inputs[k] = rightArray[j];
//          k++;
//        }
//      }
//      if (i < leftArray.length) {
//        while (i < leftArray.length) {
//          inputs[k] = leftArray[i];
//          i++;
//          k++;
//        }
//      }
//    }
  }

}
