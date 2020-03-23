package by.future.common.rpc.server.impl;

import by.future.common.rpc.server.interfaces.IEchoService;
import org.apache.commons.lang3.StringUtils;

/**
 * @author by@Deng
 * @create 2020-03-21 10:59
 */
public class EchoServiceImpl implements IEchoService {


    @Override
    public String echo(String ping) {
        if(StringUtils.isNotEmpty(ping)){
            return String.format("接收到的参数原封不动的还给你：%s",ping);
        }

        return null;
    }



}
