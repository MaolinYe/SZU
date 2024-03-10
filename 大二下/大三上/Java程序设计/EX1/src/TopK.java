import java.util.PriorityQueue;

public class TopK {
    public static void main(String[] args) {
        float[][] number=new float[100][100];
        for(int i=0;i<100;i++){
            for(int j=0;j<100;j++){
                number[i][j]= (float) Math.random();
            }
        }
        long startTime=System.nanoTime();
        PriorityQueue<Float>minHeap=new PriorityQueue<Float>();
        for(int i=0;i<5;i++){
            minHeap.add(number[0][i]);
        }
        for(int i=0;i<100;i++){
            for(int j=0;j<100;j++){
                if(number[i][j]>minHeap.peek()){
                    minHeap.poll();
                    minHeap.add(number[i][j]);
                }
            }
        }
        long endTime=System.nanoTime();
        System.out.println(minHeap);
        System.out.print("程序运行时间为"+(endTime-startTime)/1000+"微秒");
    }
}
