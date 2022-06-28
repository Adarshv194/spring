package io.adarsh.springdatajpaexp.thread.JB;

public class PrimeCalculator {

  public static int calculateNthPrime(int number) {
    boolean flag = true;
    int primeCounter = 1;
    int nthPrime = 0;
      for (int i = 2; primeCounter < number; i++) {
        flag = true;
        if (i % 2 == 0)
          continue;
        for (int j = 3; j <= i; j++) {
          if (j == i) {}
          else if (i % j == 0) {
            flag = false;
            break;
          }
        }
        if (flag) {
          primeCounter++;
          nthPrime = i;
        }
      }

    if (primeCounter == 1)
      return 2;

    return nthPrime;
  }

}
