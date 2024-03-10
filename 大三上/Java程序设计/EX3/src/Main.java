import java.util.TreeMap;

class Country implements Comparable<Country> {
    private String name;
    private int GDP2020;
    private int COVID19;

    public Country(String name, int GDP2020, int COVID19) {
        this.name = name;
        this.GDP2020 = GDP2020;
        this.COVID19 = COVID19;
    }

    public void show() {
        System.out.println("国家名称：" + name + " 国内生产总值（GDP）：" + GDP2020 + " 新冠肺炎累计确诊人数：" + COVID19);
    }

    @Override
    public int compareTo(Country o) {
        return Integer.compare(this.COVID19, o.COVID19);
    }
}

public class Main {
    public static void main(String[] args) {
        TreeMap<Country, String> countryMap = new TreeMap<>();
        countryMap.put(new Country("美国", 20932750, 44918565), "美国");
        countryMap.put(new Country("中华人民共和国", 14722837, 124924), "中华人民共和国");
        countryMap.put(new Country("日本", 5048688, 1706675), "日本");
        countryMap.put(new Country("德国", 3803014, 4284354), "德国");
        countryMap.put(new Country("英国", 2710970, 8006660), "英国");
        countryMap.put(new Country("印度", 2708770, 33893002), "印度");
        countryMap.put(new Country("法国", 2598907, 7038701), "法国");
        countryMap.put(new Country("意大利", 1884935, 4689341), "意大利");
        countryMap.put(new Country("加拿大", 1643408, 1647142), "加拿大");
        countryMap.put(new Country("韩国", 1630871, 323379), "韩国");
        for (Country country : countryMap.keySet()) {
            country.show();
        }
    }
}
