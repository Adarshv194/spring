package io.adarsh.springdatajpaexp.mycodeschool.searchingAlgorithms;

import io.adarsh.springdatajpaexp.DTO.OccurrenceDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FirstAndLastOccurrence {
    public static void main(String[] args) {
        int[] inputs = new int[]{2, 6, 36, 36, 36, 47, 63, 81, 81, 97};
        List<Integer> inputsList = Arrays.stream(inputs).boxed().collect(Collectors.toList());
        System.out.println("Inputs are");
        inputsList.forEach(input -> System.out.print(input + " "));
        System.out.println("");
        OccurrenceDTO occurrenceDTO = new OccurrenceDTO();
        int numberToSearch = Integer.MIN_VALUE;
        while (numberToSearch != -1) {
            System.out.println("Enter number to search");
            Scanner scObj = new Scanner(System.in);
            numberToSearch = scObj.nextInt();
//            firstAndLastOccurrence(inputs, numberToSearch, occurrenceDTO, 0, inputs.length - 1);
//            if (occurrenceDTO.getLastOccurrence() == Integer.MIN_VALUE || occurrenceDTO.getFirstOccurrence() == Integer.MAX_VALUE)
//                System.out.println("Number: " + numberToSearch + " is not present in the inputs");
//            else {
//                System.out.println("Number: " + numberToSearch + " exist in the inputs with first occurrence at: " + occurrenceDTO.getFirstOccurrence() + " and last occurrence at: " + occurrenceDTO.getLastOccurrence());
//                System.out.println("Number: " + numberToSearch + " appears: " + (occurrenceDTO.getLastOccurrence() - occurrenceDTO.getFirstOccurrence() + 1) + " times in the input");
//                occurrenceDTO.setFirstOccurrence(Integer.MAX_VALUE);
//                occurrenceDTO.setLastOccurrence(Integer.MIN_VALUE);
//            }
//            int firstOccurrence = firstOccurrence(inputs, numberToSearch, 0, inputs.length - 1, -1);
//            int lastOccurrence = lastOccurrence(inputs, numberToSearch, 0, inputs.length - 1, -1);
            int firstOccurrence = binarySearch(inputs, numberToSearch, 0, inputs.length - 1, -1, true);
            int lastOccurrence = binarySearch(inputs, numberToSearch, 0, inputs.length - 1, -1, false);
            if (firstOccurrence != -1 && lastOccurrence != -1) {
                System.out.println("Number: " + numberToSearch + " exist in the inputs with first occurrence at: " + firstOccurrence);
                System.out.println("Number: " + numberToSearch + " exist in the inputs with last occurrence at: " + lastOccurrence);
                System.out.println("Number: " + numberToSearch + " appears: " + (lastOccurrence - firstOccurrence + 1) + " times in the input");
            } else {
                System.out.println("Number: " + numberToSearch + " is not present in the inputs");
            }
        }
    }

    // calling twice for firstOccurrence and lastOccurrence gives O(logn) + O(logn) -> O(logn)
    private static int binarySearch(int []inputs, int numberToSearch, int startIndex, int endIndex, int occurrenceIndex, boolean firstOccurrence) {
        if (startIndex > endIndex)
            return occurrenceIndex;
        int mid = (startIndex + endIndex) / 2;
        if (inputs[mid] == numberToSearch) {
            occurrenceIndex = mid;
            if (firstOccurrence)
                return binarySearch(inputs, numberToSearch, startIndex, mid - 1, occurrenceIndex, true);
            else
                return binarySearch(inputs, numberToSearch, mid + 1, endIndex, occurrenceIndex, false);
        }
        else if (inputs[mid] > numberToSearch)
            return binarySearch(inputs, numberToSearch, startIndex, mid - 1, occurrenceIndex, firstOccurrence);
        else
            return binarySearch(inputs, numberToSearch, mid + 1, endIndex, occurrenceIndex, firstOccurrence);
    }

    private static int firstOccurrence(int[] inputs, int numberToSearch, int startIndex, int endIndex, int firstOccurrence) {
        if (startIndex > endIndex)
            return firstOccurrence;
        int mid = (startIndex + endIndex) / 2;
        if (inputs[mid] == numberToSearch) {
            firstOccurrence = mid;
//            if (firstOccurrence > mid)
//                firstOccurrence = mid;
        }
        if (inputs[mid] >= numberToSearch)
            return firstOccurrence(inputs, numberToSearch, startIndex, mid - 1, firstOccurrence);
        else
            return firstOccurrence(inputs, numberToSearch, mid + 1, endIndex, firstOccurrence);
    }

    private static int lastOccurrence(int[] inputs, int numberToSearch, int startIndex, int endIndex, int lastOccurrence) {
        if (startIndex > endIndex)
            return lastOccurrence;
        int mid = (startIndex + endIndex) / 2;
        if (inputs[mid] == numberToSearch) {
            lastOccurrence = mid;
//            if (lastOccurrence < mid)
//                lastOccurrence = mid;
        }
        if (inputs[mid] <= numberToSearch)
            return lastOccurrence(inputs, numberToSearch, mid + 1, endIndex, lastOccurrence);
        else
            return lastOccurrence(inputs, numberToSearch, startIndex, mid - 1, lastOccurrence);
    }

    // gonna give T(n) = O(n) + logn * c -> O(n)
    private static void firstAndLastOccurrence(int[] inputs, int numberToSearch, OccurrenceDTO occurrenceDTO, int startIndex, int endIndex) {
        if (startIndex > endIndex)
            return;
        int mid = (startIndex + endIndex) / 2;
        if (inputs[mid] == numberToSearch) {
            if (occurrenceDTO.getFirstOccurrence() > mid)
                occurrenceDTO.setFirstOccurrence(mid);
            if (occurrenceDTO.getLastOccurrence() < mid)
                occurrenceDTO.setLastOccurrence(mid);
        }
        firstAndLastOccurrence(inputs, numberToSearch, occurrenceDTO, startIndex, mid - 1);
        firstAndLastOccurrence(inputs, numberToSearch, occurrenceDTO, mid + 1, endIndex);
    }
}
