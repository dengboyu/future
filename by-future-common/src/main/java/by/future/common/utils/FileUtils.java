package by.future.common.utils;

import by.future.entity.constant.SysConst;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;


/**
 * 文件操作工具类
 *
 * @Author：by@Deng
 * @Date：2017/7/11 13:29
 */
public class FileUtils extends org.apache.commons.io.FileUtils {

    private static Logger log = LoggerFactory.getLogger(FileUtils.class);

    private static final int ONE_KB = 1024;
    private static final int ONE_MB = ONE_KB * ONE_KB;


    /**
     * 复制文件，如果目标文件存在，则不覆盖
     *
     * @param srcFileName  待复制的文件名
     * @param descFileName 目标文件名
     * @return 复制成功，返回true，否则返回false
     */
    public static boolean copyFile(String srcFileName, String descFileName) {
        return copyFileCover(srcFileName, descFileName, false);
    }


    /**
     * 复制文件
     *
     * @param srcFileName  待复制的文件名
     * @param descFileName 目标文件名
     * @param coverFlag     如果目标文件已存在，是否覆盖
     * @return 复制成功，返回true，否则返回false
     */
    public static boolean copyFileCover(String srcFileName, String descFileName, boolean coverFlag) {
        File srcFile = new File(srcFileName);
        if (!srcFile.exists()) {
            log.info("源文件"+ srcFileName +"不存在");
            return false;
        } else if (!srcFile.isFile()) {
            log.info("复制文件失败，"+ srcFileName +"不是一个文件");
            return false;
        }

        File descFile = new File(descFileName);
        if (descFile.exists()) {
            if (coverFlag) {
                log.info("目标文件已存在，准备删除");
                if (!delFile(descFileName)) {
                    log.info("删除目标文件"+ descFileName +"失败");
                    return false;
                }
            } else {
                log.info("复制文件失败，目标文件"+ descFileName +"已存在");
                return false;
            }
        } else {
            if (!descFile.getParentFile().exists()) {
                //创建目标文件所在的目录
                if (!descFile.getParentFile().mkdirs()) {
                    log.info("创建目标文件所在的目录失败");
                    return false;
                }
            }
        }
        
        InputStream ins = null;
        OutputStream outs = null;
        try {
            
            ins = new FileInputStream(srcFile); //输入流
            outs = new FileOutputStream(descFile); //输出流

            byte[] bytes = new byte[ONE_KB];
            int readByte;   //一次读取1024个字节，当readByte为-1时表示文件已经读取完毕
            
            while ((readByte = ins.read(bytes)) != -1) {
                outs.write(bytes, 0, readByte);
            }
            log.info("复制"+ srcFileName +"文件到"+ descFileName +"成功");
            return true;
        } catch (Exception e) {
            log.info("复制文件失败："+ e.getMessage());
            return false;
        } finally {
            //先关闭输出流，然后再关闭输入流
            if (outs != null) {
                try {
                    outs.close();
                } catch (IOException e) {
                    log.info("关闭输出流失败："+ e.getMessage());
                }
            }
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    log.info("关闭输入流失败："+ e.getMessage());
                }
            }
        }
    }


    /**
     * 复制整个目录的内容，如果目标目录存在，则不覆盖
     *
     * @param srcDirName  源目录名
     * @param descDirName 目标目录名
     * @return 复制成功返回true，否则返回false
     */
    public static boolean copyDirectory(String srcDirName, String descDirName) {
        return copyDirectoryCover(srcDirName, descDirName, false);
    }


    /**
     * 复制整个目录的内容
     *
     * @param srcDirName  源目录名
     * @param descDirName 目标目录名
     * @param coverFlag    如果目标目录存在，是否覆盖
     * @return 复制成功返回true，否则返回false
     */
    public static boolean copyDirectoryCover(String srcDirName, String descDirName, boolean coverFlag) {
        File srcDir = new File(srcDirName);
        if (!srcDir.exists()) {
            log.info("复制目录失败，源目录"+ srcDirName +"不存在");
            return false;
        } else if (!srcDir.isDirectory()) {
            log.info("复制目录失败，"+ srcDirName +"不是一个目录");
            return false;
        }

        String descDirNames = descDirName;
        if (!descDirNames.endsWith(File.separator)) {
            descDirNames = descDirNames + File.separator;
        }

        File descDir = new File(descDirNames);
        if (descDir.exists()) {
            if (coverFlag) {
                log.info("目标目录已存在，准备删除");
                if (!delFile(descDirNames)) {
                    log.info("删除目录"+ descDirNames +"失败");
                    return false;
                }
            } else {
                log.info("目标目录复制失败，目标目录"+ descDirNames +"已存在");
                return false;
            }
        } else {
            log.info("目标目录不存在，准备创建");
            if (!descDir.mkdirs()) {
                log.info("创建目标目录失败");
                return false;
            }
        }

        boolean flag = true;

        File[] files = srcDir.listFiles();
        for (int i = 0; i < files.length; i++) {

            if (files[i].isFile()) {
                flag = copyFile(files[i].getAbsolutePath(), descDirName + files[i].getName());
            } else if (files[i].isDirectory()) {
                flag = copyDirectory(files[i].getAbsolutePath(), descDirName + files[i].getName());
            }

            if (!flag) {
                log.info(files[i]+"文件或文件夹复制失败");
                break;
            }
        }

        if (!flag) {
            log.info("复制目录"+ srcDirName +"到"+ descDirName +"失败");
            return false;
        }
        log.info("复制目录"+ srcDirName +"到"+ descDirName +"成功");
        return true;
    }


    /**
     * 删除文件或文件夹
     *
     * @param fileName 被删除的文件名
     * @return 删除成功，返回true，否是返回false
     */
    public static boolean delFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            log.info(fileName +"文件或文件夹不存在");
            return true;
        } else {
            if (file.isFile()) {
                return deleteFile(fileName);
            } else {
                return deleteDirectory(fileName);
            }
        }
    }


    /**
     * 删除文件
     *
     * @param fileName 被删除的文件名
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                log.info("删除文件"+ fileName +"成功");
                return true;
            } else {
                log.info("删除文件"+ fileName +"失败");
                return false;
            }
        } else {
            log.info(fileName +"文件不存在");
            return true;
        }
    }


    /**
     * 删除目录及目录下的文件
     *
     * @param dirName 被删除的目录所在的文件路径
     */
    public static boolean deleteDirectory(String dirName) {
        String dirNames = dirName;
        if (!dirNames.endsWith(File.separator)) {
            dirNames = dirNames + File.separator;
        }
        File dirFile = new File(dirNames);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            log.info(dirNames +"目录不存在");
            return true;
        }

        boolean flag = true;

        //列出全部文件及子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
               
            } else if (files[i].isDirectory()) {
                //删除子目录
                flag = deleteDirectory(files[i].getAbsolutePath());
            }

            if (!flag) {
                log.info(files[i].getAbsolutePath() +"文件或文件夹删除失败");
                break;
            }
        }

        if (!flag) {
            log.info("删除目录失败");
            return false;
        }
        
        //删除当前目录
        if (dirFile.delete()) {
            log.info("删除目录"+ dirName +"成功");
            return true;
        } else {
            log.info("删除目录"+ dirName +"失败");
            return false;
        }
    }
    

    /**
     * 创建单个文件
     *
     * @param descFileName 文件名，包含路径
     * @return 创建成功，返回true，否则返回false
     */
    public static boolean createFile(String descFileName) {
        File file = new File(descFileName);
        if (file.exists()) {
            log.info("文件"+ descFileName +"已存在");
            return false;
        }
        if (descFileName.endsWith(File.separator)) {
            log.info(descFileName +"为目录，不能创建目录");
            return false;
        }
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                log.info("创建文件所在的目录失败");
                return false;
            }
        }

        //创建文件
        try {
            if (file.createNewFile()) {
                log.info(descFileName +"文件创建成功");
                return true;
            } else {
                log.info(descFileName +"文件创建失败");
                return false;
            }
        } catch (Exception e) {
            log.info(descFileName +"文件创建失败");
            return false;
        }
    }


    /**
     * 创建目录
     *
     * @param filePath 目录名,包含路径
     * @return 创建成功，返回true，否则返回false
     */
    public static boolean createDirectory(String filePath) {
        //判断是否以系统后缀符结束
        if (!filePath.endsWith(File.separator)) filePath = filePath + File.separator;

        File descDir = new File(filePath);
        if (descDir.exists()) return false;

        //创建目录
        if (descDir.mkdirs()) return true;

        return false;
    }


    /**
     * 写入文件
     *
     * @param fileName 要写入的文件
     */
    public static void writeToFile(String fileName, String content, boolean append) {
        try {
            write(new File(fileName), content, SysConst.CHARSET_UTF8, append);
            log.info("文件"+ fileName +"写入成功");
        } catch (IOException e) {
            log.info("文件"+ fileName +"写入失败!"+ e.getMessage());
        }
    }


    /**
     * 写入文件
     *
     * @param fileName 要写入的文件
     */
    public static void writeToFile(String fileName, String content, String encoding, boolean append) {
        try {
            write(new File(fileName), content, encoding, append);
            log.info("文件"+ fileName +"写入成功");
        } catch (IOException e) {
            log.info("文件"+ fileName +"写入失败!"+ e.getMessage());
        }
    }


    /**
     * 修复路径，将 \\ 或 / 等替换为 File.separator
     *
     * @param path
     * @return
     */
    public static String path(String path) {
        String p = StringUtils.replace(path,"\\","/");
        p = StringUtils.join(StringUtils.split(p,"/"),"/");
        if (!StringUtils.startsWithAny(p,"/") && StringUtils.startsWithAny(path,"\\","/")) {
            p +="/";
        }
        if (!StringUtils.endsWithAny(p,"/") && StringUtils.endsWithAny(path,"\\","/")) {
            p = p +"/";
        }
        return p;
    }


    /**
     * 判断系统
     *
     * @author by@Deng
     * @date 2017/9/30 19:48
     */
    public static String checkSystem() {
        String stem = System.getProperty("os.name");
        if (stem.contains("Mac")) {
            return"Mac";
        } else if (stem.contains("Windows")) {
            return"Windows";
        } else if (stem.contains("Linux")) {
            return"Linux";
        }
        return null;
    }


    /**
     * 判断文件夹是否存在
     *
     * @author by@Deng
     * @date 2017/9/30 15:14
     */
    public static boolean fileExist(String pathFile) {
        File filePath = new File(pathFile);
        return filePath.exists();
    }


    /**
     * todo 待改进，使用NIO分割大文件
     *
     * @param tempDirectory 临时文件夹，fileSize 小文件大小，MB为单位
     * @Author：by@Deng
     * @Date：2019/12/28 14:23
     */
    public static boolean splitLargeFile(File sourceFile,File tempDirectory,int fileSize){

        FileInputStream fis = null;
        int size = ONE_MB;

        try {

            if(!tempDirectory.isDirectory()){
                tempDirectory.mkdirs();
            }

            size = size * fileSize;
            long length = sourceFile.length();  //文件大小

            int num = (int)(length / size);
            int mod = (int)(length % size);

            String newSplitFile = sourceFile.getAbsolutePath();
            int newFile = newSplitFile.lastIndexOf(".");
            String strNew = newSplitFile.substring(newFile);

            fis = new FileInputStream(sourceFile);
            File[] files = new File[num+1];

            for(int i =0;i<num;i++){
                //指定分割后小文件的文件名
                files[i] = new File(tempDirectory.getAbsolutePath()+File.separator+(i+1)+strNew+".tem");
                if(!files[i].isFile()){
                    files[i].createNewFile();
                }

                FileOutputStream fos = new FileOutputStream(files[i]);
                byte[] bytes = new byte[size];
                fis.read(bytes);
                fos.write(bytes);

                fos.close();
            }

            if(mod!=0){
                files[num] = new File(tempDirectory.getAbsolutePath() +File.separator+(num+1)+".tem");

                if(!files[num].isFile()){
                    files[num].createNewFile();
                }
                FileOutputStream fos = new FileOutputStream(files[num]);
                byte[] bytes = new byte[mod];
                fis.read(bytes);
                fos.write(bytes);
                fos.close();
            }

            log.info("分割成"+(mod!=0?(num+1):num)+"文件");
            return true;
        }catch (IOException e){
            log.info("分割错误:"+e.getMessage());
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                log.info("关闭流错误:"+e.getMessage());
            }
        }
        return false;
    }


}
