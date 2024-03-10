public class PrimeJudge {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 2; i < 501; i++) {
            if (isPrime(i)) {
                count++;
                System.out.print(i + " ");
                if (count % 20 == 0)
                    System.out.println();
            }
        }
        System.out.print("\n总共有" + count + "个素数");
    }

    public static boolean isPrime(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }
}
