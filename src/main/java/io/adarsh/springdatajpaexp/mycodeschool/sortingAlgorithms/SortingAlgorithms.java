package io.adarsh.springdatajpaexp.mycodeschool.sortingAlgorithms;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SortingAlgorithms {

    public static void main(String[] args) {
        int[] inputs = new int[]{7, 2, 1, 6, 8, 5, 3, 4};
        quickSort(inputs, 0, inputs.length - 1);
        List<Integer> inputsList = Arrays.stream(inputs).boxed().collect(Collectors.toList());
        System.out.println("Inputs after sorting");
        inputsList.forEach(value -> System.out.print(value + " "));
    }

    private static void swap(int[] inputs, int index1, int index2) {
        int temp = inputs[index1];
        inputs[index1] = inputs[index2];
        inputs[index2] = temp;
    }

    private static void selectionSort(int[] inputs) {
        System.out.println("Sorting Numbers using selection sort");
        for (int i = 0; i < inputs.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < inputs.length; j++) {
                if (inputs[j] < inputs[min]) {
                    min = j;
                }
            }
            if (min != i)
                swap(inputs, min, i);
        }
    }

    private static void bubbleSort(int[] inputs) {
        System.out.println("Sorting numbers using bubble sort");
        for (int i = 0; i < inputs.length - 1; i++) {
            boolean noSwap = true;
            for (int j = 0; j < inputs.length - i - 1; j++) {
                if (inputs[j] > inputs[j + 1]) {
                    swap(inputs, j, j + 1);
                    noSwap = false;
                }
            }
            if (noSwap)
                return;
        }
    }

    private static void insertionSortWhileLoop(int[] inputs) {
        System.out.println("Sorting numbers using insertion sort");
        for (int i = 1; i < inputs.length; i++) {
            int hole = i;
            int value = inputs[i];
            while (hole > 0 && inputs[hole - 1] > value) {
                inputs[hole] = inputs[hole - 1];
                hole--;
            }
            inputs[hole] = value;
        }
    }

    private static void insertionSortForLoop(int[] inputs) {
        for (int i = 1; i < inputs.length; i++) {
            int hole;
            int value = inputs[i];
            for (hole = i; hole > 0 && inputs[hole - 1] > value; hole--) {
                inputs[hole] = inputs[hole - 1];
            }
//            for (int j = i - 1; j > -1 && inputs[j] > value; j--, hole--) {
//                inputs[hole] = inputs[j];
//            }
            inputs[hole] = value;
        }
    }

    private static void mergeSort(int[] inputs) {
        if (inputs.length <= 1)
            return;

        int mid = inputs.length / 2;
        int[] leftArray = getLeftArray(inputs, mid);
        mergeSort(leftArray);
        int[] rightArray = getRightArray(inputs, mid);
        mergeSort(rightArray);
        mergeSortedArrays(inputs, leftArray, rightArray);
    }

    private static int[] getLeftArray(int[] inputs, int mid) {
        int[] leftArray = new int[mid];
        for (int i = 0; i < mid; i++)
            leftArray[i] = inputs[i];
        return leftArray;
    }

    private static int[] getRightArray(int[] inputs, int mid) {
        int[] rightArray = new int[inputs.length - mid];
        for (int i = mid; i < inputs.length; i++)
            rightArray[i - mid] = inputs[i];
        return rightArray;
    }

    private static void mergeSortedArrays(int[] inputs, int[] leftArray, int[] rightArray) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < leftArray.length && j < rightArray.length && k < inputs.length) {
            if (leftArray[i] < rightArray[j]) {
                inputs[k] = leftArray[i];
                i++;
            } else {
                inputs[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < leftArray.length && k < inputs.length) {
            inputs[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightArray.length && k < inputs.length) {
            inputs[k] = rightArray[j];
            j++;
            k++;
        }
    }

    private static void quickSort(int[] inputs, int startIndex, int endIndex) {
        if (startIndex >= endIndex)
            return;

        int pivot = randomizedPartition(inputs, startIndex, endIndex);
        quickSort(inputs, startIndex, pivot - 1);
        quickSort(inputs, pivot + 1, endIndex);
    }

    private static int randomizedPartition(int[] inputs, int startIndex, int endIndex) {
        Random random = new Random();
        int pivotIndexToPick = random.nextInt(endIndex - startIndex) + startIndex;
        swap(inputs, pivotIndexToPick, endIndex);
        return partition(inputs, startIndex, endIndex);
    }

    private static int partition(int[] inputs, int startIndex, int endIndex) {
        int pivotPosition = startIndex;
        while (startIndex < endIndex) {
            if (inputs[startIndex] < inputs[endIndex]) {
                if (startIndex != pivotPosition) {
                    swap(inputs, pivotPosition, startIndex);
                }
                pivotPosition++;
            }
            startIndex++;
        }
        swap(inputs, pivotPosition, endIndex);
        return pivotPosition;
    }
}
