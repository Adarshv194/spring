package io.adarsh.springdatajpaexp.JavaCollectionAndEssentials;

public class Test {

    public static void main(String[] args) {
        int []nums = new int[]{1,3,-1,-3,5,3,6,7};
        int k = 3;
//        Output: [3,3,5,5,6,7]
        test(nums, k);
    }

    public static void test(int []nums, int k) {
        for (int i=0; i< nums.length-2; i++){
            int max = max(nums, i, i+k);
            System.out.print(max);
        }
    }

    private static int max(int []nums, int start, int k) {
        int max = 0;
        for (int i=start; i<k; i++) {
            if (nums[i] > 0)
                max = nums[i];
        }
        return max;
    }
}
