package by.future.servicebiz.monitor.impl;


import by.future.servicebiz.monitor.IMonitorSystem;
import by.future.servicebiz.monitor.entity.MarketingMonitorEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 监控系统-执行sql
 *
 * @Author：by@Deng
 * @Date：2018/6/17 13:51
 */
@Service
public class MonitorSystemImpl implements IMonitorSystem {


//    private ICommonDao commonDao;
//    private IMarketingMonitorDao marketingMonitorDao;
//    private IOutboundCallBiz outboundCallBiz;

    {
        try {
//            commonDao = new CommonDao(); //注意：反射动态获取
//            marketingMonitorDao = new MarketingMonitorDao();
//            outboundCallBiz = new OutboundCallBizImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行sql
     *
     * @Author：by@Deng
     * @Date：2018/6/15 1:21
     */
    public  <T> List<T> executeSql(MarketingMonitorEntity monitor, String sql, Class<T> clazz) {
        switch (monitor.getMonitorType().toUpperCase()) {
            case "DB":
//                return commonDao.selectSql(sql,clazz);
                return null;
        }
        return new ArrayList<>();
    }


    /**
     * 发送邮件或者短信
     * @param content 为自定义通知文案，若为空则用默认文案
     * @Author：by@Deng
     * @Date：2018/6/15 9:01
     */
    public void sendAlarm(MarketingMonitorEntity monitor,long account,String content) {
        try {
            boolean isSendMail = false;
            boolean isSendMsg = false;
            String operatorName = "";

            if(StringUtils.equals(monitor.getOperator(),">")){

                if(account>Long.valueOf(monitor.getEmailAlarmCount())) isSendMail = true;   //发送邮件
                if(account>Long.valueOf(monitor.getMobileAlarmCount())) isSendMsg = true;   //发送短信
                operatorName ="大于";
            }else  if(StringUtils.equals(monitor.getOperator(),"<")){

                if(account<=Long.valueOf(monitor.getEmailAlarmCount())) isSendMail = true;   //发送邮件
                if(account<=Long.valueOf(monitor.getMobileAlarmCount())) isSendMsg = true;   //发送短信
                operatorName ="小于";
            }

            if (isSendMail) {
//                String emailContent =StringUtils.isNotEmpty(content)? content:String.format(monitor.getAlarmMessage(), DateUtils.formatDate("yyyy-MM-dd HH:mm", date), monitor.getRollingMinute(), monitor.getMonitorName(), account, monitor.getAlarmCountUnit(), operatorName, monitor.getEmailAlarmCount(), monitor.getAlarmCountUnit());
//                EmailUtil.send(monitor.getAlarmEmail(),emailContent,String.format("<div style='color:red;font-weight:bold;font-size:14px;'>%s<br/>执行sql:%s</div>",emailContent, HtmlUtils.htmlEscape(monitor.getMonitorSql())));
            }

            if (isSendMsg) {
//                String smsContent = StringUtils.isNotEmpty(content)? content:String.format(monitor.getAlarmMessage(), DateUtils.formatDate("yyyy-MM-dd HH:mm", date), monitor.getRollingMinute(), monitor.getMonitorName(), account, monitor.getAlarmCountUnit(), operatorName, monitor.getMobileAlarmCount(), monitor.getAlarmCountUnit());
//                outboundCallBiz.sendMarketingSMS("",monitor.getAlarmMobile(),smsContent+ "\n执行sql:" +  monitor.getMonitorSql(),"");
            }

            //更新最后执行时间
            MarketingMonitorEntity updateMonitor = new MarketingMonitorEntity();
//            updateMonitor.setId(monitor.getId());
//            updateMonitor.setLastExecuteTime(new Timestamp(DateUtils.addMinutes(monitor.getLastExecuteTime(),monitor.getRollingMinute()).getTime()));
//            marketingMonitorDao.update(updateMonitor);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * 红包监控系统
     *
     * @Author：by@Deng
     * @Date：2018/6/17 14:32
     */
    @Override
    public void sendRedMoneyMonitor(MarketingMonitorEntity marketingMonitor){
        System.out.println("执行反射成功1");

//        String dateBegin = DateFormatUtils.format(marketingMonitor.getLastExecuteTime(), DateUtils.YMDHHMMSSPattern);
//        String dateEnd = DateFormatUtils.format(org.apache.commons.lang3.time.DateUtils.addMinutes(marketingMonitor.getLastExecuteTime(),marketingMonitor.getRollingMinute()), DateUtils.YMDHHMMSSPattern);
//
//        String sql = String.format(marketingMonitor.getMonitorSql(), dateBegin,dateEnd);

//        long account = executeSql(marketingMonitor,sql,Long.class).get(0);
//        marketingMonitor.setMonitorSql(sql);

//        sendAlarm(marketingMonitor,account,null);
    }






}
