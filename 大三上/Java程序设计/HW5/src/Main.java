import java.util.Arrays;

interface Computable {
    abstract int[] add(int[] a, int[] b);

    abstract int[] minus(int[] a, int[] b);

    abstract int[] elementwiseProduct(int[] a, int[] b);
}

class Vector implements Computable {

    @Override
    public int[] add(int[] a, int[] b) {
        int[] result = new int[4];
        for (int i = 0; i < 4; i++) {
            result[i] = a[i] + b[i];
        }
        return result;
    }

    @Override
    public int[] minus(int[] a, int[] b) {
        int[] result = new int[4];
        for (int i = 0; i < 4; i++) {
            result[i] = a[i] - b[i];
        }
        return result;
    }

    @Override
    public int[] elementwiseProduct(int[] a, int[] b) {
        int[] result = new int[4];
        for (int i = 0; i < 4; i++) {
            result[i] = a[i] * b[i];
        }
        return result;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] a = {5, 2, 1, 8};
        int[] b = {3, -1, 0, -4};
        Vector vector = new Vector();
        System.out.println("Add: " + Arrays.toString(vector.add(a, b)));
        System.out.println("Minus: " + Arrays.toString(vector.minus(a, b)));
        System.out.println("ElementwiseProduct: " + Arrays.toString(vector.elementwiseProduct(a, b)));
    }
}