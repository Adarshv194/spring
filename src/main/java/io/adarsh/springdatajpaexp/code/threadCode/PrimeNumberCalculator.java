package io.adarsh.springdatajpaexp.code.threadCode;

public class PrimeNumberCalculator {

    public static int primeNumberCalculator(int n) {
        int counter = 0;
        int i, k;
        for (i=1, k=1; counter !=n+1;) {
            if (k % i == 0) {
                if (i == 1 && k != i) {
                    i++;
                } else if (i == k) {
                    counter++;
                    k++;
                    i=1;
                } else {
                    k++;
                    i=1;
                }
            } else {
                i++;
            }
        }
        return k-1;
    }

    public static void main(String[] args) {
        System.out.println(primeNumberCalculator(3));
    }
}
