package io.adarsh.springdatajpaexp.mycodeschool.searchingAlgorithms;

public class CircularSortedArray {
    public static void main(String[] args) {
        int[] inputs = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
//        int[] inputs = new int[]{90, 100, 110, 10, 20, 30, 40, 50}; // pivot element to break the recursion will be an element whose next number is smaller
//        int[] inputs = new int[]{100, 110, 120, 130, 140, 50, 60, 70};
        int numberOfRotation = numberOfTimesSortedArrayIsRotated(inputs, 0, inputs.length - 1);
        System.out.println(
                numberOfRotation != 0 ?
                        "Inputs are rotated: " + numberOfRotation + " times" :
                        "Inputs are not rotated as it is in sorted format already"
        );
    }

    // T(n) = O(logn) as we are dividing the search space on every recursive call
    private static int numberOfTimesSortedArrayIsRotated(int[] inputs, int startIndex, int endIndex) {
        if (startIndex > endIndex)
            return 0;
        int mid = (startIndex + endIndex) / 2;
        if (inputs[mid] > inputs[mid + 1])
            return mid + 1;
        else
            if (mid == 0)
                return 0;

        if (mid != inputs.length - 1) {
            if (inputs[mid] <= inputs[endIndex])
                return numberOfTimesSortedArrayIsRotated(inputs, startIndex, mid);
            return numberOfTimesSortedArrayIsRotated(inputs, mid + 1, endIndex);
        } else {
            if (inputs[mid] < inputs[mid - 1])
                return mid;
            return 0;
        }
    }
}
