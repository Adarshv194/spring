package io.adarsh.springdatajpaexp.code.searching;

public class SortedArrayRotatedCode {
    public static void main(String[] args) {
//        int []inputs = new int[]{100, 110, 120, 130, 140, 50, 60, 70};
//        int []inputs = new int[]{110, 12};
//        int []inputs = new int[]{110};
        int[] inputs = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        System.out.println("Sorted array is rotated: " + numberOfTimesSortedArrayRotated(inputs, 0, inputs.length - 1) + " times");
    }

    public static int numberOfTimesSortedArrayRotated(int []inputs, int startIndex, int endIndex) {
        if (endIndex - startIndex >= 0) {
            int mid = (startIndex + endIndex) / 2;
            if (mid - 1 != -1) {
                if (inputs[mid] < inputs[mid - 1])
                    return mid;
                else if (inputs[mid] > inputs[endIndex])
                    return numberOfTimesSortedArrayRotated(inputs, mid + 1, endIndex);
                else
                    return numberOfTimesSortedArrayRotated(inputs, startIndex, mid - 1);
            } else {
                if (mid + 1 != inputs.length) {
                    if (inputs[mid] > inputs[mid + 1])
                        return mid + 1;
                }
                return 0;
            }
        }
        return 0;
    }
}
