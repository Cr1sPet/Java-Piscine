package edu.school21.numbers;

import edu.school21.numbers.exceptions.IllegalNumberException;

public class NumberWorker {

    public boolean isPrime (int num) {

        if (num <= 1) {
            throw new IllegalNumberException("Incorrect Number");
        }


        if (num == 2 || num == 3) {
            return true;
        }

        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }


    public int digitsSum(int number) {

        int sum = 0;

        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

}