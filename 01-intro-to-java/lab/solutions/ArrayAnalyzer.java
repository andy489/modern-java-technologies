public class ArrayAnalyzer {
    private static final int MINIMUM_ELEMENTS_REQUIREMENT = 3;

    public static boolean isMountainArray(int[] array) {
        int size = array.length;

        if (size < MINIMUM_ELEMENTS_REQUIREMENT) {
            return false;
        }

        int iterator = 0;

        while (iterator + 1 < size && array[iterator] < array[iterator + 1]) {
            ++iterator;
        }

        while (iterator + 1 < size && array[iterator] > array[iterator + 1]) {
            ++iterator;
        }

        return iterator == size - 1;
    }
}
