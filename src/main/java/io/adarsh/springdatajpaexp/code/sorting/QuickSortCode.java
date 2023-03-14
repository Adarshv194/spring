package io.adarsh.springdatajpaexp.code.sorting;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuickSortCode {
    public static void main(String[] args) {
        int []inputs = new int[]{7, 2, 1, 6, 8, 5, 3, 4};
        System.out.println("Inputs before sorting");
        IntStream intStream = Arrays.stream(inputs);
        System.out.println(intStream.boxed().collect(Collectors.toList()));
        quickSort(inputs, 0, inputs.length - 1);
        System.out.println("Inputs after sorting");
        System.out.println(Arrays.stream(inputs).boxed().collect(Collectors.toList()));
    }

    public static void quickSort(int []inputs, int startIndex, int endIndex) {
        if (endIndex - startIndex > 0) {
            int pivot = getRandomizedPivotPosition(inputs, startIndex, endIndex);
            quickSort(inputs, startIndex, pivot - 1);
            quickSort(inputs, pivot + 1, endIndex);
        }
    }

    public static int getRandomizedPivotPosition(int []inputs, int startIndex, int endIndex) {
        Random random = new Random();
        int randomPivot = random.nextInt(endIndex - startIndex) + startIndex;
        swap(inputs, randomPivot, endIndex);
        return getPivotPosition(inputs, startIndex, endIndex);
    }

    public static int getPivotPosition(int []inputs, int startIndex, int endIndex) {
        int pivotPosition = startIndex;
        for (int i=startIndex; i<endIndex; i++) {
            if (inputs[i] < inputs[endIndex]) {
                if (i != pivotPosition) {
                    swap(inputs, pivotPosition, i);
                }
                pivotPosition++;
            }
        }
        swap(inputs, pivotPosition, endIndex);
        return pivotPosition;
    }

    public static void swap(int []inputs, int firstIndex, int secondIndex) {
        int temp = inputs[firstIndex];
        inputs[firstIndex] = inputs[secondIndex];
        inputs[secondIndex] = temp;
    }

}
