package by.future.web.websocket.chat;

import org.apache.commons.collections4.CollectionUtils;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

/**
 * 给所有client发送消息
 *
 * @author by@Deng
 * @create 2019-09-22 13:51
 */
public class SendMsgToClientThread extends Thread {

    private List<PrintWriter> pwList;
    private LinkedList<String> msgList;

    public SendMsgToClientThread(List<PrintWriter> pwList, LinkedList<String> msgList) {
        this.pwList = pwList;
        this.msgList = msgList;
    }

    @Override
    public void run() {

        while (this.isAlive()){
            try {
                if(CollectionUtils.isNotEmpty(msgList)){
                    //取信息链表中最后一条，并移除
                    String msg = msgList.removeLast();

                    //对输出流列表进行遍历，循环发送消息给所有客户端
                    for(int i =0;i<pwList.size();i++){
                        pwList.get(i).println(msg);
                        pwList.get(i).flush();
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }
    }
}
