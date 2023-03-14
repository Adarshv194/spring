package io.adarsh.springdatajpaexp.code.searching;

public class SearchElementInCircularSortedArray {
    public static void main(String[] args) {
        int[] inputs = new int[]{12, 13, 14, 15, 16, 17, 8, 9, 10};
        int []input = new int[]{12, 13, 1, 2, 3, 4, 5, 6, 7};
        int numberToSearch = 13;
        int result = searchElementInCircularSortedArray(inputs, 0, inputs.length - 1, numberToSearch);
        if (result == -1)
            System.out.println("Number: " + numberToSearch + " is not present in the array");
        else
            System.out.println("Number: " + numberToSearch + " is present at: " + result);
    }

    public static int searchElementInCircularSortedArray(int []inputs, int startIndex, int endIndex, int numberToSearch) {
        if (endIndex - startIndex >= 0) {
            int mid = (startIndex + endIndex) / 2;
            if (inputs[mid] == numberToSearch)
                return mid;
            else {
                if (inputs[mid] < inputs[endIndex]) { // not exhausted the mid
                    if (inputs[mid] < numberToSearch && numberToSearch <= inputs[endIndex])
                        return searchElementInCircularSortedArray(inputs, mid + 1, endIndex, numberToSearch);
                    else
                        return searchElementInCircularSortedArray(inputs, startIndex, mid - 1, numberToSearch);
                } else { // exhausted the mid
                    if (inputs[mid] > numberToSearch && numberToSearch <= inputs[endIndex])
                        return searchElementInCircularSortedArray(inputs, mid + 1, endIndex, numberToSearch);
                    else {
                        if (inputs[mid] < numberToSearch)
                            return searchElementInCircularSortedArray(inputs, mid + 1, endIndex, numberToSearch);
                        else
                            return searchElementInCircularSortedArray(inputs, startIndex, mid - 1, numberToSearch);
                    }

                }
            }
        }
        return -1;
    }
}
