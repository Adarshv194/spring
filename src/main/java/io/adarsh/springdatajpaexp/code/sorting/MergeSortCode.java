package io.adarsh.springdatajpaexp.code.sorting;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MergeSortCode {
    public static void main(String[] args) {
        int []inputs = new int[]{2, 4, 1, 6, 8, 5, 3, 7};
        System.out.println("Inputs before sorting");
        IntStream intStream = Arrays.stream(inputs);
        System.out.println(intStream.boxed().collect(Collectors.toList()));
        mergeSort(inputs);
        System.out.println("Inputs after sorting");
        System.out.println(Arrays.stream(inputs).boxed().collect(Collectors.toList()));
    }

    public static void mergeSort(int []inputs) {
        if (inputs.length > 1) {
            int mid = inputs.length / 2; // 5/2-> 2, 4/2-> 2
            int []leftArray = getLeftArray(inputs, mid); // 5, 6
            int []rightArray = getRightArray(inputs, mid); // 1, 3, 2
            mergeSort(leftArray);
            mergeSort(rightArray);
            mergeTwoSortedArrays(leftArray, rightArray, inputs);
        }
    }

    public static int[] getLeftArray(int []inputs, int mid) {
        int []leftArray = new int[mid];
        for (int i=0; i<mid; i++)
            leftArray[i] = inputs[i];
        return leftArray;
    }

    public static int[] getRightArray(int []inputs, int mid) {
        int []rightArray = new int[inputs.length - mid];
        for (int i=mid; i<inputs.length; i++)
            rightArray[i-mid] = inputs[i];
        return rightArray;
    }

    public static void mergeTwoSortedArrays(int []leftArray, int []rightArray, int []inputs) {
        int l=0, r=0, k=0;
        while (l < leftArray.length && r < rightArray.length && k < inputs.length) {
            if (leftArray[l] < rightArray[r]) {
                inputs[k] = leftArray[l];
                l++;
            } else {
                inputs[k] = rightArray[r];
                r++;
            }
            k++;
        }
        while (l < leftArray.length) {
            inputs[k] = leftArray[l];
            l++;
            k++;
        }

        while (r < rightArray.length) {
            inputs[k] = rightArray[r];
            r++;
            k++;
        }
    }
}
