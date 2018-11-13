package by.future.servicebiz.lottery;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 抽奖概率
 *
 * @Author：by@Deng
 * @Date：2018/6/9 8:55
 */
public class LotteryUtils {

    public static LotteryEntity lottery(List<LotteryEntity> lotteryEntityList){
        //1. 计算配置规则的总概率
        double sumRate =0;
        for (LotteryEntity rule:lotteryEntityList){
            sumRate += rule.getPrizeProbability();
        }

        //2. 计算每个概率的相对坐标值
        double tempRate = 0;
        List<Double> erverRates = new ArrayList<>();
        for(LotteryEntity rule:lotteryEntityList){
            tempRate += rule.getPrizeProbability();
            erverRates.add(tempRate / sumRate);
        }

        //3. 根据抽奖值获取奖品的索引
        double rdNum = Math.random();
        erverRates.add(rdNum);
        Collections.sort(erverRates);

        int index = erverRates.indexOf(rdNum);
        return  lotteryEntityList.get(index);
    }

    public static void main(String[] args) {
        List<LotteryEntity> lotteryEntityList = new ArrayList<>();

        LotteryEntity lotteryEntity = new LotteryEntity();
        lotteryEntity.setPrizeProbability(0.1);
        lotteryEntity.setPrizeNum("1");
        lotteryEntityList.add(lotteryEntity);

        lotteryEntity = new LotteryEntity();
        lotteryEntity.setPrizeProbability(0.2);
        lotteryEntity.setPrizeNum("2");
        lotteryEntityList.add(lotteryEntity);

        lotteryEntity = new LotteryEntity();
        lotteryEntity.setPrizeProbability(0.4);
        lotteryEntity.setPrizeNum("3");
        lotteryEntityList.add(lotteryEntity);

        lotteryEntity = new LotteryEntity();
        lotteryEntity.setPrizeProbability(0.2);
        lotteryEntity.setPrizeNum("4");
        lotteryEntityList.add(lotteryEntity);

        lotteryEntity = new LotteryEntity();
        lotteryEntity.setPrizeProbability(0.1);
        lotteryEntity.setPrizeNum("5");
        lotteryEntityList.add(lotteryEntity);

        for (int i = 0; i < 100; i++) {
            lotteryEntity = lottery(lotteryEntityList);
            System.out.println(lotteryEntity.getPrizeNum());
        }

    }

}
