package by.future.thread.demo.impl;

/**
 * @author by@Deng
 * @create 2019-07-04 01:24
 */
public class ThreadLocalTest implements Runnable {

    /*private static ThreadLocal<Integer> count=new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 3;
        }
    };*/

    private static int count =3;


    @Override
    public void run() {

        /*if(count.get()>5){
            count.set(count.get()-1);
        }else{
            count.set(count.get()+1);
        }*/
        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized(ThreadLocalTest.class){
            if(count>6){
                count--;
            }else{

                count++;
            }
            System.out.println(count);
        }




    }
}
