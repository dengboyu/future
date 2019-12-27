package by.future.web.websocket.chat;

import by.future.entity.constant.SysConst;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.BufferedReader;
import java.util.Date;
import java.util.LinkedList;

/**
 * 接收client的聊天信息线程
 *
 * @author by@Deng
 * @create 2019-09-22 13:35
 */
public class GetMsgFromClientThread extends Thread {

    private BufferedReader br;
    private LinkedList<String> msgList;

    public GetMsgFromClientThread(BufferedReader br, LinkedList<String> msgList) {
        this.br = br;
        this.msgList = msgList;
    }

    @Override
    public void run() {
        while (this.isAlive()){
            try {

                String msg = br.readLine();
                if(msg != null){

                    String replayMsg = String.format("<- %s ->\n%s", DateFormatUtils.format(new Date(), SysConst.DATE_DATETIME_FORMAT),msg);
                    System.out.println(replayMsg);
                    //添加到信息链表的集合中
                    msgList.addFirst(replayMsg);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }
    }
}
