package by.future.common.cache;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程安全缓冲区
 *
 * @Author：by@Deng
 * @Date：2019/3/14 12:51
 */
public class SafeBuffer<T> {

    //显示锁lock
    private Lock lock = new ReentrantLock();

    //存数据condition
    private Condition putCondition = lock.newCondition();

    //获取数据condition
    private Condition getPutCondition = lock.newCondition();

    //初始化长度为100的数组
    Object[] objectArr = new Object[100];

    //当前存入下标位置
    private int putStr;

    //当前获取下标位置
    private int getStr;

    //当前容器已有数据个数
    private int count;

    /**
     * 存数据
     *
     * @Author：by@Deng
     * @Date：2019/3/14 14:31
     */
    public void put(T t) throws InterruptedException{

        try {

            //获取锁
            lock.lock();

            while (count == objectArr.length){
                putCondition.await();
            }

            objectArr[putStr] = t;

            if(++putStr > objectArr.length-1){
                putStr = 0;
            }

            ++count;

            //唤醒正在此对象监视器上等待的单个线程，选择是随机的
            getPutCondition.signal();
        }finally {
            //释放锁
            lock.unlock();
        }

    }


    /**
     * 获取数据
     *
     * @Author：by@Deng
     * @Date：2019/3/14 14:31
     */
    public T get() throws InterruptedException{

        try {

            lock.lock();

            while (count ==0){
                getPutCondition.await();
            }

            T t = (T) objectArr[getStr];

            if( ++getStr > objectArr.length-1){
                getStr = 0;
            }

            --count;

            putCondition.signal();

            return t;
        }finally {
            lock.unlock();
        }
    }










}
