package io.adarsh.springdatajpaexp.mycodeschool.sortingAlgorithms;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class QuickSort {

    public static void main(String[] args) {
        int[] inputs = new int[]{7, 2, 1, 6, 8, 5, 3, 4};
        System.out.println("Performing quick sort on inputs...");
        List<Integer> inputsList = Arrays.stream(inputs).boxed().collect(Collectors.toList());
        inputsList.forEach(value -> System.out.print(value + " "));
        System.out.println("");
        quickSort(inputs, 0, inputs.length - 1);
        System.out.println("");
        System.out.println("Output after performing quick sort...");
        List<Integer> outputsList = Arrays.stream(inputs).boxed().collect(Collectors.toList());
        outputsList.forEach(value -> System.out.print(value + " "));
    }

    private static void quickSort(int[] inputs, int startIndex, int endIndex) {
        if (startIndex >= endIndex) // recursion ending condition is when there is only one element left in the inputs (using startIndex and endIndex) or there is no element left at either side (left and right) of the pivot
            return;

        int pivot = randomizedPartitioning(inputs, startIndex, endIndex); // using this we can get the O(nlogn) time complexity in worst case also after converting into as average case
        quickSort(inputs, startIndex, pivot - 1); // denotes the left side of the inputs segment of the pivot
        quickSort(inputs, pivot + 1, endIndex);// denotes the right side of the inputs segment of the pivot
    }

    private static int randomizedPartitioning(int []inputs, int startIndex, int endIndex) {
        Random random = new Random();
        // when the sorted array is provided
        // As if we always choose the pivot as the endIndex then there is possibility that the pivot does not divide the array in equal or almost equal partition
        // so using the random number between startIndex and endIndex we can ensure that the pivot will not always do the unbalanced partitioning
        int pivotIndex = random.nextInt(endIndex - startIndex) + startIndex;
        System.out.println("Random pivot index picked as: " + pivotIndex + " in between start: " + startIndex + " and endIndex: " + endIndex );
        int temp = inputs[pivotIndex];
        inputs[pivotIndex] = inputs[endIndex];
        inputs[endIndex] = temp;
        // swapping the values so that our partitioning logic will work the same using the endIndex only
        // after swapping only the subarray segment endIndex will be swapped with random picked index within the subarray segment.
        return partition(inputs, startIndex, endIndex);
    }

    private static int partition(int[] inputs, int startIndex, int endIndex) {
        int pivot = startIndex; // assigning pivot index for the iteration
        // picking pivotValue as always the value present at the inputs[endIndex]
        for (int i = startIndex; i < endIndex; i++) {
            if (inputs[i] < inputs[endIndex]) {
                if (i != pivot) { // As we are swapping the values present at pivot and i then if(i == pivot) then we don't need to swap, we just increment the pivotIndex
                    int temp = inputs[i];
                    inputs[i] = inputs[pivot];
                    inputs[pivot] = temp;
                }
                pivot++;
            }
        }
        int temp = inputs[endIndex];
        inputs[endIndex] = inputs[pivot];
        inputs[pivot] = temp;

        return pivot;
    }
}
