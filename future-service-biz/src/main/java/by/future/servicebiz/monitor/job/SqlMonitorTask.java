package by.future.servicebiz.monitor.job;


import by.future.servicebiz.monitor.ISqlMonitor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 监控系统
 *
 * @Author：by@Deng
 * @Date：2018/6/14 22:04
 */
@Component
public class SqlMonitorTask {

    @Resource
    private ISqlMonitor sqlMonitorImpl;

//    @QSchedule(value = "")
    public void doWork() {

//        final TaskMonitor taskMonitor = TaskHolder.getKeeper();
//        taskMonitor.autoAck(false);

        new Thread(()->{
            try {
                sqlMonitorImpl.doMonitorTask();
//                taskMonitor.finish();
            } catch (Exception e) {
//                taskMonitor.fail(e);
            }
        }).start();

    }





}
