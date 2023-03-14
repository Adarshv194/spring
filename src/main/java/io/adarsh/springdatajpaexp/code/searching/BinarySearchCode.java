package io.adarsh.springdatajpaexp.code.searching;

// Time Complexity: O(logn) -> because it gonna takes k = logn steps to reach to the last element
public class BinarySearchCode {
    public static void main(String[] args) {
        int []inputs = new int[]{2, 4, 6, 8, 10, 12, 15, 19};
//        int result = binarySearchIterative(inputs, 0, inputs.length - 1, 15);
        int result = binarySearchRecursive(inputs, 0, inputs.length - 1, 15);
        if (result == -1)
            System.out.println("Number not found");
        else
            System.out.println("Number " + inputs[result] + " is found at: " + result + " index");
    }

    public static int binarySearchIterative(int []inputs, int starIndex, int endIndex, int numberToSearch) {
        while (endIndex - starIndex >= 0) {
            int mid = (starIndex + endIndex) / 2;
            if (inputs[mid] == numberToSearch)
                return mid;
            else if (inputs[mid] < numberToSearch)
                starIndex = mid + 1; // here we are reducing the search space by half
            else
                endIndex = mid - 1; // here we are reducing the search space by half
        }
        return -1;
    }

    public static int binarySearchRecursive(int []inputs, int startIndex, int endIndex, int numberToSearch) {
        if (endIndex - startIndex >= 0) {
            int mid = (endIndex + startIndex) / 2;
            if (inputs[mid] == numberToSearch)
                return mid;
            else if (inputs[mid] < numberToSearch)
                return binarySearchRecursive(inputs, mid + 1, endIndex, numberToSearch);
            else
                return binarySearchRecursive(inputs, startIndex, mid - 1, numberToSearch);
        }
        return -1;
    }
}
