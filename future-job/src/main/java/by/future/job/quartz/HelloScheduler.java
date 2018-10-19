package by.future.job.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author by@Deng
 * @create 2018-05-28 22:51
 */
public class HelloScheduler {

    public static void main(String[] args) throws Exception{

        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("myJob","group1")
                .build();

        System.out.println(jobDetail.getKey().getName());
        System.out.println(jobDetail.getKey().getGroup());
        System.out.println(jobDetail.getJobClass().getName());

        //创建一个trigger
        CronTrigger trigger =TriggerBuilder.newTrigger().withIdentity("myTrigger","group1")
                        .startNow()
                        .withSchedule(CronScheduleBuilder.cronSchedule("0 15 10 * * ? 2017"))
                        .build();

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail,trigger);




    }


}
