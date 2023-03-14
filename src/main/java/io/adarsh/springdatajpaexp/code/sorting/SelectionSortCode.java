package io.adarsh.springdatajpaexp.code.sorting;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SelectionSortCode {

    public static void main(String []args) {
        int []inputs = new int[]{2, 7, 4, 1, 5, 3};
        System.out.println("Inputs before sorting");
        IntStream intStream = Arrays.stream(inputs);
        System.out.println(intStream.boxed().collect(Collectors.toList()));
        selectionSort(inputs);
        System.out.println("Inputs after sorting");
        System.out.println(Arrays.stream(inputs).boxed().collect(Collectors.toList()));
    }

    public static void selectionSort(int  []inputs) {
        for (int i=0; i<inputs.length - 1; i++) {
            int min = i;
            for (int j=i+1; j<inputs.length; j++) {
                if (inputs[min] > inputs[j])
                    min = j;
            }
            if (min != i)
                swap(inputs, i, min);
        }
    }

    public static void swap(int []inputs, int firstIndex, int secondIndex) {
        int temp = inputs[firstIndex];
        inputs[firstIndex] = inputs[secondIndex];
        inputs[secondIndex] = temp;
    }
}