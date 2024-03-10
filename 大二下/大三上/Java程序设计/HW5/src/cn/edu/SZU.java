package cn.edu;
public class SZU {
    private final int collegeNumbers = 3;
    private final College[] colleges = {new College("计算机与软件学院"),
            new College("金融科技学院"), new College("医学院")};

    public void getColledgeNames() {
        for (College college : colleges) {
            college.show();
        }
    }

    public void getCollegeNumbers() {
        System.out.println("There is " + collegeNumbers + " colleges.");
    }
}
