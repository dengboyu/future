package by.future.common.rpc.server.executors;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author by@Deng
 * @create 2020-03-21 11:03
 */
public class ServerExecutorTask implements Runnable {

    private Socket client;

    public ServerExecutorTask(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {

        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {

            objectInputStream = new ObjectInputStream(client.getInputStream());

            String interfaceName = objectInputStream.readUTF();
            Class<?> clazz = Class.forName(interfaceName);

            String methodName = objectInputStream.readUTF();

            Class<?>[] parameterTypes = (Class<?>[]) objectInputStream.readObject();

            Object[] arguments = (Object[]) objectInputStream.readObject();

            Method method = clazz.getMethod(methodName,parameterTypes);

            Object ob = clazz.newInstance();
            Object result = method.invoke(ob,arguments);

            objectOutputStream = new ObjectOutputStream(client.getOutputStream());
            objectOutputStream.writeObject(result);

        } catch (IOException e) {
            System.out.println("我是IO异常:"+e.getMessage());
        }catch (Exception e){
            System.out.println("我是RunTime异常:"+e.getMessage());
        }finally {
            if(objectOutputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(client!=null){
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
