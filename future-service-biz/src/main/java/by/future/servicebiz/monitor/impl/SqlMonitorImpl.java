package by.future.servicebiz.monitor.impl;


import by.future.common.utils.ReflectUtils;
import by.future.servicebiz.monitor.ISqlMonitor;
import by.future.servicebiz.monitor.entity.MarketingMonitorEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 监控系统
 *
 * @Author：by@Deng
 * @Date：2018/6/14 22:22
 */
@Service
public class SqlMonitorImpl implements ISqlMonitor {

//    @Resource
//    private IMarketingMonitorDao marketingMonitorDao;


    /**
     * 执行sql运行监控
     *
     * @Author：by@Deng
     * @Date：2018/6/14 22:39
     */
    @Override
    public void doMonitorTask() {

        String logTitle = "doMonitorTask";
        HashMap<String,String> logMap = new HashMap();
//        logMap.put(CLogger.ACTION,logTitle);

        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        try {
//            List<MarketingMonitor> marketingMonitorList = marketingMonitorDao.queryAll();
            List<MarketingMonitorEntity> marketingMonitorList = new ArrayList<>();

            Date nowTime = new Date();

            for(MarketingMonitorEntity monitor:marketingMonitorList){

                String sql = monitor.getMonitorSql();
                if(StringUtils.isEmpty(sql)) return;

//                String startTime = String.format("%s %s", DateFormatUtils.format(monitor.getMonitorStartDate(), DateUtils.YMDPattern), monitor.getMonitorStartTime());
//                String endTime = String.format("%s %s", DateFormatUtils.format(monitor.getMonitorEndDate(), DateUtils.YMDPattern), monitor.getMonitorEndTime());

//                if (nowTime.before(DateUtils.parseString(DateUtils.YMDHHMMSSPattern,startTime))) return;//未到执行时间
//                if (nowTime.after(DateUtils.parseString(DateUtils.YMDHHMMSSPattern,endTime))) return;  //已经过期

                //下次执行时间
                Date nextExecuteTime = org.apache.commons.lang3.time.DateUtils.addMinutes(monitor.getLastExecuteTime(),monitor.getRollingMinute());
                if(nowTime.before(nextExecuteTime)) return; //未到执行时间

                singleThreadPool.execute(()->{
                    //执行sql
                    Object object = ReflectUtils.execute(monitor.getClassName(),monitor.getMethodName(),monitor);
                });
            }
        }catch (Exception e){
//            CLogger.Warn(logTitle,e.getMessage(),logMap);
        }finally {
            singleThreadPool.shutdown();
        }
    }

}
