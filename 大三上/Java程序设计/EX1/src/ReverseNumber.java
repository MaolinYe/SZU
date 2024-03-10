import java.util.Scanner;

public class ReverseNumber {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String number=new String();
        for(int i=0;i<10;i++){
            number=scanner.next();
            System.out.println(new StringBuilder(number).reverse());
        }
    }
}
