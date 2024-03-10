public class SequenceSum {
    public static void main(String[] args) {
        float sum=0;
        for(int i=1;i<21;i++){
            sum+=getOne(i);
        }
        System.out.println("前20项和为"+sum);
    }
    public static float getOne(int n){
        return (float) (n/Math.pow(5,n-1));
    }
}
