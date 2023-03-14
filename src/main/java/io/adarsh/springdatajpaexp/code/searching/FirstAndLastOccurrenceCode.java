package io.adarsh.springdatajpaexp.code.searching;

import io.adarsh.springdatajpaexp.DTO.OccurrenceDTO;

public class FirstAndLastOccurrenceCode {
    public static void main(String[] args) {
        int []inputs = new int[]{2, 4, 10, 10, 10, 18, 20};
        var value = new Object() {
            int firstOccurrence = -1;
            int lastOccurrence = -1;

            public int getFirstOccurrence() {
                return firstOccurrence;
            }

            public void setFirstOccurrence(int firstOccurrence) {
                this.firstOccurrence = firstOccurrence;
            }

            public int getLastOccurrence() {
                return lastOccurrence;
            }

            public void setLastOccurrence(int lastOccurrence) {
                this.lastOccurrence = lastOccurrence;
            }
        };

        OccurrenceDTO occurrence = new OccurrenceDTO();

        binarySearch(inputs, 18, 0, inputs.length - 1, true, occurrence);
        binarySearch(inputs, 18, 0, inputs.length - 1, false, occurrence);
        if (occurrence.getFirstOccurrence() == Integer.MAX_VALUE || occurrence.getLastOccurrence() == Integer.MIN_VALUE)
            System.out.println("Number not found");
        else
            System.out.println("Number: " + inputs[occurrence.getFirstOccurrence()] +  " first occurrence found at: " + occurrence.getFirstOccurrence() + " last occurrence found at: " + occurrence.getLastOccurrence());

    }

    public static void binarySearch(int []inputs, int numberToSearch, int startIndex, int endIndex, boolean firstOccurrence, OccurrenceDTO checkValue) {
        if (endIndex - startIndex >= 0) {
            int mid = (startIndex + endIndex) / 2;
            if (inputs[mid] == numberToSearch) {
                if (firstOccurrence) {
                    checkValue.setFirstOccurrence(mid);
                    binarySearch(inputs, numberToSearch, startIndex, mid - 1, true, checkValue);
                } else {
                    checkValue.setLastOccurrence(mid);
                    binarySearch(inputs, numberToSearch, mid + 1, endIndex, false, checkValue);
                }
            } else if (inputs[mid] > numberToSearch) {
                if (firstOccurrence)
                    binarySearch(inputs, numberToSearch, startIndex, mid - 1, true, checkValue);
                else
                    binarySearch(inputs, numberToSearch, startIndex, mid - 1, false, checkValue);
            } else
                if (firstOccurrence)
                    binarySearch(inputs, numberToSearch, mid + 1, endIndex, true, checkValue);
                else
                    binarySearch(inputs, numberToSearch, mid + 1, endIndex, false, checkValue);
        }
    }
}
