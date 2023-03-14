package io.adarsh.springdatajpaexp.repos.mycodeschool.searchingAlgorithms;

import java.util.Scanner;

public class BinarySearchAlgorithm {
    public static void main(String[] args) {
        int[] inputs = new int[]{2, 6, 13, 21, 36, 47, 63, 81, 97};
        int numberToSearch = Integer.MIN_VALUE;
        while (numberToSearch != -1) {
            System.out.println("Enter number to search");
            Scanner scObj = new Scanner(System.in);
            numberToSearch = scObj.nextInt();
            int indexFound = binarySearch(inputs, numberToSearch, 0, inputs.length - 1);
//            int indexFound = binarySearchIterative(inputs, numberToSearch);
            if (indexFound != -1) {
                System.out.println("Number: " + numberToSearch + " is found at index: " + indexFound);
            } else {
                System.out.println("Number: " + numberToSearch + " is not present in the inputs");
            }
        }
    }

    private static int binarySearch(int[] inputs, int numberToSearch, int startIndex, int endIndex) {
        if (startIndex > endIndex)
            return -1;

        int mid = (startIndex + endIndex) / 2;
        if (inputs[mid] == numberToSearch)
            return mid;
        else if (inputs[mid] < numberToSearch)
            return binarySearch(inputs, numberToSearch, mid + 1, endIndex); // reducing the search space by almost half
        else
            return binarySearch(inputs, numberToSearch, startIndex, mid - 1); // reducing the search space by almost half
    }

    private static int binarySearchIterative(int[] inputs, int numberToSearch) {
        int startIndex = 0;
        int endIndex = inputs.length - 1;

        while (startIndex <= endIndex) {
            int mid = (startIndex + endIndex) / 2;
            if (inputs[mid] == numberToSearch)
                return mid;
            else if (inputs[mid] < numberToSearch)
                startIndex = mid + 1;
            else
                endIndex = mid - 1;
        }
        return -1;
    }
}
