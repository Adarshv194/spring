package io.adarsh.springdatajpaexp.code.sorting;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BubbleSortCode {
    public static void main(String[] args) {
        int inputs[] = new int[]{2, 7, 4, 1, 5, 3};
        System.out.println("Inputs before sorting");
        IntStream intStream = Arrays.stream(inputs);
        System.out.println(intStream.boxed().collect(Collectors.toList()));
        bubbleSort(inputs);
        System.out.println("Inputs after sorting");
        System.out.println(Arrays.stream(inputs).boxed().collect(Collectors.toList()));
    }

    public static void bubbleSort(int []inputs) {
        for (int i=0; i<inputs.length - 1; i++) {
            boolean sorted = true;
            for (int j=0; j<inputs.length - i - 1; j++) {
                if (inputs[j] > inputs[j+1]) {
                    swap(inputs, j, j+1);
                    sorted =  false;
                }
            }
            if (sorted)
                break;
        }
    }

    public static void swap(int []inputs, int firstIndex, int secondIndex) {
        int temp = inputs[firstIndex];
        inputs[firstIndex] = inputs[secondIndex];
        inputs[secondIndex] = temp;
    }
}
