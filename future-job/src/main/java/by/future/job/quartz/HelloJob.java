package by.future.job.quartz;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * @author by@Deng
 * @create 2018-05-28 22:47
 */
public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss")+":Hello world");


    }
}
