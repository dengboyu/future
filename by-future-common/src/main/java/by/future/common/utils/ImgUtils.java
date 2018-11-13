package by.future.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;

/**
 * Created by by@Deng on 2017/7/11.
 */
public class ImgUtils {

    /**
     * 对图片进行缩放
     * @param targetWidth 目标宽度
     * @param targetHeight 目标高度
     * @param newImg
     * @return
     *
     * @Author: by@Deng
     * @Date: 2018/6/16 下午12:07
     */
    public static BufferedImage resizeImg(int targetWidth, int targetHeight, BufferedImage newImg) {
        BufferedImage newImageTwo = new BufferedImage(targetWidth, targetHeight, newImg.getType());
        Graphics g = newImageTwo.getGraphics();
        g.drawImage(newImg, 0, 0, targetWidth, targetHeight, null); //画图
        g.dispose();
        return newImageTwo;
    }

    /**
     * 合成图片并且返回字节数组
     * @param qrPosition_x
     * @param qrPosition_y
     * @param mainImg
     * @param slaveImg
     * @param qrWidth
     * @param qrHeight
     */
    public static byte[]  getSlaveImg(int qrPosition_x,int qrPosition_y,String mainImg, String slaveImg,int qrWidth,int qrHeight) {
        byte[] uploadBytes=null;
        try {
            URL mainImgURL = new URL(mainImg);
            URL slaveImgURL = new URL(slaveImg);
            //读取第一张图片
            BufferedImage ImageOne = ImageIO.read(mainImgURL);
            int width = ImageOne.getWidth();//图片宽度
            int height = ImageOne.getHeight();//图片高度
            //从图片中读取RGB
            int[] ImageArrayOne = new int[width * height];
            ImageArrayOne = ImageOne.getRGB(0, 0, width, height, ImageArrayOne, 0, width);
            //对第二张图片做相同的处理
            BufferedImage ImageTwo = ImageIO.read(slaveImgURL);
            //新图片
            BufferedImage newImageTwo = resizeImg(qrWidth, qrHeight, ImageTwo);
            int widthTwo = newImageTwo.getWidth();//图片宽度
            int heightTwo = newImageTwo.getHeight();//图片高度
            int[] ImageArrayTwo = new int[widthTwo * heightTwo];
            ImageArrayTwo = newImageTwo.getRGB(0, 0, widthTwo, heightTwo, ImageArrayTwo, 0, widthTwo);

            //生成新图片
            BufferedImage ImageNew = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            ImageNew.setRGB(0, 0, width, height, ImageArrayOne, 0, width);//设置左半部分的RGB  第一张图片
            ImageNew.setRGB(qrPosition_x, qrPosition_y, widthTwo, heightTwo, ImageArrayTwo, 0, widthTwo);//设置右半部分的RGB

            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            ImageIO.write(ImageNew, "png", byteArrayOutputStream);//写图片
            uploadBytes =byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadBytes;
    }


    /**
     * 合成图片并且返回字节数组
     * @param qrPosition_x
     * @param qrPosition_y
     * @param mainByte
     * @param slaveByte
     * @param qrWidth
     * @param qrHeight
     */
    public static byte[]  getSlaveImg(int qrPosition_x,int qrPosition_y,byte[] mainByte, byte[] slaveByte,int qrWidth,int qrHeight) {
        byte[] uploadBytes=null;
        try {

            //读取第一张图片
            BufferedImage ImageOne = ImageIO.read(new ByteArrayInputStream(mainByte));
            int width = ImageOne.getWidth();//图片宽度
            int height = ImageOne.getHeight();//图片高度
            //从图片中读取RGB
            int[] ImageArrayOne = new int[width * height];
            ImageArrayOne = ImageOne.getRGB(0, 0, width, height, ImageArrayOne, 0, width);

            //对第二张图片做相同的处理
            BufferedImage ImageTwo = ImageIO.read(new ByteArrayInputStream(slaveByte));
            //新图片
            BufferedImage newImageTwo = resizeImg(qrWidth, qrHeight, ImageTwo);
            int widthTwo = newImageTwo.getWidth();//图片宽度
            int heightTwo = newImageTwo.getHeight();//图片高度
            int[] ImageArrayTwo = new int[widthTwo * heightTwo];
            ImageArrayTwo = newImageTwo.getRGB(0, 0, widthTwo, heightTwo, ImageArrayTwo, 0, widthTwo);

            //生成新图片
            BufferedImage ImageNew = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            ImageNew.setRGB(0, 0, width, height, ImageArrayOne, 0, width);//设置左半部分的RGB  第一张图片
            ImageNew.setRGB(qrPosition_x, qrPosition_y, widthTwo, heightTwo, ImageArrayTwo, 0, widthTwo);//设置右半部分的RGB

            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            ImageIO.write(ImageNew, "png", byteArrayOutputStream);//写图片
            uploadBytes =byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadBytes;
    }


    /**
     * 合成图片并且返回字节数组
     * @param qrPosition_x
     * @param qrPosition_y
     * @param mainImg
     * @param slaveByte
     * @param qrWidth
     * @param qrHeight
     */
    public static byte[]  getSlaveImg(int qrPosition_x,int qrPosition_y,String mainImg, byte[] slaveByte,int qrWidth,int qrHeight) {
        byte[] uploadBytes=null;
        try {
            URL mainImgURL = new URL(mainImg);
            //读取第一张图片
            BufferedImage ImageOne = ImageIO.read(mainImgURL);
            int width = ImageOne.getWidth();//图片宽度
            int height = ImageOne.getHeight();//图片高度
            //从图片中读取RGB
            int[] ImageArrayOne = new int[width * height];
            ImageArrayOne = ImageOne.getRGB(0, 0, width, height, ImageArrayOne, 0, width);
            //对第二张图片做相同的处理
            BufferedImage ImageTwo = ImageIO.read(new ByteArrayInputStream(slaveByte));
            //新图片
            BufferedImage newImageTwo = resizeImg(qrWidth, qrHeight, ImageTwo);
            int widthTwo = newImageTwo.getWidth();//图片宽度
            int heightTwo = newImageTwo.getHeight();//图片高度
            int[] ImageArrayTwo = new int[widthTwo * heightTwo];
            ImageArrayTwo = newImageTwo.getRGB(0, 0, widthTwo, heightTwo, ImageArrayTwo, 0, widthTwo);

            //生成新图片
            BufferedImage ImageNew = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            ImageNew.setRGB(0, 0, width, height, ImageArrayOne, 0, width);//设置左半部分的RGB  第一张图片
            ImageNew.setRGB(qrPosition_x, qrPosition_y, widthTwo, heightTwo, ImageArrayTwo, 0, widthTwo);//设置右半部分的RGB

            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            ImageIO.write(ImageNew, "png", byteArrayOutputStream);//写图片
            uploadBytes =byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return uploadBytes;
    }

}
