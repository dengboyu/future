package by.future.servicebiz.miaosha.impl;

import by.future.servicebiz.miaosha.IMiaoShaService;
import by.future.servicebiz.miaosha.MiaoShaTestEntity;
import org.springframework.stereotype.Service;

/**
 * @author by@Deng
 * @create 2019-09-04 01:15
 */
@Service
public class MiaoShaServiceImpl implements IMiaoShaService {


    /**
     * 秒杀商品
     *
     * @Author: by@Deng
     * @Date: 2019/9/4 1:19 上午
     */
    @Override
    public boolean killGoods(int userId, int goodsId) {

        //1.判断当前用户是否秒杀到当前商品
        if (true) {

            //2.查询秒杀商品详情
            MiaoShaTestEntity miaoShaTestEntity = new MiaoShaTestEntity();

            //3.判断是否可以被秒杀
            if(miaoShaTestEntity.getCanKillFlag() == 1){

                //扣减库存
                int count = 0;
                //count = killDao.updateCount();

                //扣减库存成功
                if(count>0){
                    //秒杀成功，插入秒杀订单记录，并异步通知去支付等信息

                    //killRecordDao.insertDao();

                    return true;
                }

                System.out.println("扣减库存失败");
            }else{
                System.out.println("该商品不可以被秒杀");
            }

        }else{
            System.out.println("该用户已经秒杀到商品");
        }

        return false;
    }
}
