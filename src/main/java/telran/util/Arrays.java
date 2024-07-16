package telran.util;

import java.util.Comparator;

public class Arrays {
    public static int search(int[] ar, int key) {
        int index = 0;
        while (index < ar.length && key != ar[index]) {
            index++;
        }
        return index == ar.length ? -1 : index;
    }

    public static int[] add(int[] ar, int number) {
        int[] res = java.util.Arrays.copyOf(ar, ar.length + 1);
        res[ar.length] = number;
        return res;
    }

    public static int[] insert(int[] ar, int index, int number) {
        int res[] = new int[ar.length + 1];
        System.arraycopy(ar, 0, res, 0, index);
        res[index] = number;
        System.arraycopy(ar, index, res, index + 1, ar.length - index);
        return res;
    }

    public static int[] remove(int[] numbers, int index) {
        int[] res = numbers;
        res = new int[numbers.length - 1];
        System.arraycopy(numbers, 0, res, 0, index);
        System.arraycopy(numbers, index + 1, res, index, res.length - index);
        return res;
    }

    private static boolean pushMaxAtEnd(int[] ar, int length) {
        boolean res = true;
        for (int i = 0; i < length; i++) {
            if (ar[i] > ar[i + 1]) {
                res = false;
                swap(ar, i, i + 1);
            }
        }
        return res;
    }

    private static void swap(int[] ar, int i, int j) {
        int tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
    }

    public static void sort(int[] ar) {
        int length = ar.length;
        boolean flSorted = false;
        while (!flSorted) {
            length--;
            flSorted = pushMaxAtEnd(ar, length);
        }
    }

    public static int binarySearch(int[] ar, int key) {
        int left = 0;
        int right = ar.length - 1;
        int index = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (key == ar[mid]) {
                index = mid;
                break;
            }
            if (ar[mid] < key)
                left = mid + 1;
            else
                right = mid - 1;
        }

        if (index < 0) {
            index = -(left - 1);

        }

        return index;
    }

    public static int[] insertSorted(int[] arSorted, int number) {
        int insertIndex = Math.abs((binarySearch(arSorted, number) - 1));
        return insert(arSorted, insertIndex, number);
    }

    public static boolean isOneSwap(int[] array) {
        int indexFromRight = getIndexFromRight(array);
        int indexFromLeft = getIndexFromLeft(array);
        boolean res = false;
        if (indexFromLeft != indexFromRight && indexFromLeft != -1 && indexFromRight != -1) {
            swap(array, indexFromLeft, indexFromRight);
            res = isSorted(array);
            swap(array, indexFromRight, indexFromLeft);
        }

        return res;
    }

    private static boolean isSorted(int[] ar) {
        boolean res = true;
        int i = 0;
        while ((i < ar.length - 1) && (res == true)) {
            if (ar[i] > ar[i + 1])
                res = false;
            i++;
        }
        return res;
    }

    private static int getIndexFromRight(int[] ar) {
        int res = -1;
        int i = ar.length - 1;
        while ((i >= 1) && (res == -1)) {
            if (ar[i - 1] > ar[i]) {
                res = i;
            }
            i--;
        }
        return res;
    }

    private static int getIndexFromLeft(int[] ar) {
        int res = -1;
        int i = 0;
        while ((i < ar.length - 1) && (res == -1)) {
            if (ar[i] > ar[i + 1]) {
                res = i;
            }
            i++;
        }
        return res;
    }

    public static <T> void sort(T[] array, Comparator<T> comparator) {
        int length = array.length;
        boolean flSort = false;
        do {
            length--;
            flSort = true;
            for (int i = 0; i < length; i++) {
                if (comparator.compare(array[i], array[i + 1]) > 0) {
                    swap(array, i, i + 1);
                    flSort = false;
                }
            }
        } while (!flSort);
    }

    private static <T> void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static <T> int binarySearch(T[] array, T key, Comparator<T> comp) {
        int left = 0;
        int right = array.length - 1;
        int middle = (left + right) / 2;
        while (left <= right && array[middle] != key) {
            if (comp.compare(key, array[middle]) < 0) {
                right = middle - 1;
            } else {
                left = middle + 1;
            }
            middle = (left + right) / 2;
        }
        return left > right ? -(left + 1) : middle;
    }
}
