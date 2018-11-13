package by.future.servicebiz.monitor;


import by.future.servicebiz.monitor.entity.MarketingMonitorEntity;

/**
 * 监控系统-执行sql
 *
 * @Author：by@Deng
 * @Date：2018/6/17 13:52
 */
public interface IMonitorSystem {


    /**
     * 红包监控系统
     *
     * @Author：by@Deng
     * @Date：2018/6/17 14:32
     */
    void sendRedMoneyMonitor(MarketingMonitorEntity marketingMonitor);




}
