package io.adarsh.springdatajpaexp.mycodeschool.searchingAlgorithms;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SearchInCircularSortedArray {
    public static void main(String[] args) {
        int []inputs = new int[]{60, 70, 80, 90, 100, 10, 20, 30, 40, 50, 55};
//        int []inputs = new int[]{90, 100, 110, 120, 130, 140, 40, 50, 60};
        List<Integer> inputsList = Arrays.stream(inputs).boxed().collect(Collectors.toList());
        inputsList.forEach(input -> System.out.print(input + " "));
        System.out.println("");
        Scanner scObj = new Scanner(System.in);
        int numberToSearch = 0;
        while (numberToSearch != -1) {
            System.out.println("Enter a number to search");
            numberToSearch = scObj.nextInt();
            int indexFound = searchNumberInCircularSortedArray(inputs, numberToSearch, 0, inputs.length - 1);
            System.out.println(indexFound != -1 ? "Number: " + numberToSearch + " is found at index: " + indexFound :
                    "Number: " + numberToSearch + " is not found in the inputs");
        }
    }

    private static int searchNumberInCircularSortedArray(int []inputs, int numberToSearch, int startIndex, int endIndex) {
        if (startIndex > endIndex)
            return -1;
        int mid = (startIndex + endIndex) / 2;
        if (inputs[mid] == numberToSearch)
            return mid;
        if (inputs[mid] <= inputs[endIndex]) {
            if (inputs[mid] < numberToSearch && inputs[endIndex] >= numberToSearch)
                return searchNumberInCircularSortedArray(inputs, numberToSearch, mid + 1, endIndex);
            return searchNumberInCircularSortedArray(inputs, numberToSearch, startIndex, mid - 1);
        } else {
            if ((inputs[mid] > numberToSearch && numberToSearch <= inputs[endIndex]) || inputs[mid] < numberToSearch)
                return searchNumberInCircularSortedArray(inputs, numberToSearch, mid + 1, endIndex);
            return searchNumberInCircularSortedArray(inputs, numberToSearch, startIndex, mid - 1);
        }
    }
}
