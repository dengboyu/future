package by.future.web.websocket.chat;

import by.future.common.utils.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端socket
 *
 * @author by@Deng
 * @create 2019-09-22 14:12
 */
public class ChatClient extends JFrame {

    private Socket socket;
    private PrintWriter pw;
    private BufferedReader br;

    private JPanel panel;
    private JScrollPane sPane;
    private JTextArea txtContent;
    private JLabel lblName,lblSend;
    private JTextField txtName,txtSend;
    private JButton btnSend;

    public ChatClient() {
        super("QST聊天室");

        txtContent= new JTextArea();

        //设置文本域只读
        txtContent.setEditable(false);
        sPane = new JScrollPane(txtContent);

        lblName = new JLabel("昵称：");
        txtName = new JTextField(5);
        lblSend = new JLabel("发言：");
        txtSend = new JTextField(20);
        btnSend = new JButton("发送");

        panel = new JPanel();
        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblSend);
        panel.add(txtSend);
        panel.add(btnSend);
        this.add(panel, BorderLayout.SOUTH);

        this.add(sPane);
        this.setSize(500,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {

            socket = new Socket("127.0.0.1",28888);
            pw = new PrintWriter(socket.getOutputStream());

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        //注册监听
        btnSend.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户输入文本
                String strName = txtName.getText();
                String strMsg = txtSend.getText();
                if(StringUtils.isNotEmpty(strMsg)){

                    pw.println(strName +"说："+strMsg);
                    pw.flush();

                    //清空文本
                    txtSend.setText("");
                }
            }
        });

        //启动线程
        new GetMsgFromServerThread(br,txtContent).start();
    }

    public static void main(String[] args) {
        new ChatClient().setVisible(true);
        new ChatClient().setVisible(true);
    }
}
