package by.future.web.websocket.chat;

import javax.swing.*;
import java.io.BufferedReader;

/**
 * 从服务端获取回复socket
 *
 * @author by@Deng
 * @create 2019-09-22 14:40
 */
public class GetMsgFromServerThread extends Thread{

    private BufferedReader br;
    private JTextArea txtContent;

    public GetMsgFromServerThread(BufferedReader br, JTextArea txtContent) {
        this.br = br;
        this.txtContent = txtContent;
    }

    @Override
    public void run() {
        while (this.isAlive()){
            try {

                String strMsg = br.readLine();
                if(strMsg != null){
                    //在文本中显示聊天信息
                    txtContent.append(strMsg+"\n");
                }

                Thread.sleep(50);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
        }
    }
}
