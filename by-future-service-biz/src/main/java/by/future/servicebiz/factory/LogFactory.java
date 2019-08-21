package by.future.servicebiz.factory;

import by.future.common.utils.ApplicationContextUtils;
import by.future.servicebiz.multipleinstance.ILog;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author by@Deng
 * @create 2019-08-21 22:48
 */
public class LogFactory {

    private static ConcurrentHashMap<String,ILog> logMap = new ConcurrentHashMap<>();


    /**
     * 获取ILog
     *
     * @Author: by@Deng
     * @Date: 2019-08-21 22:54
     */
    public static ILog builderLog(String name){

        ILog log = ApplicationContextUtils.getBean(name);

        ILog oldLog = logMap.putIfAbsent(name,log);
        if(oldLog!=null){
            log = oldLog;
        }
        return log;
    }

}
