import java.util.Scanner;

public class StepPrice {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int electricity;
        for(int i=0;i<10;i++){
            electricity=scanner.nextInt();
            System.out.println("用电量为"+electricity+"应缴纳"+ getPrice(electricity)+"元");
        }
    }
    public static float getPrice(int electricity){
        float price=0f;
        if(electricity<=50){
            price= (float) (electricity*0.55);
        }else if(electricity<=220){
            price= (float) (50*0.55+(electricity-50)*0.58);
        }else{
            price= (float) (50*0.55+170*0.58+(electricity-220)*0.65);
        }
        return price;
    }
}
