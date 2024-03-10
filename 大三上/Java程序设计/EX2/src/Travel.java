import java.util.*;

class COVID19Status {
    private int death; //死亡人数
    private int cumulativeCases; //累积确诊人数
    private int currentCases; //现有确诊人数
    public COVID19Status(int death, int cumulativeCases, int currentCases) {
        this.death = death;
        this.cumulativeCases = cumulativeCases;
        this.currentCases = currentCases;
    }
    public int getCumulativeCases() {
        return cumulativeCases;
    }
    public int getCurrentCases() {
        return currentCases;
    }
    public int getDeath() {
        return death;
    }
}

class TravelIndex {
    private float deathRatio;
    private float currentCaseRatio;
    private float travelIndex;
    public TravelIndex(COVID19Status covid19Status) {
        this.deathRatio = covid19Status.getDeath()*1.0f / covid19Status.getCumulativeCases();
        this.currentCaseRatio = covid19Status.getCurrentCases() *1.0f/ covid19Status.getCumulativeCases();
        this.travelIndex = 1 / (deathRatio*0.7f+ currentCaseRatio*0.3f);
    }
    public float getTravelIndex() {
        return travelIndex;
    }
}

public class Travel {
    public static void main(String[] args) {
        COVID19Status china = new COVID19Status(31509,9506895 , 90067);
        COVID19Status japan = new COVID19Status(74694, 33800000, 11980000);
        COVID19Status singapore = new COVID19Status(1885, 2600000, 450000);
        COVID19Status usa = new COVID19Status(1180000, 108830000, 1000000);
        COVID19Status uk = new COVID19Status(230000, 24950000, 74763);
        COVID19Status russia = new COVID19Status(400000, 23050000, 160000);

        TravelIndex chinaIndex = new TravelIndex(china);
        TravelIndex japanIndex = new TravelIndex(japan);
        TravelIndex singaporeIndex = new TravelIndex(singapore);
        TravelIndex usaIndex = new TravelIndex(usa);
        TravelIndex ukIndex = new TravelIndex(uk);
        TravelIndex russiaIndex = new TravelIndex(russia);

        Map<String, Float> travelIndexMap = new HashMap<>();
        travelIndexMap.put("中国旅游推荐指数", chinaIndex.getTravelIndex());
        travelIndexMap.put("日本旅游推荐指数", japanIndex.getTravelIndex());
        travelIndexMap.put("新加坡旅游推荐指数", singaporeIndex.getTravelIndex());
        travelIndexMap.put("美国旅游推荐指数", usaIndex.getTravelIndex());
        travelIndexMap.put("英国旅游推荐指数", ukIndex.getTravelIndex());
        travelIndexMap.put("俄罗斯旅游推荐指数", russiaIndex.getTravelIndex());

        // 排序并输出
        List<Map.Entry<String, Float>> list = new ArrayList<>(travelIndexMap.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        for (Map.Entry<String, Float> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
