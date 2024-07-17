package telran.util.test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import static telran.util.Arrays.add;
import static telran.util.Arrays.binarySearch;
import static telran.util.Arrays.insert;
import static telran.util.Arrays.insertSorted;
import static telran.util.Arrays.isOneSwap;
import static telran.util.Arrays.remove;
import static telran.util.Arrays.search;
import static telran.util.Arrays.sort;

public class ArraysTest {
    private static final int N_ELEMENTS = 1000;

    int[] numbers = { 10, 7, 12, -4, 13, 3, 14 };

    private int[] getRandomArray(int nElements) {
        int[] res = new int[nElements];
        Random random = new Random();
        for (int i = 0; i < nElements; i++) {
            res[i] = random.nextInt();
        }
        return res;
    }

    @Test
    void searchTest() {
        assertEquals(0, search(numbers, 10));
        assertEquals(6, search(numbers, 14));
        assertEquals(3, search(numbers, -4));
        assertEquals(-1, search(numbers, 100));
    }

    @Test
    void addTest() {
        int newNumber = 100;
        int[] expected = { 10, 7, 12, -4, 13, 3, 14, newNumber };
        assertArrayEquals(expected, add(numbers, newNumber));
    }

    @Test
    void insertTest() {
        // {10, 7, 12, -4, 13, 3, 14} - all numbers
        int newNumber = 30;
        int[] expected_0 = { newNumber, 10, 7, 12, -4, 13, 3, 14 };
        int[] expected_3 = { 10, 7, 12, newNumber, -4, 13, 3, 14 };
        int[] expected_last = { 10, 7, 12, -4, 13, 3, 14, newNumber };
        assertArrayEquals(expected_0, insert(numbers, 0, newNumber));
        assertArrayEquals(expected_3, insert(numbers, 3, newNumber));
        assertArrayEquals(expected_last, insert(numbers, numbers.length, newNumber));
        assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> insert(numbers, numbers.length + 1, newNumber));
        assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> insert(numbers, -1, newNumber));
    }

    @Test
    void removeTest() {
        // {10, 7, 12, -4, 13, 3, 14} - all numbers
        int[] expected_0 = { 7, 12, -4, 13, 3, 14 };
        int[] expected_3 = { 10, 7, 12, 13, 3, 14 };
        int[] expected_last = { 10, 7, 12, -4, 13, 3 };
        assertArrayEquals(expected_0, remove(numbers, 0));
        assertArrayEquals(expected_3, remove(numbers, 3));
        assertArrayEquals(expected_last, remove(numbers, numbers.length - 1));
        assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> remove(numbers, numbers.length));
        assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> remove(numbers, -1));
    }

    @Test
    void sortTest() {
        int[] testNumbers = java.util.Arrays.copyOf(numbers, numbers.length);
        int[] expected = { -4, 3, 7, 10, 12, 13, 14 };
        sort(testNumbers);
        assertArrayEquals(expected, testNumbers);
    }

    @Test
    void sortTestRandomArray() {
        int[] array = getRandomArray(N_ELEMENTS);
        int limit = array.length - 1;
        sort(array);
        for (int i = 0; i < limit; i++) {
            assertTrue(array[i] <= array[i + 1]);
        }
    }

    @Test
    void binarySearchTest() {
        int[] array = { 153, -4, 6, 515, 3, 7, 10, 12, 789, 13, 14 };
        int[] array_1 = { 0, 10 };
        int[] array_2 = { 1, 2, 7, 3, 5, 4 };
        sort(array);
        sort(array_1);
        sort(array_2);
        assertEquals(10, binarySearch(array, 789));
        assertEquals(0, binarySearch(array, 2));
        assertEquals(1, binarySearch(array_1, 10));
        assertEquals(-4, binarySearch(array_2, 6));
    }

    @Test
    void insertSortedTest() {
        int[] arraySorted = { -8, -7, -6, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int[] arrayExpected = { -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        assertArrayEquals(arrayExpected, insertSorted(arraySorted, -5));

        int[] arraySorted_2 = { 0 };
        int[] arrayExpected_2 = { 0, 1 };
        assertArrayEquals(arrayExpected_2, insertSorted(arraySorted_2, 1));

        int[] arraySorted_3 = { 0 };
        int[] arrayExpected_3 = { -1, 0 };
        assertArrayEquals(arrayExpected_3, insertSorted(arraySorted_3, -1));

        int[] ar = { 1, 2, 4, 5 };
        int[] expected = { 0, 1, 2, 4, 5 };
        assertArrayEquals(expected, insertSorted(ar, 0));

    }

    @Test
    void isOneSwapTest() {
        int[] arrOneSwap = { 0, 1, 2, 3, 5, 4, 8, 9, 10 };
        assertTrue(isOneSwap(arrOneSwap));

        int[] arrOneSwap_1 = { 10, 2, 3, 4, 5, 8, 9, 1 };
        assertTrue(isOneSwap(arrOneSwap_1));

        int[] arrNotOneSwap = { 0, 1, 2, 3, 5, 4, 8, 99, 10, 45 };
        int[] arrNotOneSwap_1 = { 0, 1 };

        assertFalse(isOneSwap(arrNotOneSwap));
        assertFalse(isOneSwap(arrNotOneSwap_1));
    }

    @Test
    void sortAnyTypeTest() {
        String[] strings = { "lmn", "cfta", "w", "aa" };
        String[] expectedASCII = { "aa", "cfta", "lmn", "w" };
        String[] expectedLength = { "w", "aa", "lmn", "cfta" };

        sort(strings, new ComparatorASCII());
        assertArrayEquals(expectedASCII, strings);
        sort(strings, new ComparatorLength());
        assertArrayEquals(expectedLength, strings);
    }

    @Test
    void binarySearchAnyTypeTest() {
        String[] colors = { "red", "blue", "yellow", "green" };
        String[] colorsAfterSorting = { "blue", "green", "red", "yellow" };
        sort(colors, new ComparatorASCII());
        assertArrayEquals(colors, colorsAfterSorting);
        assertEquals(1, binarySearch(colors, "green", new ComparatorASCII()));
        assertEquals(3, binarySearch(colors, "yellow", new ComparatorASCII()));
        assertEquals(2, binarySearch(colors, "red", new ComparatorASCII()));
        assertEquals(0, binarySearch(colors, "blue", new ComparatorASCII()));
        assertEquals(-4, binarySearch(colors, "white", new ComparatorASCII()));

        Integer[] numbers = { 9, 3, 5, 0, -2, -28 };
        Integer[] numbersAfterSorting = { -28, -2, 0, 3, 5, 9 };
        sort(numbers, new ComparatorIntegers());
        assertArrayEquals(numbersAfterSorting, numbers);
        assertEquals(0, binarySearch(numbers, -28, new ComparatorIntegers()));
        assertEquals(2, binarySearch(numbers, 0, new ComparatorIntegers()));
        assertEquals(5, binarySearch(numbers, 9, new ComparatorIntegers()));
        assertEquals(-6, binarySearch(numbers, 6, new ComparatorIntegers()));

        assertEquals(1, binarySearch(colors, new String("green"), new ComparatorASCII()));
        Integer[] numbers2 = { 1000, 2000 };
        assertEquals(0, binarySearch(numbers2, 1000, new ComparatorIntegers()));
    }
}
