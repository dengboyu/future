package by.future.common.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.LockSupport;

/**
 * @author by@Deng
 * @create 2020-04-12 15:39
 */
public class ByLock {

    private static Unsafe unsafe = null;

    static {

        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");

            f.setAccessible(true);

            unsafe = (Unsafe) f.get(null);
            stateOffSet = unsafe.objectFieldOffset(ByLock.class.getDeclaredField("state"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private volatile int state=0;

    private static long stateOffSet =0;

    private Thread hasLockThread;    //当前获取锁的线程

    //线程安全队里，但不是阻塞队列
    private ConcurrentLinkedDeque<Thread> waitThreadQueue = new ConcurrentLinkedDeque();


    /**
     * 尝试获取锁
     *
     * @Author: by@Deng
     * @Date: 2020/4/12 4:21 下午
     */
    private boolean acquire(){

        Thread currentThread = Thread.currentThread();

        if((waitThreadQueue.size()==0 || currentThread == waitThreadQueue.peek()) && compareAndSwapState(0,1)){

            setHasLockThread(currentThread);
            return true;
        }

        return false;
    }


    /**
     * 加锁
     *
     * @Author: by@Deng
     * @Date: 2020/4/12 4:33 下午
     */
    public void lock(){

        if(acquire()){
            return;
        }

        Thread thread = Thread.currentThread();
        waitThreadQueue.add(thread);

        //让所有线程自旋
        for(;;){

            if(waitThreadQueue.peek() == thread && acquire()){
                waitThreadQueue.poll();

                return;
            }

            //线程挂起
            LockSupport.park();
        }
    }


    /**
     * 释放锁
     *
     * @Author: by@Deng
     * @Date: 2020/4/12 4:33 下午
     */
    public void unLock() throws Exception{

        Thread thread = Thread.currentThread();
        if (thread != hasLockThread) {
            throw new Exception("not current thread");
        }

        int state = getState();
        if(compareAndSwapState(state,0)){

            setHasLockThread(null);

            Thread nextThread = waitThreadQueue.peek();
            if(nextThread!=null){
                //线程唤醒
                LockSupport.unpark(nextThread);
            }
        }
    }




    public final boolean compareAndSwapState(int expect,int update){
        return unsafe.compareAndSwapInt(this,stateOffSet,expect,update);
    }


    public Thread getHasLockThread() {
        return hasLockThread;
    }

    public void setHasLockThread(Thread hasLockThread) {
        this.hasLockThread = hasLockThread;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
