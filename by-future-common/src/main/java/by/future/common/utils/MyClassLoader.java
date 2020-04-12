package by.future.common.utils;

import java.io.File;
import java.io.FileInputStream;

/**
 * 自定义创建ClassLoader
 * @specical：
 *
 * @author by@Deng
 * @create 2019-09-22 18:11
 */
public class MyClassLoader extends ClassLoader{


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = loadClassData(name);

        //调用ClassLoader的dineClass方法将二进制数据转换成class对象
        return this.defineClass(name, data, 0, data.length);
    }


    private byte[] loadClassData(String className) {

        try {
            //当前项目路径
            String path = this.getClass().getResource("/").getPath().substring(1);
            className = className.replaceAll(".","/");

            File classFile = new File(path + className+".class");
            long len = classFile.length();

            byte[] raw = new byte[(int)len];

            FileInputStream fileInputStream = new FileInputStream(classFile);

            //一次性读取class文件的全部二进制数据
            int r = fileInputStream.read(raw);
            if(r != len){
                System.out.println("无法读取全部文件！");
                return null;
            }

            return raw;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

        return null;
    }


}
