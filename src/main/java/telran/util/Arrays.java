package telran.util;

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
        int res = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (key == ar[mid]) {
                res = mid;
                break;
            }
            if (ar[mid] < key)
                left = mid + 1;
            else
                right = mid - 1;

        }
        return res;
    }

    public static int[] insertSorted(int[] arSorted, int number) {
        int insertIndex = java.util.Arrays.binarySearch(arSorted, number);
        if (insertIndex < 0) {
            insertIndex = -(insertIndex + 1);
        }
        return insert(arSorted, insertIndex, number);
    }

    public static boolean isOneSwap(int[] array) {
        int length = array.length;
        boolean flSorted = false;
        int counter = 0;
        while (!flSorted) {
            length--;
            flSorted = true;
            for (int i = 0; i < length; i++) {
                if (array[i] > array[i + 1]) {
                    counter++;
                    flSorted = false;
                    int tmp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = tmp;
                }
            }
            if (counter > 1)
                break;
        }

        return counter == 1;
    }
}
