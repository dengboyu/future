package by.future.common.test.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * 强软弱虚引用
 *
 * @author by@Deng
 * @create 2020-04-11 09:41
 */
public class ReferenceTest {


    public static void main(String[] args) {

        Object obj = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue();   //引用队列用于告诉用户线程，用作反操作


        //软引用
        SoftReference<Object> stringSoftReference = new SoftReference<>(obj,referenceQueue);

        //弱引用
//        WeakReference<Object> weakReference = new WeakReference<>(obj,referenceQueue);


        obj = null;

        System.gc();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(stringSoftReference.get());
        System.out.println(referenceQueue.poll());

        /*System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());*/

    }



}
