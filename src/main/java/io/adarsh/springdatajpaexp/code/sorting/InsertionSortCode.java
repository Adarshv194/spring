package io.adarsh.springdatajpaexp.code.sorting;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InsertionSortCode {
    public static void main(String[] args) {
        int []inputs = new int[]{7, 2, 4, 1, 5, 3};
        System.out.println("Inputs before sorting");
        IntStream intStream = Arrays.stream(inputs);
        System.out.println(intStream.boxed().collect(Collectors.toList()));
        insertionSort(inputs);
        System.out.println("Inputs after sorting");
        System.out.println(Arrays.stream(inputs).boxed().collect(Collectors.toList()));
    }

    public static void insertionSort(int []inputs) {
        for (int i=1; i< inputs.length; i++) {
            int value = inputs[i];
            int hole = i;
            while (hole > 0 && inputs[hole - 1] > value) {
                inputs[hole] = inputs[hole - 1];
                hole--;
            }
            inputs[hole] = value;
        }
    }


}
