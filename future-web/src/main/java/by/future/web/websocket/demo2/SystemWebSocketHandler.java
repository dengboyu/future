package by.future.web.websocket.demo2;

/**
 *
 *
 * @author by@Deng
 * @create 2017-10-27 13:55
 *//*
public class SystemWebSocketHandler implements WebSocketHandler {

    private static final ArrayList<WebSocketSession> users = new ArrayList<>();


    *//**
     * 建立连接之后
     * @author by@Deng
     * @date 2017/10/27 下午2:01
     *//*
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println("连接成功了");
    }

    *//**
     * 连接中,处理逻辑
     * @author by@Deng
     * @date 2017/10/27 下午2:01
     *//*
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        sendMessageToUsers(new TextMessage(webSocketMessage.getPayload() + "hello"));
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }



    *//**
     * 给所有在线用户发送消息
     *//*
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                user.sendMessage(message);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}*/
