package by.future.web.task;


import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * spring的scheduled定时任务
 *
 * @Author：by@Deng
 * @Date：2018/12/7 16:01
 */
@Component
public class ScheduledTask {


    @Scheduled(fixedRate = 5000)
    public void reportTest(){
        System.out.println("当前的时间:"+ DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
    }


}
