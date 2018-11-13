package by.future.servicebiz.lottery;


/**
 * @Author：by@Deng
 * @Date：2018/6/9 8:54
 */
public class LotteryEntity {

    private String prizeNum;    //发放金额
    private String prizeName;   //名称
    private double prizeProbability;    //概率

    public String getPrizeNum() {
        return prizeNum;
    }

    public void setPrizeNum(String prizeNum) {
        this.prizeNum = prizeNum;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public double getPrizeProbability() {
        return prizeProbability;
    }

    public void setPrizeProbability(double prizeProbability) {
        this.prizeProbability = prizeProbability;
    }
}
