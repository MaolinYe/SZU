class Athlete{
    private String name;
    private String sport;
    private int medal;
    public Athlete(String name,String sport,int medal){
        this.name=name;
        this.sport=sport;
        this.medal=medal;
    }
    @Override
    public String toString(){
        System.out.println("姓名："+name+"\n运动项目："+sport+"\n奖牌数量："+medal);
        return "";
    }
}
public class Athletes {
    public static void main(String[] args) {
        Athlete athlete=new Athlete("齐天大圣","近身搏斗",0);
        athlete.toString();
    }
}
