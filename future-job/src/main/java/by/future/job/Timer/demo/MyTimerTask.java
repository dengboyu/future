package by.future.job.Timer.demo;

import java.util.TimerTask;

/**
 * @author by@Deng
 * @create 2018-05-27 22:54
 */
public class MyTimerTask extends TimerTask {

    private String name;
    public MyTimerTask(String inputName){
        name = inputName;
    }

    @Override
    public void run() {
        System.out.println("currentName is:"+name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
