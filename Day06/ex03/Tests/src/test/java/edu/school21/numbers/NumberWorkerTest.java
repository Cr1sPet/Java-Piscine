package edu.school21.numbers;


import edu.school21.numbers.exceptions.IllegalNumberException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest  {

    NumberWorker numberWorker;

    @BeforeEach
    void beforeEachMethod() {
        numberWorker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 521, 34543, 2147483647})
    void isPrimeForPrimes(int num) {
        Assertions.assertTrue(numberWorker.isPrime(num));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 21423, 9363, 20001, 9})
    void isPrimeNotForPrimes(int num) {
        Assertions.assertFalse(numberWorker.isPrime(num));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 0, -1, -100})
    void isPrimeForIncorrectNumbers(int num) {
        Assertions.assertThrows(IllegalNumberException.class, () -> numberWorker.isPrime(num));
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"}, delimiter = ',')
    void checkSumOfDigits(int x, int y) {
        Assertions.assertEquals(numberWorker.digitsSum(x), y);
    }


}
