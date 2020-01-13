package by.future.common.utils;


import by.future.entity.common.SignResultEntity;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：by@Deng
 * @Date：2020/1/13 20:13
 */
public class RedisUtils {


    /**
     * 分布式锁 2.6.12版本之前，这个方法还是有问题的，借鉴把时间戳带上的思想即可
     * 依赖setNx，getSet两个方法，不依赖expire方法，即便删除锁失败时，逻辑（2）会规避锁不释放问题
     *
     * @Author：by@Deng
     * @Date：2020/1/13 20:14
     */
    public static boolean getLock(String lockKey){

        boolean lock = false;
        while (!lock){

            //锁的时间5s
            long lockTime = 5000;

            String expireTime = String.valueOf(System.currentTimeMillis() + lockTime);

            lock = setNx(lockKey,expireTime);
            if(lock) return lock;

            String oldTimeStr = getStr(lockKey);
            if(oldTimeStr !=null && !"".equals(oldTimeStr.trim())){
                Long oldTime = Long.valueOf(oldTimeStr);

                Long nowTime = System.currentTimeMillis();

                //（2）锁的时间小于当前时间，强制忽略该线程持有的锁，重新设置自己的锁
                if(oldTime < nowTime){

                    //获取之前的时间戳，这里会出现多个线程竞争，但肯定只会有一个线程会获取到锁时设置的expire
                    String oldTimeStr2 = getSet(lockKey,String.valueOf(System.currentTimeMillis()+lockTime));

                    //如果刚获取的时间戳和之前获取的时间戳一样的话，说明没有其他线程在占用这个锁，则此线程可以获取这个锁
                    if(oldTimeStr2!=null && oldTimeStr.equals(oldTimeStr2)){
                        lock = true;    //获取锁标记
                        break;
                    }
                }
            }

            try {
                //暂停50ms，重新循环
                Thread.sleep(50);
            }catch (InterruptedException e){

            }
        }

        return lock;
    }


    public static boolean setNx(String lockKey,String expireTime){
        return false;
    }

    public static String getStr(String lockKey){
        return "";
    }

    public static String getSet(String lockKey,String value){
        return "";
    }


    //锁的数量越少，每个用户对锁的竞争越激烈，直接打到数据库的流量就越少，如果太小，又影响系统的吞吐量
    public static final  String[] orderLockArr = new String[128];
    static {
        for(int i =0;i<128;i++){
            orderLockArr[i] = "orderLock_"+i;
        }
    }


    public List<SignResultEntity> getCacheByUid(String uid){
        return new ArrayList<>();
    }


    /**
     * 防雪崩、击穿获取db信息
     *
     * @Author：by@Deng
     * @Date：2020/1/13 22:53
     */
    public List<SignResultEntity> getSirnRusultEntityList(String uid){

        //从缓存中获取
        List<SignResultEntity> signResultEntityList = getCacheByUid(uid);
        if(CollectionUtils.isNotEmpty(signResultEntityList)){
            return signResultEntityList;
        }

        //获取锁，锁的粒度：hashcode与128取余，保证最多有128个线程到数据库中
        int index = uid.hashCode() & (orderLockArr.length -1); //对128取余

        try {

            //此处加锁，保证最多有128个线程到数据库请求
            getLock(orderLockArr[index]);

            //再判断缓存中是否有数据，有可能别的线程存入到缓存中
            signResultEntityList = getCacheByUid(uid);
            if(CollectionUtils.isNotEmpty(signResultEntityList)){
                return signResultEntityList;
            }

            //查询db
            signResultEntityList = new ArrayList<>();
            if(CollectionUtils.isNotEmpty(signResultEntityList)){
                //落缓存

            }else{
                //落缓存为空

            }

        }finally {
            //释放锁
            //removeLock(orderLockArr[index]);
        }

        return signResultEntityList;
    }


}
