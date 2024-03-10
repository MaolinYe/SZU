class Queue{
    private short[] elements; //数据
    private int size=0; //队列数据个数
    private int capacity; //队列容量
    public Queue(){
        capacity=16;
        elements=new short[capacity];
    }
    public void enqueue(short v){
        if(size>=capacity){ //队列已满
            System.out.println("The queue is full.");
        }else{
            elements[size++]=v; //入队，个数增加
        }
    }
    public short dequeue(){
        if(size<=0){ //空队列
            System.out.println("The queue is empty.");
            return 0;
        }else{
            short out=elements[0];
            for(int i=0;i<size-1;i++){
                elements[i]=elements[i+1];
            }
            size--;
            return out;
        }
    }
    public int getSize(){
        return size;
    }
    public void show(){
        System.out.println("The size of the queue is "+getSize());
        if(size==0){ //空队列
            System.out.println("The queue is empty.");
            return;
        }
        for(int i=0;i<size;i++){
            System.out.print(i+" ");
        }
        System.out.println();
    }
}
public class Queues {
    public static void main(String[] args) {
        Queue queue=new Queue();
        for(short i=0;i<16;i++){ //元素入队
            queue.enqueue(i);
        }
        queue.show(); //输出队列元素
        queue.enqueue((short) 16);
        queue.show();
        for(int i=0;i<16;i++){
            queue.dequeue();
        }
        queue.show();
    }
}
