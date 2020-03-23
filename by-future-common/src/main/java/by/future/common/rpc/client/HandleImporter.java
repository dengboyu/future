package by.future.common.rpc.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author by@Deng
 * @create 2020-03-21 11:47
 */
public class HandleImporter implements InvocationHandler {

    private Class<?> serviceClass;
    private InetSocketAddress addr;

    public HandleImporter(Class<?> serviceClass, InetSocketAddress addr) {
        this.serviceClass = serviceClass;
        this.addr = addr;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Socket socket = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;

        try {

            socket = new Socket();
            socket.connect(addr);

            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(serviceClass.getName());
            objectOutputStream.writeUTF(method.getName());
            objectOutputStream.writeObject(method.getParameterTypes());
            objectOutputStream.writeObject(args);

            objectInputStream = new ObjectInputStream(socket.getInputStream());

            return objectInputStream.readObject();

        }catch (IOException e){
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(socket!=null){
                socket.close();
            }

            if (objectOutputStream!=null) {
                objectOutputStream.close();
            }

            if(objectInputStream!=null){
                objectInputStream.close();
            }
        }

        return null;
    }
}
