public class ArraySum implements InArray{
    @Override
    public int[] addArrays(int[] array1, int[] array2) {
        int length = Math.min(array1.length, array2.length);
        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            result[i] = array1[i] + array2[i];
        }

        return result;
    }
}
